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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OccurrenceFinderTest {

    @Test
    public void nullArgs() {
        try {
            OccurrenceFinder.containsExactlyOnce(null, null);
            fail("Should throw an exception because arguments are invalid");
        } catch (final IllegalArgumentException iae) {
            // intended
        }

        try {
            OccurrenceFinder.containsExactlyOnce(null, "asdasdasd");
            fail("Should throw an exception because arguments are invalid");
        } catch (final IllegalArgumentException iae) {
            // intended
        }

        try {
            OccurrenceFinder.containsExactlyOnce("asdasdasd", null);
            fail("Should throw an exception because arguments are invalid");
        } catch (final IllegalArgumentException iae) {
            // intended
        }
    }

    @Test
    public void validOccurrences() {
        assertTrue(OccurrenceFinder.containsExactlyTimes(3, "abcabcab", "ab"));
        assertTrue(OccurrenceFinder.containsExactlyTimes(3, "abcabcab", "a"));
        assertFalse(OccurrenceFinder.containsExactlyOnce("abcabcab", "ab"));
    }
}
