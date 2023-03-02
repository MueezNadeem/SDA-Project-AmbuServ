package com.example.sda_project;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class AvailabilityChecker {
    private DatabaseClass myC=new DatabaseClass();
    private DatabaseHelper DB =myC.getInstance();

    public boolean CheckAvailability(String DriverID,String AmbulanceID){
       return DB.CheckDriverAndAmbulance(DriverID,AmbulanceID);
    }


    public void AssignToTask(String TaskID, String DriverID, String AmbID) {
        SQLiteDatabase db=DB.getWritableDatabase();
        ContentValues v1=new ContentValues();
        v1.put("DriverID",DriverID);
        v1.put("AmbulanceID",AmbID);
        db.update("Tasks",v1,"TaskID=?",new String[]{TaskID});

        ContentValues v2=new ContentValues();
        ContentValues v3=new ContentValues();
        v2.put("Availability",0);
        v3.put("CurrentStatus","IR");

        db.update("Ambulance",v3,"AmbulanceID=?",new String[]{AmbID});
        db.update("Driver",v2,"DriverID=?",new String[]{DriverID});



        db.close();
        //    DB.ExecuteSQLQuery(cmd);
    }
}
