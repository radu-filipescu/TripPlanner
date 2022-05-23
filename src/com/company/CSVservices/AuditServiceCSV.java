package com.company.CSVservices;

import com.company.CSVgeneric;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

class AppLog {
    private int Id;
    private String actionName;
    private Timestamp time;

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getActionName() {
        return actionName;
    }
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }
    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }
}

public class AuditServiceCSV extends CSVgeneric {
    private AuditServiceCSV(String filepath) throws FileNotFoundException {
        super(filepath);
    }

    private static AuditServiceCSV instance;

    static {
        try {
            instance = new AuditServiceCSV("src/Storage/applogs.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static AuditServiceCSV getInstance() {
        return instance;
    }

    // for auto-increment, to replicate SQL behaviour
    private static int lastId = 0;

    public void addLog(String action) {
        AppLog newLog = new AppLog();

        lastId++;
        newLog.setId(lastId);
        newLog.setActionName(action);

        newLog.setTime(new Timestamp(System.currentTimeMillis()));

        try {
            addObject(newLog);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printLogs() {
        System.out.println("%%%% LOGS %%%%");

        ArrayList<String> rawLogs = readAll();

        for(int i = 3; i < rawLogs.size(); i += 3) {
            System.out.println(rawLogs.get(i + 1) + " at: " + rawLogs.get(i + 2));
        }
    }

    public void printLogs(int limit) {
        System.out.println("%%%% LOGS %%%%");

        ArrayList<String> rawLogs = readAll();

        // latest 'limit' logs
        int startLog = rawLogs.size() - 3 * limit;

        for(int i = startLog; i < rawLogs.size(); i += 3) {
            System.out.println(rawLogs.get(i + 1) + " at: " + rawLogs.get(i + 2));
        }
    }
}
