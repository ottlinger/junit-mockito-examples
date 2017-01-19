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

import com.google.common.base.Strings;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Helper to find occurrences of a pattern in a given String.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OccurrenceFinder {

	public static boolean containsExactlyOnce(String source, String marker) {
		return containsExactlyTimes(1, source, marker);
	}

	public static boolean containsExactlyTimes(int times, String source, String marker) {
		if (Strings.isNullOrEmpty(source) || Strings.isNullOrEmpty(marker)) {
			throw new IllegalArgumentException("No null arguments allowed");
		}
		return times == (source.length() - source.replace(marker, "").length()) / marker.length();
	}
}
