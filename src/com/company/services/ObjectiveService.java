package com.company.services;

import Objects.VisitingObjective;
import com.company.JDBCgeneric;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ObjectiveService extends JDBCgeneric {
    final private String tableName = "objectives";

    public void addObjective(VisitingObjective obj) {
        String addQuery = "insert into " + tableName + "(name, location, timeToVisit, seen) values(";
        addQuery += "'" + obj.getName() + "', '" + obj.getLocation() + "', " + obj.getEstimatedTimeToVisit() + ", " + obj.getSeen() + ")";

        executeSQLupdate(addQuery);
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

                objectives.add(currentObjective);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objectives;
    }
}
