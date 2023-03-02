package com.example.sda_project;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class AttendanceMarker {
   private DatabaseClass myC=new DatabaseClass();
   private DatabaseHelper DB =myC.getInstance();

    public boolean CheckEmployeeExistence(String EmpID){
        return DB.CheckEmployee(EmpID);
    }


    public boolean IsEmployeeCheckedIn(String EmpID){
        return DB.CheckEmployeeTimedIn(EmpID);

    }

    public String CheckAttendanceStatus(String EmpID){
        if( !DB.AttendanceStatus(EmpID)){
           return  "Absent";
        }else{
            return "Present";
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void AddTimeIn(String EmpID){
        DB.AddTimeIn(EmpID);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void AddTimeOut(String EmpID){
        DB.AddTimeOut(EmpID);
    }
}
