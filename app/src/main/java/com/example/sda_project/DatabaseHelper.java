package com.example.sda_project;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    String DB_PATH = null;
    private static String DB_NAME = "ProjectDB3";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 10);
        this.myContext = context;
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DB_PATH);
    }


    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
        } else {
            this.getWritableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[10];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();

            }
    }

    public boolean CheckUserName(String Uname, String Pword) {
        String command = "Select UserName,Password from User where Username= '" + Uname + "' AND Password ='" + Pword + "'";

        Cursor cs = myDataBase.rawQuery(command, null);
        if (cs.getCount() == 0) {
            return false;
        } else {
            return true;
        }

    }


    public boolean CheckEmployee(String EmpID) {
        String command = "Select * from Employee where EmpID=" + EmpID;
        Cursor cs = myDataBase.rawQuery(command, null);
        if (cs.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean CheckEmployeeTimedIn(String EmpID) {
        String command = "Select TimeIn from Attendance where EmpID=" + EmpID;
        Cursor cs = myDataBase.rawQuery(command, null);
        if(cs.getCount()==0){
            return  false;
        }
        cs.moveToFirst();
        String TimedIN = cs.getString(0);

        if (TimedIN == "") {
            return false;
        } else {
            return true;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void AddTimeIn(String EmpID) {
        LocalDate CurrentDate = LocalDate.now();
        LocalTime CurrentTime = LocalTime.now();
     //   Time SQLTime = Time.valueOf(CurrentTime.toString());
       // Date SQLDate = Date.valueOf(CurrentDate.toString());
        //String command = "Insert into Attendance (EmpID,Date,TimeIn,TimeOut,Status) VALUES (" + EmpID + "," + SQLDate + "," + SQLTime + ",'',A";
        //myDataBase.execSQL(command);
        SQLiteDatabase db=getWritableDatabase();
        ContentValues v1=new ContentValues();
        v1.put("EmpID",EmpID);
        v1.put("Date",CurrentDate.toString());
        v1.put("TimeIn",CurrentTime.toString());
        v1.put("TimeOut","");
        v1.put("Status","A");
        db.insert("Attendance",null,v1);
        db.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void AddTimeOut(String EmpID) {
        LocalTime CurrentTime = LocalTime.now();
        //Time SQLTime = Time.valueOf(CurrentTime.toString());
        SQLiteDatabase db=getWritableDatabase();
        ContentValues v1=new ContentValues();
        v1.put("TimeOut",CurrentTime.toString());
        v1.put("Status","P");
        db.update("Attendance",v1,"EmpID=?",new String[]{EmpID});
        //String cmd = "Update Attendance Set TimeOut=" + SQLTime + "where EmpID=" + EmpID;
        //myDataBase.execSQL(cmd);
        //ToDo: Add Code; ,, can be done via execSql //Done!
    }


    public boolean CheckDriverAndAmbulance(String Driver_ID, String Amb_ID) {
        String cmd1 = "Select Availability from Driver where DriverID='" + Driver_ID + "'";
        String cmd2 = "Select CurrentStatus from Ambulance where AmbulanceID='" + Amb_ID + "'";
        Cursor cs1 = myDataBase.rawQuery(cmd1, null);
        Cursor cs2 = myDataBase.rawQuery(cmd2, null);
        cs1.moveToFirst();
        cs2.moveToFirst();

        if (cs1.getString(0).equals("1") && cs2.getString(0).equals("RY")) {
            return true;
        } else {
            return false;
        }

    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return myDataBase.query("PLEASE CHANGE TO YOUR TABLE NAME", null, null, null, null, null, null);
    }


    public int getMaxAccID() {
        String cmd = "Select max(AccountID) from User";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();
        int MaxID = Integer.parseInt(cs.getString(0));
        return (MaxID + 1);
    }

    public void ExecuteSQLQuery(String query) {
       // myDataBase.execSQL(query);
            // Database is in ReadOnly Mode despite linking the Database in a writeable format. So error thrown
        Toast ErrMsg=Toast.makeText(myContext.getApplicationContext(), "Error; cannot write to Database", Toast.LENGTH_LONG);
        ErrMsg.show();

    }

    public int getMaxPatID() {
        String cmd = "Select max(PatientID) from Patient";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();
        int MaxID = Integer.parseInt(cs.getString(0));
        return (MaxID + 1);

    }


    public boolean CheckAmbulanceExistence(String ambulanceID) {
        String cmd = "Select CurrentStatus from Ambulance where AmbulanceNo='" + ambulanceID + "'";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        if (cs.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public int getMaxAmbID() {
        String cmd = "Select max(AmbulanceID) from Ambulance";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();
        int MaxID = Integer.parseInt(cs.getString(0));
        return (MaxID + 1);
    }

    public AccountType getAccType(String userName) {
        String cmd = "Select AccountType from User where UserName='" + userName + "'";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();
        String ans = cs.getString(0);
        if (ans.equals("E")) {
            return AccountType.Employee;
        } else if (ans.equals("C")) {
            return AccountType.Consumer;
        }
        return null;
    }

    public Patient GetPatient(String uName) {
        String cmd = "Select PatientID,Name,DOB,CNIC,Address,PhoneNum,EnrollDate,UserName,Password,Patient.AccountID,AccountType from Patient JOIN User on Patient.AccountID=User.AccountID where UserName='" + uName + "'";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();
        Patient temp = new Patient();

        temp.setPatientID(Integer.parseInt(cs.getString(0)));
        temp.setName(cs.getString(1));


//        String d = null;
//        try {
//            d = (Date) new SimpleDateFormat("dd/MM/yyyy").parse( cs.getString(2) );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//

        temp.setDOB(cs.getString(2));
        temp.setCNIC(cs.getString(3));
        temp.setAddress(cs.getString(4));
        temp.setPhoneNum(cs.getString(5));

//        try {
//            d = (Date) new SimpleDateFormat("dd/MM/yyyy").parse( cs.getString(6) );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        temp.setEnrollDate(cs.getString(6));
        temp.setUserName(cs.getString(7));
        temp.setPassword(cs.getString(8));
        temp.SetAccID(Integer.parseInt(cs.getString(9)));
        temp.SetAccType(cs.getString(10));

        return temp;
    }

    public String getEmpType(String uName) {
        String ans = null;
        String cmd = "Select EmpType from User join Employee on Employee.AccountID=User.AccountID where UserName='" + uName + "'";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();

        ans = cs.getString(0);
        return ans;
    }

    public Admin GetAdmin(String uname) {
        Admin temp = new Admin();
        String cmd = "Select AdminID,Name,DOB,CNIC,Address,PhoneNum,EnrollDate,UserName,Password,Employee.AccountID,AccountType,Employee.EmpID from Admin join Employee on Employee.EmpID=Admin.EmpID join User on User.AccountID = Employee.AccountID where UserName='" + uname + "'";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();

        temp.setAdminID(Integer.parseInt(cs.getString(0)));
        temp.setName(cs.getString(1));


        Date d = null;
//        try {
//            d = (Date) new SimpleDateFormat("dd/MM/yyyy").parse( cs.getString(2) );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        temp.setDOB(cs.getString(2));
        temp.setCNIC(cs.getString(3));
        temp.setAddress(cs.getString(4));
        temp.setPhoneNum(cs.getString(5));

//        try {
//            d = (Date) new SimpleDateFormat("dd/MM/yyyy").parse( cs.getString(6) );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        temp.setEnrollDate(cs.getString(6));
        temp.setUserName(cs.getString(7));
        temp.setPassword(cs.getString(8));
        temp.SetAccID(Integer.parseInt(cs.getString(9)));
//        temp.SetAccType( cs.getString(10) );

        temp.setEmployeeID(Integer.parseInt(cs.getString(11)));


        return temp;
    }

    public Driver GetDriver(String uname) {
        Driver temp = new Driver();
        String cmd = "Select DriverID,Name,DOB,CNIC,Address,PhoneNum,EnrollDate,UserName,Password,Employee.AccountID,AccountType,LicenseID,Employee.EmpID from Driver join Employee on Employee.EmpID=Driver.EmpID join User on User.AccountID = Employee.AccountID where UserName='" + uname + "'";

        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();

        temp.setDriverID(Integer.parseInt(cs.getString(0)));
        temp.setName(cs.getString(1));


        Date d = null;
//        try {
//            d = (Date) new SimpleDateFormat("dd/MM/yyyy").parse( cs.getString(2) );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        temp.setDOB(cs.getString(2));
        temp.setCNIC(cs.getString(3));
        temp.setAddress(cs.getString(4));
        temp.setPhoneNum(cs.getString(5));

//        try {
//            d = (Date) new SimpleDateFormat("dd/MM/yyyy").parse( cs.getString(6) );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        temp.setEnrollDate(cs.getString(6));
        temp.setUserName(cs.getString(7));
        temp.setPassword(cs.getString(8));
        temp.SetAccID(Integer.parseInt(cs.getString(9)));
        temp.SetAccType(cs.getString(10));
        temp.setLicenseID(Integer.parseInt(cs.getString(11)));

        temp.setEmployeeID(Integer.parseInt(cs.getString(12)));

        temp.setAvailability(true);
        return temp;
    }

    public Manager GetManager(String uname) {
        Manager temp = new Manager();

        String cmd = "Select ManagerID,Name,DOB,CNIC,Address,PhoneNum,EnrollDate,UserName,Password,Employee.AccountID,AccountType,Employee.EmpID from Manager join Employee on Employee.EmpID=Manager.EmpID join User on User.AccountID = Employee.AccountID where UserName='" + uname + "'";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();

        temp.setManagerID(Integer.parseInt(cs.getString(0)));
        temp.setName(cs.getString(1));


        Date d = null;
//        try {
//            d = (Date) new SimpleDateFormat("dd/MM/yyyy").parse( cs.getString(2) );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        temp.setDOB(cs.getString(2));
        temp.setCNIC(cs.getString(3));
        temp.setAddress(cs.getString(4));
        temp.setPhoneNum(cs.getString(5));

//        try {
//            d = (Date) new SimpleDateFormat("dd/MM/yyyy").parse( cs.getString(6) );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        temp.setEnrollDate(cs.getString(6));
        temp.setUserName(cs.getString(7));
        temp.setPassword(cs.getString(8));
        temp.SetAccID(Integer.parseInt(cs.getString(9)));
        temp.SetAccType(cs.getString(10));
        temp.setEmployeeID(Integer.parseInt(cs.getString(11)));


        return temp;

    }

    public Dispatcher GetDispatcher(String uname) {
        Dispatcher temp = new Dispatcher();

        String cmd = "Select DispatcherID,Name,DOB,CNIC,Address,PhoneNum,EnrollDate,UserName,Password,Employee.AccountID,AccountType,Employee.EmpID from Dispatcher join Employee on Employee.EmpID=Dispatcher.EmpID join User on User.AccountID = Employee.AccountID where UserName='" + uname + "'";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();

        temp.setDispatcherID(Integer.parseInt(cs.getString(0)));
        temp.setName(cs.getString(1));


        Date d = null;
//        try {
//            d = (Date) new SimpleDateFormat("dd/MM/yyyy").parse( cs.getString(2) );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


        temp.setDOB(cs.getString(2));
        temp.setCNIC(cs.getString(3));
        temp.setAddress(cs.getString(4));
        temp.setPhoneNum(cs.getString(5));

//        try {
//            d = (Date) new SimpleDateFormat("dd/MM/yyyy").parse( cs.getString(6) );
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        temp.setEnrollDate(cs.getString(6));
        temp.setUserName(cs.getString(7));
        temp.setPassword(cs.getString(8));
        temp.SetAccID(Integer.parseInt(cs.getString(9)));
        temp.SetAccType(cs.getString(10));
        temp.setEmployeeID(Integer.parseInt(cs.getString(11)));

        return temp;


    }

    public boolean AttendanceStatus(String empID) {
        String cmd = " Select Status from Attendance WHERE EmpID='" + empID + "' ";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        if (cs.getCount() == 0) {
            return false;
        }
        cs.moveToFirst();
        if (cs.getString(0).equals("P")) {
            return true;
        } else {
            return false;
        }
    }

    public String getMaxTaskID() {
        String cmd = "Select max(TaskID) from Tasks";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();
        int MaxID = Integer.parseInt(cs.getString(0));

        return (String.valueOf(MaxID + 1));
    }

    public List<String> GetAllTasks() {
        List<String> temp = new ArrayList<>();
        String cmd = "Select TaskID, Name, Status from Tasks";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        if (cs.getCount() == 0) {
            return null;
        } else {
            cs.moveToFirst();
            for (int i = 0; i < cs.getCount(); i++) {
                String status=null;
                if (cs.getString(2).equals("1")){
                    status="Completed";
                }else{
                    status="Pending";
                }
                temp.add("ID: "+cs.getString(0)+"\t Name: "+cs.getString(1)+ "\t Status:"+status);
                cs.moveToNext();
            }

        }

        return temp;
    }

    public List<String> GetAllTasksAmbulance() {
        List<String> temp = new ArrayList<>();
        String cmd = "Select AmbulanceNo,CurrentStatus from Ambulance";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        if (cs.getCount() == 0) {
            return null;
        } else {
            cs.moveToFirst();
            for (int i = 0; i < cs.getCount(); i++) {
                temp.add("License No"+cs.getString(0)+"\tStatus: "+cs.getString(1));
                cs.moveToNext();
            }

        }

        return temp;

    }

    public List<String> GetAllEmployees() {
        List<String> temp = new ArrayList<>();
        String cmd = "select U.Name, U.CNIC, U.PhoneNum, U.DOB, U.Address, E.EmpType, U.EnrollDate, E.EmpID from (select * from User where AccountType = 'E') U \n" +
                "join Employee as E on E.AccountID = U.AccountID ";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        if (cs.getCount() == 0) {
            return null;
        } else {
            cs.moveToFirst();
            for (int i = 0; i < cs.getCount(); i++) {
                temp.add("Employee ID: " + cs.getString(7) + "\n\n Name: " + cs.getString(0) + "\n\n CNIC: " + cs.getString(1) + "\n\n Phone #: " + cs.getString(2) + "\n\n DOB: " + cs.getString(3) + "\n\n Address: " + cs.getString(4) + "\n\n Employee Type: " + cs.getString(5) + "\n\n Enroll Date: " + cs.getString(6));
                cs.moveToNext();
            }

        }

        return temp;

    }

    public List<String> GetAvailableAmb() {

        List<String> temp = new ArrayList<>();
        String cmd = "Select AmbulanceID, AmbulanceNo,CurrentStatus from Ambulance where CurrentStatus='RY' ";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        if (cs.getCount() == 0) {
            return null;
        } else {
            cs.moveToFirst();
            for (int i = 0; i < cs.getCount(); i++) {
                temp.add("ID: "+cs.getString(0)+"\tLicense No: "+cs.getString(1)+"\tStatus: "+cs.getString(2));
                cs.moveToNext();
            }

        }

        return temp;


    }

    public List<String> GetAllDrivers() {

        List<String> temp = new ArrayList<>();
        String cmd = "Select DriverID,Name from Driver join Employee on Employee.EmpID=Driver.EmpID join User on User.AccountID=Employee.AccountID where Availability=1 ";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        if (cs.getCount() == 0) {
            return null;
        } else {
            cs.moveToFirst();
            for (int i = 0; i < cs.getCount(); i++) {
                temp.add("ID: "+cs.getString(0)+"\tName: "+cs.getString(1));
                cs.moveToNext();
            }

        }

        return temp;

    }

    public List<String> GetPatRecords(String PatID) {
        List<String> temp = new ArrayList<>();
        String cmd = "Select Name,Location from Tasks where PatientID="+PatID;
        Cursor cs = myDataBase.rawQuery(cmd, null);
        if (cs.getCount() == 0) {
            return null;
        } else {
            cs.moveToFirst();
            for (int i = 0; i < cs.getCount(); i++) {
                temp.add(cs.getString(0)+"\n Area"+cs.getString(1));
                cs.moveToNext();
            }

        }

        return temp;


    }

    public String EmployeeTypeString(String empID) {
        String cmd = "Select EmpType from Employee where EmpID="+empID;
        Cursor cs = myDataBase.rawQuery(cmd, null);
        if (cs.getCount()!=0){
            cs.moveToFirst();
            return cs.getString(0);
        }
        return null;
    }

    public int getMaxEmpID() {
        String cmd = "Select max(EmpID) from Employee";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();
        int MaxID = Integer.parseInt(cs.getString(0));
        return (MaxID + 1);

    }

    public String getEmpUname(String empID) {
        String cmd = " Select UserName from User join Employee on Employee.AccountID=User.AccountID where EmpID="+empID;
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();
        return cs.getString(0);
    }

    public Employee getEditableEmp(String getUname) {
        String cmd = " Select Name,DOB,CNIC,Address,PhoneNum from User where UserName='"+getUname+"'";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();
        Employee temp=new Employee();
        temp.setName(cs.getString(0));
     //   temp.setDOB(cs.getString(1));
        temp.setCNIC(cs.getString(2));
        temp.setAddress(cs.getString(3));
        temp.setPhoneNum(cs.getString(4));
        return temp;

    }

    public Task FindTask(String ID) {
        Task temp=new Task();
        String cmd = " Select Name , Location,Status,DriverID,AmbulanceID from Tasks where TaskID="+ID;
        Cursor cs = myDataBase.rawQuery(cmd, null);
        if (cs.getCount()==0){
            return null;
        }else {
            cs.moveToFirst();
            temp.setName( cs.getString(0));
            temp.setLocation( cs.getString(1));

            if (cs.getString(2).equals("1")){
                temp.setCurrentStatus(TaskStatus.Completed);
            }else {
                temp.setCurrentStatus(TaskStatus.Pending);
            }
            if (cs.getString(3)!=null &&cs.getString(4)!=null){
            temp.setDriverID(Integer.parseInt( cs.getString(3)));
            temp.setAmbulanceID( Integer.parseInt(cs.getString(4)));
            }
        }
        return temp;
    }

    public Task FindDriverTask(int driverID) {
      SQLiteDatabase db=getReadableDatabase();
        Task temp=new Task();
        String cmd = "Select Name,Location,Status,TaskID,AmbulanceID from Tasks where DriverID="+driverID+" ORDER by TaskID DESC";
        Cursor cs = db.rawQuery(cmd, null);
        if (cs.getCount()==0){
            return null;
        }else {
            cs.moveToFirst();
            temp.setName( cs.getString(0));
            temp.setLocation( cs.getString(1));

            if (cs.getString(2).equals("1")){
                temp.setCurrentStatus(TaskStatus.Completed);
            }else {
                temp.setCurrentStatus(TaskStatus.Pending);
            }

            temp.setTaskID(Integer.parseInt( cs.getString(3)));
            temp.setAmbulanceID( Integer.parseInt(cs.getString(4)));
        }
        return temp;
    }

    public int getMaxAdminID() {
        String cmd = "Select max(AdminID) from Admin";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();
        int MaxID = Integer.parseInt(cs.getString(0));
        return (MaxID + 1);
    }

    public int getMaxManagerID() {
        String cmd = "Select max(ManagerID) from Manager";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();
        int MaxID = Integer.parseInt(cs.getString(0));
        return (MaxID + 1);
    }

    public int getMaxDispID() {
        String cmd = "Select max(DispatcherID) from Dispatcher";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();
        int MaxID = Integer.parseInt(cs.getString(0));
        return (MaxID + 1);
    }

    public int getMaxDriverID() {
        String cmd = "Select max(DriverID) from Driver";
        Cursor cs = myDataBase.rawQuery(cmd, null);
        cs.moveToFirst();
        int MaxID = Integer.parseInt(cs.getString(0));
        return (MaxID + 1);
    }


    public boolean CheckUNExistance(String UN) {
        String command = "Select UserName from user where UserName='" + UN + "'";
        Cursor cs = myDataBase.rawQuery(command, null);
        if (cs.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean CheckCNICExistance(String CNIC) {
        String command = "Select UserName from user where CNIC='" + CNIC + "'";
        Cursor cs = myDataBase.rawQuery(command, null);
        if (cs.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }

}
