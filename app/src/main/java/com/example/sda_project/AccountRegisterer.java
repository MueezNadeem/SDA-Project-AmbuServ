package com.example.sda_project;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class AccountRegisterer {

    private DatabaseClass myC=new DatabaseClass();
    private DatabaseHelper DB =myC.getInstance();

    public AccountRegisterer(){


    }
    public boolean ValidateCode(){
        return true;
    }

    public boolean CreateAccount(Patient PatientAcc){
        int Max=DB.getMaxAccID(); //ToDo : write query and code // DONE!
        int Max1=DB.getMaxPatID();

        PatientAcc.setPatientID(Max1);
        PatientAcc.SetAccID(Max);
        PatientAcc.SetAccType("C");
        AddPatientToDB(PatientAcc);
        return true;
    }

    private void AddPatientToDB(Patient PatientAcc){
        SQLiteDatabase db =DB.getWritableDatabase();
        ContentValues v1=new ContentValues();
        ContentValues v2=new ContentValues();
        //DB.AddUser(PatientAcc);
      //  String AddUser= "INSERT INTO \"main\".\"User\" (\"Name\", \"DOB\", \"CNIC\", \"Address\", \"PhoneNum\", \"EnrollDate\", \"UserName\", \"Password\", \"AccountID\", \"AccountType\") VALUES ('"+PatientAcc.getName()+"', '"+PatientAcc.getDOB().toString()+"', '"+PatientAcc.getCNIC()+"', '"+PatientAcc.getAddress()+"', '"+PatientAcc.getPhoneNum()+"', '"+PatientAcc.getEnrollDate().toString()+"', '"+PatientAcc.getUserName()+"', '"+PatientAcc.getPassword()+"', '"+PatientAcc.GetAccID()+"', 'C');";
        //String AddPatient="INSERT INTO \"main\".\"Patient\" (\"PatientID\", \"AccountID\") VALUES ('"+PatientAcc.getPatientID()+"', '"+PatientAcc.GetAccID()+"');";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String CurrDate=sdf.format(new Date());

        v1.put("Name",PatientAcc.getName());
        v1.put("DOB",PatientAcc.getDOB());
        v1.put("CNIC",PatientAcc.getCNIC());
        v1.put("Address",PatientAcc.getAddress());
        v1.put("PhoneNum",PatientAcc.getPhoneNum());
        v1.put("EnrollDate",CurrDate);
        v1.put("UserName",PatientAcc.getUserName());
        v1.put("Password",PatientAcc.getPassword());
        v1.put("AccountID",PatientAcc.GetAccID());
        v1.put("AccountType","C");
        db.insert("User",null,v1);

        v2.put("PatientID",PatientAcc.getPatientID());
        v2.put("AccountID",PatientAcc.GetAccID());
        db.insert("Patient",null,v2);

        db.close();
        // DB.ExecuteSQLQuery(AddUser);
     //   DB.ExecuteSQLQuery(AddPatient);
        //DB.AddPatient(PatientAcc); //ToDo: write query and code //DONE!

    }

    public void PromptCode(String VerificationCode){
                //ToDo: look for email code if possible
    }

    public boolean CheckEmployeeExistenceByUN(String userName) {
        return DB.CheckUNExistance(userName);
    }

    public boolean CheckEmployeeExistenceByCNIC(String cnic) {
        return  DB.CheckCNICExistance(cnic);
    }
}
