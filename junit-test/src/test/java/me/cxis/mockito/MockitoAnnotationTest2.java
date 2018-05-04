package me.cxis.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationTest2 {

    @Mock
    private List mockList;

    @Test
    public void testShortHand() {
        mockList.add(1);
        verify(mockList).add(1);
    }
}
