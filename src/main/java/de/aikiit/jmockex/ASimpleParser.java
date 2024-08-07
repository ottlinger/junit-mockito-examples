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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.common.base.Charsets;

/**
 * This class is just an example, no real useful implementation.
 */
public class ASimpleParser {

	private final Path path;
	private StringBuilder contents;

        /**
        * Constructor to initialize with a given path.
        * @param path path to work on. Must not be {@code null}.
        * @throws IllegalArgumentException in case {@code null} is passed.
        */ 
	public ASimpleParser(Path path) {
		if (path == null) {
			throw new IllegalArgumentException("No null arguments allowed.");
		}

		this.path = path;
		this.contents = new StringBuilder();
	}

        /**
        * @return underlying contents.
        */
	public String getContents() {
		return contents.toString();
	}

        /**
        * Concatenated way to write/reset the underlying contents.
        * @param input resets the underlying contents with the given contents.
        * @return this for concatenated access.
        */
	public ASimpleParser write(String input) {
		contents = new StringBuilder(input);
		return this;
	}

        /**
        * Concatenated way to append contents to the underlying contents.
        * @param input appends the given input to the underlying contents.
        * @return this for concatenated access.
        */
	public ASimpleParser append(String input) {
		contents.append(input);
		return this;
	}

        /**
        * Flushes/Saves the contents to the underlying path.
        * @throws IOException in case of IO errors.
        */
	public void flush() throws IOException {
		Files.write(path, contents.toString().getBytes(Charsets.UTF_8));
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ASimpleParser [path=");
		builder.append(path);
		builder.append(", contents=");
		builder.append(contents);
		builder.append("]");
		return builder.toString();
	}
}
