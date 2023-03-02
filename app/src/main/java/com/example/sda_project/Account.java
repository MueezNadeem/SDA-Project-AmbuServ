package com.example.sda_project;

import java.io.Serializable;

public class Account implements Serializable {
    private  int AccountID;
    private AccountType Type;

    public  Account(){
        AccountID=0;
        Type=null;

    }
    public AccountType getType() {
        return Type;
    }

    public void setType(AccountType type) {
        Type = type;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int accountID) {
        AccountID = accountID;
    }
}
