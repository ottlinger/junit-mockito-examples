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

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLConnection;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class MockTests {
	private static final int TIMEOUT = 4711;
	@Mock
	private URLConnection connection;

	@InjectMocks
	private UrlConnector connector;

	@Test
	public void injectionWorks() {
		assertNotNull(connector);
		assertEquals(connection, connector.getConnection());
	}

	@Test
	public void getTimeout() {
		// WHEN
		when(connection.getConnectTimeout()).thenReturn(TIMEOUT);

		// THEN
		assertEquals(TIMEOUT, connector.getTimeout());
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
		when(connection.getContent()).thenReturn(Lists.newArrayList(field1, field2, field3, field4));

		@SuppressWarnings("rawtypes")
		final ArgumentMatcher<List> matchesUUIDs = new ArgumentMatcher<List>() {
			@SuppressWarnings("unchecked")
			@Override
			public boolean matches(Object data) {
				if (data == null) {
					return false;
				}

				final List<UUID> usageData = (List<UUID>) data;
				return usageData.size() == 4
						&& usageData.containsAll(Lists.newArrayList(field1, field2, field3, field4));
			}
		};

		// THEN
		assertTrue(matchesUUIDs.matches(Lists.newArrayList(field1, field2, field3, field4)));

		assertFalse(matchesUUIDs.matches(null));
		assertFalse(matchesUUIDs.matches(Lists.newArrayList()));
		assertFalse(matchesUUIDs
				.matches(Lists.newArrayList(field1, field2, field3, field4, field1, field2, field3, field4)));
	}

}
