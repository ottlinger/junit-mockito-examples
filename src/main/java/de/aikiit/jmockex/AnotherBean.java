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

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Example bean with member and method.
 */
@AllArgsConstructor
@Data
public class AnotherBean {

    private String name;

    /**
     * Example method to perform something within the bean.
     */
    public void perform() {
        throw new IllegalStateException("You are not allowed to perform anything here.");
    }
}
