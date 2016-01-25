/**
 * Test Examples,  Copyright (C) 2016  P.Ottlinger

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 */
package de.aikiit.jmockex;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.common.base.Charsets;

public class ASimpleParserTest {

	private static String CONTENTS = "This is a test with German umlauts or not, äöüß!";

	// HINT: needs to be public, @Rule if not static
	@ClassRule
	public static TemporaryFolder testdata = new TemporaryFolder();

	@Before
	public void showTestDataBaseDir() throws IOException {
		assertNotNull(testdata);
		System.out.println("Base directory is " + testdata.getRoot() + " containing "
				+ Arrays.asList(testdata.getRoot().listFiles()));

	}

	@Test(expected = IllegalArgumentException.class)
	public final void nullArguments() {
		assertNotNull(new ASimpleParser(null));
	}

	@Test
	public final void performWrite() throws IOException {

		final Path path = testdata.newFile().toPath();

		final ASimpleParser parser = new ASimpleParser(path);
		parser.write(CONTENTS).flush();
		final String readFromFile = parser.getContents();
		assertThat(readFromFile, isA(String.class));
		assertThat(readFromFile, equalTo(CONTENTS));
		System.out.println(parser);
		System.out.println(
				"Base directory " + testdata.getRoot() + " contains " + Arrays.asList(testdata.getRoot().listFiles()));
	}
	
	@Ignore("Not yet working")
	public void intentionallyLeftBlank() {
		throw new RuntimeException("You should be ignored!");
	}

	@Test
	public void writeFluentlyAndReadContents() throws IOException {
		final Path path = testdata.newFile().toPath();

		final ASimpleParser parser = new ASimpleParser(path);

		parser.append(CONTENTS).append(CONTENTS).append(CONTENTS).flush();
		final List<String> flushedContents = read(path);
		assertThat(flushedContents, hasItems(CONTENTS + CONTENTS + CONTENTS));
		System.out.println(parser);
	}

	public static List<String> read(Path path) throws IOException {
		return Files.readAllLines(path, Charsets.UTF_8);
	}

	@Test
	public final void performWriteAfterAppend() throws IOException {

		final Path path = testdata.newFile().toPath();

		final ASimpleParser parser = new ASimpleParser(path);
		parser.append(CONTENTS).append(CONTENTS).write(CONTENTS).append(CONTENTS).write(CONTENTS).flush();
		final String readFromFile = parser.getContents();
		assertThat(readFromFile, isA(String.class));
		assertThat(readFromFile, equalTo(CONTENTS));
		System.out.println(parser);
		System.out.println(
				"Base directory " + testdata.getRoot() + " contains " + Arrays.asList(testdata.getRoot().listFiles()));

	}

	@AfterClass
	public static void makeSureToRemoveAllFiles() throws IOException {
		// if there are open files in there or an exception takes place @Rule
		// does not delete folder properly!
		if (testdata != null) {
			System.out.println("Before deletion: " + Arrays.asList(testdata.getRoot().listFiles()));
			// JDK8 compliant and symlink-safe
			Files.walkFileTree(Paths.get(testdata.getRoot().toURI()), new SimpleFileVisitor<Path>() {
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
		} else {
			System.out.println("Already deleted by JUnit-RULE.");
		}
	}

}
