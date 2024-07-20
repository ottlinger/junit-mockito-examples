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

import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
* Example service that filters for a given filter and prints help information. 
*/
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public final class LookupService {
        /**
        * Return mapped ids,names for a given filter.
        * @param filter filter for specific names.
        * @return mapped results id,name for the given filter query.
        */
	public Map<UUID, String> getNames(String filter) {
		final Map<UUID, String> results = Maps.newHashMap();
		if ("magic".equals(filter)) {
			results.put(UUID.fromString("f3c028fb-c9c0-4f07-8ed5-09259de0d910"), "Alfons");
			results.put(UUID.fromString("bf457d0c-b952-4f93-aaf0-5ccca62c3156"), "Zitterbacke");
		}
		return results;
	}

        /**
        * Just print out help information to the console.
        */
	public static void help() {
		System.out.println("This is an example service implementation used to demonstrate testing :D");
	}
}
