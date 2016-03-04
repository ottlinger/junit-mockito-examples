package de.aikiit.jmockex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class RemoteServiceCallerTest {

    @Mock
    private RemoteServiceCaller.RemoteService remoteService;

    @InjectMocks
    private RemoteServiceCaller caller = new RemoteServiceCaller();

    @Test
    public void argumentCaptor() {
        assertNotNull(caller);
        // TODO to be continued :-D

    }
}
