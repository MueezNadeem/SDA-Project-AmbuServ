package com.example.sda_project;

public class Task {
    private int TaskID;
    private String Name;
    private String Location;
    private TaskStatus CurrentStatus;
    private int DriverID;
    private int AmbulanceID;
    private int PatientID;

    public int getTaskID() {
        return TaskID;
    }

    public void setTaskID(int taskID) {
        TaskID = taskID;
    }

    public TaskStatus getCurrentStatus() {
        return CurrentStatus;
    }

    public void setCurrentStatus(TaskStatus currentStatus) {
        CurrentStatus = currentStatus;
    }

    public int getDriverID() {
        return DriverID;
    }

    public void setDriverID(int driverID) {
        DriverID = driverID;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getPatientID() {
        return PatientID;
    }

    public void setPatientID(int patientID) {
        PatientID = patientID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAmbulanceID() {
        return AmbulanceID;
    }

    public void setAmbulanceID(int ambulanceID) {
        AmbulanceID = ambulanceID;
    }
    // Add Team object?
}
