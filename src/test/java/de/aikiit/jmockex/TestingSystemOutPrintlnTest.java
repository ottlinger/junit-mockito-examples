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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.verify;
import org.junit.platform.runner.JUnitPlatform;

import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestingSystemOutPrintlnTest {

	@Mock
	private PrintStream console;

	@Test
	public void ensureHelpIsPrinted() {
		System.setOut(console);
		LookupService.help();
		assertEquals(console, System.out);
		verify(console).println(contains("example"));
		verify(console).println(startsWith("This is an"));
	}
}
