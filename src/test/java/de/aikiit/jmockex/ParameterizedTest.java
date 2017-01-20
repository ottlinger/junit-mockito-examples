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

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedTest {
    private int number;
    private int expectation;

    @Parameterized.Parameters
    public static List expectations() {
        return Arrays.asList(new Object[][]{{1,12}, {2,13}});
    }

    public ParameterizedTest(int input, int  output) {
        this.number = input;
        this.expectation = output;
    }

    @Test
    public void ensureCalculationIsCorrect() {
        assertEquals(expectation, addElevenToIt(number));
    }

    public static int addElevenToIt(int number) {
        return 11 + number;
    }

}
