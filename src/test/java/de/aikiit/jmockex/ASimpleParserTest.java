/**
 * Test Examples,  Copyright (C) 2016  P.Ottlinger
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.aikiit.jmockex;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@Disabled("not yet working")
public class ASimpleParserTest {

    private static final String CONTENTS = "This is a test with German umlauts or not, äöüß!";

    @TempDir
    private static Path testdata;

    @BeforeEach
    public void showTestDataBaseDir() throws IOException {
        Assertions.assertNotNull(testdata);
        System.out.println(
                "Base directory is " + testdata.getRoot() + " containing " + listFilesNullSafe(testdata.toFile()));
    }

    private static String listFilesNullSafe(File folder) {
        final File[] files = folder.listFiles();
        if (files != null) {
            return String.valueOf(Arrays.asList(files));
        }

        return "nothing";
    }

    public final void nullArguments() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new ASimpleParser(null);
                }
        );
    }

    public final void performWrite() throws IOException {

        final Path path = testdata.toAbsolutePath();

        final ASimpleParser parser = new ASimpleParser(path);
        parser.write(CONTENTS).flush();
        final String readFromFile = parser.getContents();
        assertThat(readFromFile, isA(String.class));
        assertThat(readFromFile, equalTo(CONTENTS));
        System.out.println(parser);
        System.out.println("Base directory " + testdata.getRoot() + " containsExactlyOnce "
                + listFilesNullSafe(testdata.getRoot().toFile()));
    }

    public void writeFluentlyAndReadContents() throws IOException {
        final Path path = testdata.toAbsolutePath();

        final ASimpleParser parser = new ASimpleParser(path);

        parser.append(CONTENTS).append(CONTENTS).append(CONTENTS).flush();
        final List<String> flushedContents = read(path);
        assertThat(flushedContents, hasItems(CONTENTS + CONTENTS + CONTENTS));
        System.out.println(parser);
    }

    private static List<String> read(Path path) throws IOException {
        return Files.readAllLines(path, UTF_8);
    }

    public final void performWriteAfterAppend() throws IOException {

        final Path path = testdata.toAbsolutePath();

        final ASimpleParser parser = new ASimpleParser(path);
        parser.append(CONTENTS).append(CONTENTS).write(CONTENTS).append(CONTENTS).write(CONTENTS).flush();
        final String readFromFile = parser.getContents();
        assertThat(readFromFile, isA(String.class));
        assertThat(readFromFile, equalTo(CONTENTS));
        assertFalse(parser.toString().isEmpty());
        System.out.println(parser);
        System.out.println("Base directory " + testdata.getRoot() + " containsExactlyOnce "
                + listFilesNullSafe(testdata.getRoot().toFile()));
    }

    @AfterAll
    public static void makeSureToRemoveAllFiles() throws IOException {
        // if there are open files in there or an exception takes place @Rule
        // does not delete folder properly!
        System.out.println("Before deletion: " + listFilesNullSafe(testdata.getRoot().toFile()));
        // JDK8 compliant and symlink-safe
        Files.walkFileTree(testdata.getRoot(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
        System.out.println("Did remove stuff in temporary folder");
    }

}
