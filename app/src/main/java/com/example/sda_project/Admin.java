package com.example.sda_project;

import java.io.Serializable;

public class Admin extends  Employee implements Serializable {
    private int AdminID;

    public Admin(){
        this.AdminID=0;
    }

    public int getAdminID() {
        return AdminID;
    }

    public void setAdminID(int adminID) {
        AdminID = adminID;
    }
}
