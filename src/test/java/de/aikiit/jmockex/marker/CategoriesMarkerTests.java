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
package de.aikiit.jmockex.marker;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * https://github.com/junit-team/junit/wiki/Categories
 */
public class CategoriesMarkerTests {

	@Category(UnitTestsMarker.class)
	// since it's taken by the normal runner as well
	@Test(expected = AssertionError.class)
	public void justFail() {
		fail("This should not be tested here.");
	}

	@Category(IntegrationTestsMarker.class)
	@Test
	public void thisIsAnIntegrationTest() {
		System.out.println("This is an integrationTest :-D");
	}

	@RunWith(Categories.class)
	@Categories.IncludeCategory(IntegrationTestsMarker.class)
	@Categories.ExcludeCategory(UnitTestsMarker.class)
	@Suite.SuiteClasses({ CategoriesMarkerTests.class })
	public class CategoriesMarkerTestsSuite {
		// what will we see :-D
	}

}
