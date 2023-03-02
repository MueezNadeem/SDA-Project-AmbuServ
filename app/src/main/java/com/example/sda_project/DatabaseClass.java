package com.example.sda_project;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;


public class DatabaseClass {
    private static DatabaseHelper myDB=null;
    private static Context CurrContext=null;

    public DatabaseClass(Context _context){
        CurrContext=_context;
        if (myDB==null){
            myDB=new DatabaseHelper(CurrContext);
            try {
                myDB.createDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            myDB.openDataBase();
        }
    }
    public DatabaseClass(){
        Log.d("Instantiate", "DatabaseClass: ");
    }
    public static DatabaseHelper getInstance() {
            return myDB;
    }
}
