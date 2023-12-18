package com.devincompany.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */

    @Test
    public void shouldAnswerWithTrue() throws FileNotFoundException, IOException, ParseException
    {
        String[] args = {""};
        MessagesReader.main(args);
        
    }
    
    @Test
    public void addMessageTest() throws FileNotFoundException, IOException, ParseException 
    {
        
    }
}
