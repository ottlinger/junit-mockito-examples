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

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * https://github.com/junit-team/junit/wiki/Exception-testing
 */
public class ExpectingExceptionsTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test(expected = IllegalStateException.class)
	public void theOldWay() {
		new AnotherBean("example").perform();
	}

	@Test
	public void veryOldWay() {
		try {
			new AnotherBean("example").perform();
			fail("Not expected");
		} catch (final IllegalStateException ise) {
			// expected
		}
	}

	@Test
	public void newWay() throws IllegalStateException {
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage("You are not allowed to perform anything here.");
		thrown.expectMessage(containsString("You are not allowed to "));
		// Enable to see nice error messages
		// thrown.expect(hasProperty("name", is("example")));

		// put your code at the end
		new AnotherBean("example").perform();
	}

}
