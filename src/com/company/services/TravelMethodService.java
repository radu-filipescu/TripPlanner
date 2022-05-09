package com.company.services;

import Objects.Reminder;
import Objects.TravelMethod;
import Objects.TravelMethods.LongDistance;
import Objects.TravelMethods.ShortDistance;
import Objects.VisitingObjective;
import com.company.JDBCgeneric;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class TravelMethodService extends JDBCgeneric {
    final private String tableName1 = "shortdistancetravel";
    final private String tableName2 = "longdistancetravel";

    private TreeSet<Time> getTimetable(int idx) {
        String timetableQuery = "select * from shortdistancetimetable where shortDistanceId=" + idx;
        ResultSet results = executeSQLquery(timetableQuery);

        TreeSet<Time> timetable = new TreeSet<Time>();
        try {
            if(results != null)
                while(results.next()) {
                    Time currentTime = results.getTime("time");
                    timetable.add(currentTime);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return timetable;
    }

    public ArrayList<TravelMethod> getTravelMethods() {
        // short distance travel methods
        String selectQuery = "select * from shortdistancetravel";

        ArrayList<TravelMethod> travels = new ArrayList<TravelMethod>();
        ResultSet results = executeSQLquery(selectQuery);

        try {
            while (results.next()) {
                ShortDistance currentTravel = new ShortDistance();
                currentTravel.setId(results.getInt("Id"));
                currentTravel.setName(results.getString("name"));
                currentTravel.setPrice(results.getString("price"));

                // gets the associated timetable from the timetables table
                currentTravel.setTimeTable(getTimetable(currentTravel.getId()));

                travels.add(currentTravel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // long distance ones
        selectQuery = "select * from longdistancetravel";
        results = executeSQLquery(selectQuery);

        try {
            while (results.next()) {
                LongDistance currentTravel = new LongDistance();
                currentTravel.setId(results.getInt("Id"));
                currentTravel.setName(results.getString("name"));
                currentTravel.setPrice(results.getString("price"));
                currentTravel.setDepartureTime(results.getTime("departureTime"));
                currentTravel.setReturnTime(results.getTime("returnTime"));
                currentTravel.setRequiresPassport(results.getBoolean("passport"));
                currentTravel.setRequiresCheckIn(results.getBoolean("checkin"));

                travels.add(currentTravel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return travels;
    }

    private boolean isShortDistance(TravelMethod obj) {
        return obj.getClass().toString().equals("class Objects.TravelMethods.ShortDistance");
    }

    private int getIdOfAddedShortMethod() {
        ResultSet result = executeSQLquery("select * from " + tableName1 + " order by Id desc limit 1");

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

    // adds a time entry to the short distance
    // travel method with index idx
    private void addTimeEntry(int idx, Time t) {
        String insertQuery = "insert into shortdistancetimetable values(" + idx + ", '" + t + "')";

        executeSQLupdate(insertQuery);
    }

    public void addTravelMethod(TravelMethod obj) {
        String insertQuery;

        if(isShortDistance(obj)) {
            insertQuery = "insert into " + tableName1 + "(name, price) values('" + obj.getName() + "', '" + obj.getPrice() + "')";

            executeSQLupdate(insertQuery);

            // add timetable to the coresponding table

            int idx = getIdOfAddedShortMethod();

            ShortDistance sd = (ShortDistance)obj;
            Iterator it = sd.getTimeTable().iterator();

            while(it.hasNext()) {
                addTimeEntry(idx, (Time) it.next());
            }
        }
        else {
            LongDistance ld = (LongDistance) obj;
            insertQuery = "insert into " + tableName2 + "(name, price, departureTime, returnTime, passport, checkin) " +
                    "values('" + ld.getName() + "', '" + ld.getPrice() + "', '" + ld.getDepartureTime() +
                    "', '" + ld.getReturnTime() + "', " + ld.isRequiresPassport() + ", " + ld.isRequiresCheckIn() + ")";

            executeSQLupdate(insertQuery);
        }
    }

    public void removeShortDistance(int idx) {
        String deleteQuery = "delete from shortdistancetravel where Id=" + idx;
        executeSQLupdate(deleteQuery);

        deleteQuery = "delete from shortdistancetimetable where shortDistanceId=" + idx;
        executeSQLupdate(deleteQuery);

        System.out.println("travel method deleted!");
    }

    public void removeLongDistance(int idx) {
        String deleteQuery = "delete from longdistancetravel where Id=" + idx;
        executeSQLupdate(deleteQuery);

        System.out.println("travel method deleted!");
    }
}
