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


import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.hamcrest.collection.IsMapContaining.hasValue;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsEqual.equalTo;

// a bit picky concerning the correct import when putting more conditions into one, but readable
public class LookupServiceTest {

    @Test
    public void lookupStuff() {
        final Map<UUID, String> results = new LookupService().getNames("magic");
        assertThat(results.keySet(), hasSize(equalTo(2)));
        assertThat(results, hasEntry(UUID.fromString("f3c028fb-c9c0-4f07-8ed5-09259de0d910"), "Alfons"));
        assertThat(results, hasValue("Alfons"));
        assertThat(results, allOf(hasKey(UUID.fromString("f3c028fb-c9c0-4f07-8ed5-09259de0d910")),
                hasKey(UUID.fromString("bf457d0c-b952-4f93-aaf0-5ccca62c3156"))));
    }

    @Test
    public void lookupWithWrongMagicFilter() {
        final Map<UUID, String> results = new LookupService().getNames(null);
        assertThat(results.keySet(), is(empty()));
    }
}
