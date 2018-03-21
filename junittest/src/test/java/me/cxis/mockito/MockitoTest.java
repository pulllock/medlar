package me.cxis.mockito;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MockitoTest {

    @Test
    public void testMock() {
        List list = mock(List.class);
        when(list.get(0)).thenReturn(1);
        assertEquals(1, list.get(0));
    }

    @Test
    public void testMockSpy() {
        List list = new LinkedList();
        assertEquals(0, list.size());

        List spy = spy(list);

        when(spy.size()).thenReturn(100);
        assertEquals(100, spy.size());

        spy.add("test");
        assertEquals("test", spy.get(0));

        verify(spy).add("test");
    }

    @Test
    public void testForIOException() {
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello,").thenReturn("Mockito");
        String result = i.next() + " " + i.next();
        assertEquals("Hello, Mockito", result);

        doThrow(new NoSuchElementException()).when(i).next();
        i.next();
    }
}
