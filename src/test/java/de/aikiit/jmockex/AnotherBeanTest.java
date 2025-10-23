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

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AnotherBeanTest {

    // needs to be a list, map is not supported here
    public static final List<String> names = Arrays.asList("A", "B", "C");

    @ParameterizedTest
    @FieldSource("names")
    public void checkAllNames(String name) {
        System.out.printf("Testing with '%s'%n", name);
        assertEquals(name, new AnotherBean(name).getName());

        // the hamcrest way:
        assertThat(new AnotherBean(name), hasProperty("name", is(name)));
    }

    @Disabled("Enable to see how this test throws an error and theories log their expectation failures")
    @ParameterizedTest
    @FieldSource("names")
    public void checkAllNamesWithError(String name) {
        System.out.printf("Testing with '%s'%n", name);
        assertEquals("WWW", new AnotherBean(name).getName());
    }

}
