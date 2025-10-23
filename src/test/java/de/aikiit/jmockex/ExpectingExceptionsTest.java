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
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpectingExceptionsTest {

    @Test
    void veryOldWay() {
        try {
            new AnotherBean("example").perform();
            fail("Not expected");
        } catch (final IllegalStateException ise) {
            // expected
        }
    }

    @Test
    void newWay() throws IllegalStateException {
        Exception expextedException = assertThrows(IllegalStateException.class, () -> {
            new AnotherBean("example").perform();
        });
        assertEquals("You are not allowed to perform anything here.", expextedException.getMessage());
    }

    @Test
    void noException() {
        assertDoesNotThrow(() -> {
            System.out.println("noExpection");
        });
    }


}
