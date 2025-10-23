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

/**
 * Example class that calls an underlying remote service with a parameter.
 */
public class RemoteServiceCaller {

    private final RemoteService remoteService = new RemoteService();

    /**
     * Example service class that will be mocked during tests.
     */
    // not final because it can only be mocked if not final :-)
    public static class RemoteService {
        /**
         * Converts a given value.
         *
         * @param value value to be converted.
         * @return converted value
         */
        public String convert(long value) {
            return "alonglongway:" + value;
        }
    }

    /**
     * Routes the given timestamp to the underlying service for conversion.
     *
     * @param timestamp timestamp to convert.
     * @return converted timestamp
     */
    public String serviceCall(long timestamp) {
        return remoteService.convert(timestamp);
    }

}
