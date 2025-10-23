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
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

public class TimeoutTest {

    @Disabled
    @Test
    // This test will fail if enabled since it reaches the timeout configured above.
    public void willWaitForGlobalTimeout() {
        assertTimeout(Duration.ofMillis(1), () -> {
            while (true) ;
        });
    }

    @Test
    public void nothingHappensIfTimeoutIsNotHit() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            System.out.println("Good morning everybody");
        });
    }
}
