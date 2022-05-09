package com.company.services;

import com.company.JDBCgeneric;
import jdk.jfr.Timespan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class AuditService extends JDBCgeneric {
    private final String tablename = "applogs";

    public void addLog(String action) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        String insertQuery = "insert into " + tablename + "(actionName, time) values('" + action + "', '" + currentTime + "')";

        executeSQLupdate(insertQuery);
    }

    public void printLogs() {
        String selectQuery = "select * from " + tablename;
        ResultSet results = executeSQLquery(selectQuery);

        System.out.println("%%%% LOGS %%%%");
        try {
            while(results.next()) {
                System.out.println(results.getString("actionName") + " at: " + results.getTimestamp("time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // optional limit parameter to only fetch the latest X logs
    public void printLogs(int limit) {
        String selectQuery = "select * from " + tablename + " order by Id desc limit " + limit;
        ResultSet results = executeSQLquery(selectQuery);

        System.out.println("%%%% LOGS %%%%");
        try {
            while(results.next()) {
                System.out.println(results.getString("actionName") + " at: " + results.getTimestamp("time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
