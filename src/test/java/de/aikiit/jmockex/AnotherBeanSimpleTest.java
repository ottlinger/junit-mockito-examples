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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnotherBeanSimpleTest {

    private static List<String> names = Arrays.asList("My", "name", "is", "unknown");

    @ParameterizedTest
    @MethodSource("getTestNames")
    @FieldSource("names")
    public void verifyNameIsOkay(String aName) {
        System.out.printf("Name under test is '%s'%n", aName);
        assertEquals(aName, new AnotherBean(aName).getName());
    }

    private static Collection<String> getTestNames() {
        return Arrays.asList("one", "two", "three");
    }

    private static String getTestName() {
        return "Er hieß Waldemar, weil es im Wald geschah :-)";
    }
}
