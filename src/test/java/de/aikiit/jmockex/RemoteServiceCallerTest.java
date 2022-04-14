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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class RemoteServiceCallerTest {

    @Mock
    private RemoteServiceCaller.RemoteService remoteService;

    private final RemoteServiceCaller caller = new RemoteServiceCaller();

    @Test
    public void argumentCaptor() {
        assertNotNull(caller);
        assertNotNull(remoteService);


        final long timestamp = System.currentTimeMillis();
        String converted = caller.serviceCall(timestamp);

        assertEquals("alonglongway:"+timestamp, converted);
        // TODO to be continued :-D

    }
}
