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

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ASimpleParserTest {

	// HINT: needs to be public
	@Rule
	public TemporaryFolder testdata = new TemporaryFolder();

	@Before
	public void showTestDataBaseDir() {
		assertNotNull(testdata);
		System.out.println("Base directory is " + testdata.getRoot());
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testASimpleParser() {
		assertNotNull(new ASimpleParser(null));
	}

	@Test
	public final void testWithArguments() {
		// assertNull(new ASimpleParser(null).asFile());
	}

	@After
	public void makeSureToRemoveAllFiles() throws IOException {
		// if there are open files in there @Rule does not delete them properly!
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
