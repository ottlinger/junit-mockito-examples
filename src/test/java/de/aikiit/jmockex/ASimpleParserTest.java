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
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.common.base.Charsets;

public class ASimpleParserTest {

	private static String CONTENTS = "This is a test with German umlauts or not, äöüß!";

	// HINT: needs to be public
	@Rule
	public TemporaryFolder testdata = new TemporaryFolder();

	@Before
	public void showTestDataBaseDir() {
		assertNotNull(testdata);
		System.out.println("Base directory is " + testdata.getRoot());
	}

	@Test(expected = IllegalArgumentException.class)
	public final void nullArguments() {
		assertNotNull(new ASimpleParser(null));
	}

	@Test
	public final void performWrite() throws IOException {
		final File writeExample = testdata.newFile();

		final ASimpleParser parser = new ASimpleParser(writeExample.toPath());
		parser.write(CONTENTS).flush();
		final String readFromFile = parser.getContents();
		assertThat(readFromFile, isA(String.class));
		assertThat(readFromFile, equalTo(CONTENTS));
		
	}

	public static List<String> read(Path path) throws IOException {
		return Files.readAllLines(path, Charsets.UTF_8);
	}

	@After
	public void makeSureToRemoveAllFiles() throws IOException {
		// if there are open files in there or an exception takes place @Rule
		// does not delete folder properly!
		if (testdata != null) {
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
			System.out.println("Manually removed stuff in temporary folder.");
		} else {
			System.out.println("Already deleted by JUnit-RULE.");
		}
	}

}
