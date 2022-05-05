package com.company.services;

import Objects.Reminder;
import com.company.JDBCgeneric;

public class ReminderService extends JDBCgeneric {
    final private String tableName = "reminders";

    public void addReminder(Reminder rem){
        String addQuery = "insert into " + tableName + "(title, description, done, objectiveId) values(";
        addQuery += "'" + rem.getTitle() + "', '" + rem.getDescription() + "', " + rem.isDone() + ", " + rem.getObjectiveId() + ")";

        executeSQLupdate(addQuery);
    }
}
