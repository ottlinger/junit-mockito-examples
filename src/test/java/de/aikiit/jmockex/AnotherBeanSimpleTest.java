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

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Theories.class)
public class AnotherBeanSimpleTest {

	@DataPoint
	public static final String name = "My";
	@DataPoint
	public static final String name1 = "name";
	@DataPoint
	public static final String name2 = "is";
	@DataPoint
	public static final String name3 = "unknown.";

	// Collections need a different annotation in order to be injected
	@DataPoints
	public static final Collection<String> collectedNames = Arrays.asList("one", "two", "three");

	@Theory
	public void verifyNameIsOkay(String aName) {
		System.out.printf("Name under test is '%s'%n", aName);
		assertEquals(aName, new AnotherBean(aName).getName());
	}

	@DataPoint
	public static String getName() {
		return "Er hieß Waldemar, weil es im Wald geschah :-)";
	}
}
