package com.example.sda_project;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String Name;
    private String DOB;
    private String CNIC;
    private String Address;
    private String Email;
    private String PhoneNum;
    private String EnrollDate;
    private String UserName;
    private String Password;
    private Account myAcc;

    public User() {
        Name=null;
        DOB=null;
        CNIC=null;
        Address=null;
        Email=null;
        PhoneNum=null;
        EnrollDate=null;
        UserName=null;
        Password=null;
        myAcc=new Account();
    }

    public int GetAccID(){return myAcc.getAccountID();}
    public void SetAccID(int ID){
        this.myAcc.setAccountID(ID);
    }

    public void SetAccType(String Type){
        if (Type.equals("C")){
            this.myAcc.setType(AccountType.Consumer);
        }else if (Type.equals("E")){
            this.myAcc.setType(AccountType.Employee);
        }

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEnrollDate() {
        return EnrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        EnrollDate = enrollDate;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
