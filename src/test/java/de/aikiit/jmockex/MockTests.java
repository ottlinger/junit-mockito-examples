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
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockTests {
    private static final int TIMEOUT = 4711;
    @Mock
    private URLConnection connection;

    @InjectMocks
    private UrlConnector connector;

    @Test
    public void injectionWorks() {
        assertThat(connector).isNotNull();
        assertThat(connection).isEqualTo(connector.getConnection());
    }

    @Test
    public void getTimeout() {
        // WHEN
        when(connection.getConnectTimeout()).thenReturn(TIMEOUT);

        // THEN
        assertThat(TIMEOUT).isEqualTo(connector.getTimeout());
        verify(connection).getConnectTimeout();
    }

    // useful verification calls
    @Test
    public void closeOutputStreamOnClose() throws IOException {
        final OutputStream mock = mock(OutputStream.class);
        final OutputStreamWriter osw = new OutputStreamWriter(mock);
        osw.close();
        verify(mock).close();
    }

    // write your own matcher
    @Test
    public void saveUsageWithRoutesAndFieldIdMerge() throws IOException {
        // WHEN
        final UUID field1 = UUID.randomUUID();
        final UUID field2 = UUID.randomUUID();
        final UUID field3 = UUID.randomUUID();
        final UUID field4 = UUID.randomUUID();

        @SuppressWarnings("rawtypes") final ArgumentMatcher<List> matchesUUIDs = new ArgumentMatcher<List>() {
            @SuppressWarnings("unchecked")
            public boolean matches(List data) {
                if (data == null) {
                    return false;
                }

                final List<UUID> usageData = (List<UUID>) data;
                return usageData.size() == 4
                        && usageData.containsAll(Lists.newArrayList(field1, field2, field3, field4));
            }
        };

        // THEN
        assertThat(matchesUUIDs.matches(Lists.newArrayList(field1, field2, field3, field4))).isTrue();

        assertThat(matchesUUIDs.matches(null)).isFalse();
        assertThat(matchesUUIDs.matches(Lists.newArrayList())).isFalse();
        assertThat(matchesUUIDs
                .matches(Lists.newArrayList(field1, field2, field3, field4, field1, field2, field3, field4))).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void mockEmptyMethodException() {
        UrlConnector spy = spy(UrlConnector.class);
        doThrow(new IllegalArgumentException("Just for testing")).when(spy).getConnection();
        assertThat(spy.getConnection()).isNull();
    }

}
