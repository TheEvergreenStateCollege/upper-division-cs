package com.pswishcorp.app;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;


public class HeapSortTest {

    @Test
    public void testHeapSort() {
        Integer[] arr = { 12, 4, 11, 3, 1, 9, 6 };
        Integer[] expectedSorted = { 1, 3, 4, 6, 9, 11, 12 };

        HeapSort.heapsort(arr);
        assertNotNull(arr);
        assertNotNull(expectedSorted);
        assertArrayEquals(expectedSorted, arr); 
        System.out.println(Arrays.toString(arr));
    }
}
