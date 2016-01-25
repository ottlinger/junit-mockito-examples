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
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ASimpleParserTest {

	@Test
	public final void testASimpleParser() {
		assertNotNull(new ASimpleParser(null));
	}

	@Test
	public final void testAsFile() {
		assertNull(new ASimpleParser(null).asFile());
	}

}
