package com.example.sda_project;

import java.time.LocalTime;
import java.util.Date;

public class Attendance {
    private int EmpID;
    private Date DateEntry;
    private LocalTime TimedIn;
    private LocalTime TimedOut;
    private AttendanceStatus CurrentStatus=AttendanceStatus.Absent;

    public AttendanceStatus getCurrentStatus() {
        return CurrentStatus;
    }

    public void setCurrentStatus(AttendanceStatus currentStatus) {
        CurrentStatus = currentStatus;
    }

    public int getEmpID() {
        return EmpID;
    }

    public void setEmpID(int empID) {
        EmpID = empID;
    }

    public LocalTime getTimedIn() {
        return TimedIn;
    }

    public void setTimedIn(LocalTime timedIn) {
        TimedIn = timedIn;
    }

    public LocalTime getTimedOut() {
        return TimedOut;
    }

    public void setTimedOut(LocalTime timedOut) {
        TimedOut = timedOut;
    }

    public Date getDateEntry() {
        return DateEntry;
    }

    public void setDateEntry(Date dateEntry) {
        DateEntry = dateEntry;
    }
}
