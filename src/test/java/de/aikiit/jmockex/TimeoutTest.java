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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.model.TestTimedOutException;

public class TimeoutTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(1);

    @Ignore
    // @Test
    // This test will fail if enabled since it reaches the timeout configured above.
    public void willWaitForGlobalTimeout() {
        while (true) ;
    }

    @Test
    public void nothingHappensIfTimeoutIsNotHit() {
        System.out.println("Good morning everybody");
    }
}
