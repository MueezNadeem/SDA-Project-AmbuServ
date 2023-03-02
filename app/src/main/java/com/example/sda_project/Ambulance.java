package com.example.sda_project;

public class Ambulance
{
    private int AmbulanceID;
    private int AmbulanceNo;
    private AmbulanceStatus CurrentStatus;


    public int getAmbulanceID() {
        return AmbulanceID;
    }

    public void setAmbulanceID(int ambulanceID) {
        AmbulanceID = ambulanceID;
    }

    public AmbulanceStatus getCurrentStatus() {
        return CurrentStatus;
    }

    public void setCurrentStatus(AmbulanceStatus currentStatus) {
        CurrentStatus = currentStatus;
    }

    public int getAmbulanceNo() {
        return AmbulanceNo;
    }

    public void setAmbulanceNo(int ambulanceNo) {
        AmbulanceNo = ambulanceNo;
    }
}
