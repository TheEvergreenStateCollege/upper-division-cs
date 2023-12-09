package dev.codewithfriends;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.List;

public class ArrayWrapperTest {

    String[] s1 = {"Eggs", "Bread", "Peanut Butter", "Butter"};

    
    @Test
    public void testAdd(){
        List myList = new ArrayWrapper<String>(10);
        
        for (int i = 0; i < s1.length; i++){
            myList.add(s1[i]);
        }
        
        // instert broccoli at myList [index 2]
        int insertIndex = 2;
        
        assertEquals("Eggs",myList.get(0));
        assertEquals("Bread",myList.get(1));
        assertEquals("Peanut Butter",myList.get(insertIndex));
        assertEquals("Butter",myList.get(3));
        
        myList.add(insertIndex, "Broccoli");

        assertEquals("Eggs",myList.get(0));
        assertEquals("Bread",myList.get(1));
        assertEquals("Broccoli",myList.get(insertIndex));
        assertEquals("Peanut Butter",myList.get(insertIndex+1));
        assertEquals("Butter",myList.get(insertIndex+2));
    }

    Book b1 = new Book ("Some title", "some author");

    public void insertShifts ()  {
        List myList = new ArrayWrapper<Book>(10);
        assertEquals(0, myList.size());
        myList.add(b1);
        assertEquals(1,myList.size());
    }
}