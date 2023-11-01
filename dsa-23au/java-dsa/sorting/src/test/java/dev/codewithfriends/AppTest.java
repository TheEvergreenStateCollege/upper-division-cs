package dev.codewithfriends;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testInsertionSort()
    {
        int[] testArray = {5,4,3,2,1,0};

        int[] result = App.insertionSort(testArray);

        // assertEquals(Integer.valueOf(0),  Integer.valueOf(result[0]));
        for(int i = 0; i < testArray.length; i++){
            assertEquals(Integer.valueOf(i),  Integer.valueOf(result[i]));
        }
    }

    public void testMergeSort(){
        int[] theArray = {9,6,12,23,78,1};
        int[] theResult = App.mergeSort(theArray);
        int[] expected = {1,6,9,12,23,78};
        assertArrayEquals(expected, theResult);
        assertArrayEquals(theResult, expected);
    }
}
