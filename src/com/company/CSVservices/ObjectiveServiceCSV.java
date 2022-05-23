package com.company.CSVservices;

import Objects.Reminder;
import Objects.VisitingObjective;
import com.company.CSVgeneric;
import com.company.JDBCservices.ObjectiveService;
import com.company.JDBCservices.ReminderService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ObjectiveServiceCSV extends CSVgeneric {
    private ObjectiveServiceCSV(String filepath) {
        super(filepath);
    }

    private static ObjectiveServiceCSV instance = new ObjectiveServiceCSV("src/Storage/objectives.csv");

    public static ObjectiveServiceCSV getInstance() {
        return instance;
    }

    public void addObjective(VisitingObjective obj) {
        try {
            addObject(obj);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void markAsSeen(int idx) {
        ArrayList<VisitingObjective> objectives = getObjectives();

        for(int i = 0; i < objectives.size(); ++i)
            if(objectives.get(i).getId() == idx)
                objectives.get(i).setSeen();
    }

    public ArrayList<VisitingObjective> getObjectives() {
        ArrayList<VisitingObjective> objectives = new ArrayList<VisitingObjective>();
        ArrayList<String> raw = readAll();

        for(int i = 5; i < raw.size(); i = i + 5) {
            VisitingObjective objective = new VisitingObjective();

            objective.setId(Integer.parseInt(raw.get(i)));
            objective.setName(raw.get(i + 1));
            objective.setLocation(raw.get(i + 2));
            objective.setEstimatedTimeToVisit(Integer.parseInt(raw.get(i + 3)));
            if(raw.get(i + 4) == "1")
                objective.setSeen();

            objectives.add(objective);
        }

        return objectives;
    }

    public VisitingObjective getObjectiveById(int idx) {
        ArrayList<VisitingObjective> objectives = getObjectives();

        for(int i = 0; i < objectives.size(); ++i)
            if(objectives.get(i).getId() == idx)
                return objectives.get(i);

        // in case no objective with that Id exists
        return null;
    }
}
