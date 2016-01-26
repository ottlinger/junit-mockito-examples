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

import org.junit.Ignore;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class AnotherBeanTest {

    @DataPoints
    public static final List<String> names = Arrays.asList("A", "B", "C");

    @Theory
    public void checkAllNames(String name) {
        System.out.println(String.format("Testing with '%s'", name));
        assertEquals(name, new AnotherBean(name).getName());

        // the hamcrest way:
        assertThat(new AnotherBean(name), hasProperty("name", is(name)));
    }

    @Theory
    @Ignore("Enable to see how this test throws an error and theories log their expectation failures")
    public void checkAllNamesWithError(String name) {
        System.out.println(String.format("Testing with '%s'", name));
        assertEquals("WWW", new AnotherBean(name).getName());
    }

}
