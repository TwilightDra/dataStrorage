package com.example.a00848218.myapplication;

import org.junit.Test;
import com.example.a00848218.myapplication.mydb.DataStorageImp;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
//    public void
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void dataStorage_isWorking() throws Exception{
        DataStorageImp db = new DataStorageImp();
        db.saveState("Testing");
        assertEquals("Testing", db.getState());
    }
}