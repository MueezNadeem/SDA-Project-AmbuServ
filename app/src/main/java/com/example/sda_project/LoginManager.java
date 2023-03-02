package com.example.sda_project;

public class LoginManager {
    private DatabaseClass myC;
    private DatabaseHelper DB ;
    public LoginManager(){
        myC=new DatabaseClass();
        DB=myC.getInstance();

    }
    public boolean VerifyCredentials(String Password,String Username){
       return DB.CheckUserName(Username,Password);
    }

    public AccountType getTypeUser(String UserName) {
        AccountType AT;
        AT=DB.getAccType(UserName);
        return AT;
    }

    public Patient GetPatient(String UName) {
    Patient CurrPat=DB.GetPatient(UName);
        return CurrPat;
    }

    public EmployeeType getEmpType(String UName) {
        String EType=DB.getEmpType(UName);
        EmployeeType ET=null;

        if (EType.equals("AD")){
            ET=EmployeeType.Admin;
        }else if (EType.equals("MG")){
            ET=EmployeeType.Manager;
        }else if (EType.equals("DP")){
            ET=EmployeeType.Dispatcher;
        }else if (EType.equals("DR")){
            ET=EmployeeType.Driver;
        }else if (EType.equals("WR")){
            ET=EmployeeType.Worker;
        }

        return ET;
    }

    public Admin GetAdmin(String Uname) {
        return DB.GetAdmin(Uname);
    }

    public Driver GetDriver(String Uname) {
        return DB.GetDriver(Uname);
    }

    public Manager GetManager(String Uname) {
        return DB.GetManager(Uname);
    }

    public Dispatcher GetDispatcher(String Uname) {
        return DB.GetDispatcher(Uname);
    }

}
