package me.cxis.mockito;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

public class MockitoAnnotationTest1 {

    @Mock
    private List mockList;

    public MockitoAnnotationTest1() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShortHand() {
        mockList.add(1);
        verify(mockList).add(1);
    }

}
