package com.company.services;

import Objects.Reminder;
import Objects.VisitingObjective;
import com.company.JDBCgeneric;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ObjectiveService extends JDBCgeneric {
    final private String tableName = "objectives";

    private int getIdOfAddedObjective() {
        ResultSet result = executeSQLquery("select * from " + tableName + " order by Id desc limit 1");

        int idx = 0;

        try {
            while(result.next()) {
                idx = result.getInt("Id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idx;
    }

    public void addObjective(VisitingObjective obj) {
        String addQuery = "insert into " + tableName + "(name, location, timeToVisit, seen) values(";
        addQuery += "'" + obj.getName() + "', '" + obj.getLocation() + "', " + obj.getEstimatedTimeToVisit() + ", " + obj.getSeen() + ")";

        executeSQLupdate(addQuery);
        int idx = getIdOfAddedObjective();

        // now we will add the reminders
        // associated with visiting this objective

        ReminderService reminderService = new ReminderService();
        for(Reminder rem: obj.getToDoDependencies()) {
            rem.setObjectiveId(idx);
            reminderService.addReminder(rem);
        }
    }

    public void markAsSeen(int idx) {
        String updateQuery = "update " + tableName + " set seen=1 where Id=" + idx;

        executeSQLupdate(updateQuery);
    }

    public ArrayList<VisitingObjective> getObjectives() {
        String selectQuery = "select * from objectives where seen = false";

        ArrayList<VisitingObjective> objectives = new ArrayList<VisitingObjective>();
        ResultSet results = executeSQLquery(selectQuery);

        try {
            while (results.next()) {
                VisitingObjective currentObjective = new VisitingObjective();
                currentObjective.setId(results.getInt("Id"));
                currentObjective.setName(results.getString("name"));
                currentObjective.setLocation(results.getString("location"));
                currentObjective.setEstimatedTimeToVisit(results.getDouble("timeToVisit"));
                if(results.getBoolean("seen"))
                    currentObjective.setSeen();

                // gets the associated reminders from the reminder table
                ReminderService reminderService = new ReminderService();
                ArrayList<Reminder> currentReminders = reminderService.getObjectiveReminders(currentObjective.getId());
                currentObjective.setToDoDependencies(currentReminders);

                objectives.add(currentObjective);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objectives;
    }

    public VisitingObjective getObjectiveById(int idx) {
        String selectQuery = "select * from objectives where Id=" + idx;

        VisitingObjective objective = new VisitingObjective();
        ResultSet results = executeSQLquery(selectQuery);

        try {
            while (results.next()) {
                VisitingObjective currentObjective = new VisitingObjective();
                currentObjective.setId(results.getInt("Id"));
                currentObjective.setName(results.getString("name"));
                currentObjective.setLocation(results.getString("location"));
                currentObjective.setEstimatedTimeToVisit(results.getDouble("timeToVisit"));
                if(results.getBoolean("seen"))
                    currentObjective.setSeen();

                // gets the associated reminders from the reminder table
                ReminderService reminderService = new ReminderService();
                ArrayList<Reminder> currentReminders = reminderService.getObjectiveReminders(currentObjective.getId());
                currentObjective.setToDoDependencies(currentReminders);

                objective = currentObjective;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objective;
    }
}
