package com.example.sda_project;

import java.util.ArrayList;
import java.util.List;

public class RescueTeam {
    private int LeaderID;
    private int TeamID;
    List<Worker> TeamMembers= new ArrayList<>();

    public int getLeaderID() {
        return LeaderID;
    }

    public void setLeaderID(int leaderID) {
        LeaderID = leaderID;
    }

    public int getTeamID() {
        return TeamID;
    }

    public void setTeamID(int teamID) {
        TeamID = teamID;
    }

    public void AddWorkerToTeam(Worker _w){
        TeamMembers.add(_w);
    }

    public List<Worker> GetTeam(){
        return TeamMembers;
    }

    public void RemoveWorkerFromTeam(Worker _w){
        TeamMembers.remove(_w);
    }


}
