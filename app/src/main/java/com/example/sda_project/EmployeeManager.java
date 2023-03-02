package com.example.sda_project;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EmployeeManager {
    private DatabaseClass myC=new DatabaseClass();
    private DatabaseHelper DB =myC.getInstance();

    public EmployeeType CheckEmployeeType(String EmpID){
        String EmpFromDB=DB.EmployeeTypeString(EmpID);
        // can be done via Joins
        switch (EmpFromDB) {
            case "AD":
                return EmployeeType.Admin;
            case "MG":
                return EmployeeType.Manager;
            case "DP":
                return EmployeeType.Dispatcher;
            case "DR":
                return EmployeeType.Driver;
            default:
                return null;
        }
    }

    //TODO: Add Code
//    public void AddEmployeeToDB(Admin _ad){
//        // can be done via execSql
//    }
//    public void AddEmployeeToDB(Manager _mg){
//        //,, can be done via execSql
//    }
//    public void AddEmployeeToDB(Dispatcher _dp){
//        // can be done via execSql
//    }
//    public void AddEmployeeToDB(Driver _dr){
//        // can be done via execSql
//    }
//    public void AddEmployeeToDB(Worker _wr){
//        // can be done via execSql
//    }

    public  void AddEmployeeToDB(Employee Emp){
        String nextAccID=String.valueOf(DB.getMaxAccID());
        Date CurrDateFormat=new Date();
        SQLiteDatabase db=DB.getWritableDatabase();
        //String cmd= " INSERT INTO \"main\".\"User\" (\"Name\", \"DOB\", \"CNIC\", \"Address\", \"PhoneNum\", \"EnrollDate\", \"UserName\", \"Password\", \"AccountID\", \"AccountType\") VALUES ('"+Emp.getName()+"', '"+Emp.getDOB()+"', '"+Emp.getCNIC()+"', '"+Emp.getAddress()+"', '"+Emp.getPhoneNum()+"', '"+Emp.getEnrollDate()+"', '"+Emp.getUserName()+"', '"+Emp.getPassword()+"', '"+nextAccID+"', 'E'); ";
        Emp.SetAccID(Integer.parseInt( nextAccID ));
        Emp.setEmployeeID(   DB.getMaxEmpID() );
        String EType=null;
        if (Emp.getEmpType()==EmployeeType.Admin){
            EType="AD";
        }else if(Emp.getEmpType()==EmployeeType.Manager) {
            EType="MG";
        }else if(Emp.getEmpType()==EmployeeType.Dispatcher) {
            EType="DP";
        }else if(Emp.getEmpType()==EmployeeType.Driver) {
            EType="DR";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String CurrDate=sdf.format(new Date());
        ContentValues values=new ContentValues();
        values.put("Name",Emp.getName());
        values.put("DOB",Emp.getDOB());
        values.put("CNIC",Emp.getCNIC());
        values.put("Address",Emp.getAddress());
        values.put("PhoneNum",Emp.getPhoneNum());
        values.put("EnrollDate",CurrDate);
        values.put("UserName",Emp.getUserName());
        values.put("Password",Emp.getPassword());
        values.put("AccountID",Emp.GetAccID());
        values.put("AccountType","E");

        db.insert("User",null,values);
        //String cmd2= " INSERT INTO \"main\".\"Employee\" (\"AccountID\", \"EmpID\", \"EmpType\") VALUES ('"+Emp.GetAccID()+"', '"+Emp.getEmpType()+"', '"+EType+"'); ";
        //DB.ExecuteSQLQuery(cmd);
        ContentValues values2=new ContentValues();
        values2.put("AccountID",Emp.GetAccID());
        values2.put("EmpID",Emp.getEmployeeID());
        values2.put("EmpType",EType);
        db.insert("Employee",null,values2);
        //DB.ExecuteSQLQuery(cmd2);

        ContentValues v3=new ContentValues();
        if (Emp.getEmpType()==EmployeeType.Admin){
            v3.put("EmpID",Emp.getEmployeeID());
            v3.put("AdminID",DB.getMaxAdminID());
            db.insert("Admin",null,v3);
        }else if(Emp.getEmpType()==EmployeeType.Manager) {
            v3.put("EmpID",Emp.getEmployeeID());
            v3.put("ManagerID",DB.getMaxManagerID());
            db.insert("Manager",null,v3);
        }else if(Emp.getEmpType()==EmployeeType.Dispatcher) {
            v3.put("EmpID",Emp.getEmployeeID());
            v3.put("DispatcherID",DB.getMaxDispID());
            db.insert("Dispatcher",null,v3);
        }else if(Emp.getEmpType()==EmployeeType.Driver) {
            v3.put("EmpID",Emp.getEmployeeID());
            v3.put("DriverID",DB.getMaxDriverID());
            v3.put("LicenseID", ThreadLocalRandom.current().nextLong(1000000,9999999));
            v3.put("Availability",1);
            db.insert("Driver",null,v3);
        }

        db.close();
    }

    public boolean CheckEmployeeExistence(String  EmpID){
        return  DB.CheckEmployee(EmpID);
    }

    public void DeleteEmp(String EmpID) {
        String cmd;
        if (CheckEmployeeExistence(EmpID)){
            SQLiteDatabase db=DB.getWritableDatabase();
//            cmd="Delete from Employee where EmpID="+EmpID;
//            DB.ExecuteSQLQuery(cmd);
            String Uname=DB.getEmpUname(EmpID);
            String type = DB.getEmpType(Uname);
            if (Objects.equals(type, "AD")){
                db.delete("Admin","EmpID=?",new String[]{EmpID});;
            }else if(Objects.equals(type, "MG")) {
                db.delete("Manager","EmpID=?",new String[]{EmpID});
            }else if(Objects.equals(type, "DP")) {
                db.delete("Dispatcher","EmpID=?",new String[]{EmpID});
            }else if(Objects.equals(type, "DR")) {
                db.delete("Driver","EmpID=?",new String[]{EmpID});
            }

            db.delete("Employee","EmpID=?",new String[]{EmpID});
            db.delete("User","UserName=?",new String[]{Uname});
            db.close();
        }
    }

    public String getEmployeeUName(String EmpID) {
        return DB.getEmpUname(EmpID);
    }


    public Employee getEmp(String getUname) {
        return DB.getEditableEmp(getUname);
    }

    public void UpdateEmployee(Employee tempEmp) {
        SQLiteDatabase db=DB.getWritableDatabase();
        ContentValues values =new ContentValues();
//        String cmd="Update User set Name="+tempEmp.getName()+", CNIC="+tempEmp.getCNIC()+", PhoneNum="+tempEmp.getPhoneNum()+",Address="+tempEmp.getAddress()+"where UserName="+tempEmp.getUserName();
//        DB.ExecuteSQLQuery(cmd);

        values.put("Name",tempEmp.getName());
        values.put("CNIC",tempEmp.getCNIC());
        values.put("PhoneNum",tempEmp.getPhoneNum());
        values.put("Address",tempEmp.getAddress());
        db.update("User",values,"UserName=?",new String[]{tempEmp.getUserName()});
    }


    public boolean CheckEmployeeExistenceByUN(String userName) {
        return DB.CheckUNExistance(userName);
    }

    public boolean CheckEmployeeExistenceByCNIC(String cnic) {
        return  DB.CheckCNICExistance(cnic);
    }
}
