package com.example.sda_project;

import static java.security.AccessController.getContext;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class AmbulanceManager {
    private DatabaseClass myC=new DatabaseClass();
    private DatabaseHelper DB =myC.getInstance();

    public boolean CheckAmbulanceExistence(String AmbulanceID)
    {
        return DB.CheckAmbulanceExistence(AmbulanceID);
            //Todo: add Code // DONE!
    }
    public boolean SetStatus(String AmbulanceID,AmbulanceStatus _status){
        SQLiteDatabase db=DB.getWritableDatabase();
        ContentValues v1=new ContentValues();
        String query=null;
        if (CheckAmbulanceExistence(AmbulanceID)){
        String statusString=null;
            switch (_status){
            case Ready: statusString="RY";
                        break;
            case Reserved: statusString="IR";
                        break;
            case Maintainance: statusString="IM";
                    break;

        }
        query="UPDATE Ambulance Set CurrentStatus='"+statusString+"' where AmbulanceID="+AmbulanceID;
       // DB.ExecuteSQLQuery(query);

            v1.put("CurrentStatus",statusString);
            db.update("Ambulance",v1,"AmbulanceNo=?",new String[]{AmbulanceID});
            db.close();
            return true;
            //Todo: add queries //DONE!
        }else {
          return false;
        }
    }
    public int GetNextID(){
        int ID=0;
        ID= DB.getMaxAmbID();
        return ID;
    }
    public void AddAmbulancetoDB(@NonNull Ambulance Amb){
        //ToDo : add query,, can be done via execSql //DONE!
        SQLiteDatabase db1 = DB.getWritableDatabase();
        ContentValues values = new ContentValues();
        String statusString=null;
        switch (Amb.getCurrentStatus()){
            case Ready: statusString="RY";
                break;
            case Reserved: statusString="IR";
                break;
            case Maintainance: statusString="IM";
                break;

        }
        values.put("AmbulanceID", Amb.getAmbulanceID());
        values.put("AmbulanceNo", Amb.getAmbulanceNo());
        values.put("CurrentStatus", statusString);

        // after adding all values we are passing
        // content values to our table.
        db1.insert("Ambulance", null, values);

        // at last we are closing our
        // database after adding database.
        db1.close();
        //String query="INSERT INTO \"main\".\"Ambulance\" (\"AmbulanceID\", \"AmbulanceNo\", \"CurrentStatus\") VALUES ('"+Amb.getAmbulanceID()+"', '"+Amb.getAmbulanceNo()+"', '"+statusString+"');";
        //DB.ExecuteSQLQuery(query);
    }


    public void DeleteAmbulance(String AmbulanceNo) {
        //ToDo: add query,, can be done via execSql // DONE!
        //if (CheckAmbulanceExistence(AmbulanceNo)){
        SQLiteDatabase db = DB.getWritableDatabase();

        db.delete("Ambulance", "AmbulanceNo=?", new String[]{AmbulanceNo});
        db.close();
        //String query="DELETE from Ambulance where AmbulanceNo="+AmbulanceNo;
        //DB.ExecuteSQLQuery(query);
        //}else {
        //Do nothing
        //}
    }


}
