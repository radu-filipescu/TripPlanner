package com.company.services;

import Objects.Reminder;
import Objects.VisitingObjective;
import com.company.JDBCgeneric;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReminderService extends JDBCgeneric {
    final private String tableName = "reminders";

    public void addReminder(Reminder rem){
        String addQuery = "insert into " + tableName + "(title, description, done, objectiveId) values(";
        addQuery += "'" + rem.getTitle() + "', '" + rem.getDescription() + "', " + rem.isDone() + ", " + rem.getObjectiveId() + ")";

        executeSQLupdate(addQuery);
    }

    public ArrayList<Reminder> getReminders() {
        String selectQuery = "select * from reminders";

        ArrayList<Reminder> reminders = new ArrayList<Reminder>();
        ResultSet results = executeSQLquery(selectQuery);

        try {
            while (results.next()) {
                Reminder currentReminder = new Reminder();
                currentReminder.setId(results.getInt("Id"));
                currentReminder.setObjectiveId(results.getInt("objectiveId"));
                currentReminder.setTitle(results.getString("title"));
                currentReminder.setDescription(results.getString("description"));
                if(results.getBoolean("done"))
                    currentReminder.setAsDone();

                reminders.add(currentReminder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reminders;
    }

    public ArrayList<Reminder> getObjectiveReminders(int idx) {
        ArrayList<Reminder> unfiltered = getReminders();

        for(int i = 0; i < unfiltered.size(); ++i)
            if(unfiltered.get(i).getObjectiveId() != idx)
                unfiltered.remove(i);

        return unfiltered;
    }

    public void markReminderDone(int idx) {
        String updateQuery = "update " + tableName + " set done=true where Id=" + idx;

        executeSQLupdate(updateQuery);
    }

    public void removeReminder(int idx) {
        String deleteQuery = "delete from " + tableName + " where Id=" + idx;
        executeSQLupdate(deleteQuery);

        System.out.println("reminder removed!");
    }
}
