package com.example.a00848218.myapplication.mydb;
import com.example.a00848218.myapplication.mydb.*;
public class DataStorageImp implements IDataStore
{
   public String state = null;
   public void saveState(String content)
   {

      this.state = content;
   }
   public String getState() 
   {
      return state;
   }
}