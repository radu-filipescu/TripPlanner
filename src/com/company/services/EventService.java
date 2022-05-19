package com.company.services;

import Objects.Event;
import Objects.Events.IndoorEvent;
import Objects.Events.OutdoorEvent;
import Objects.Reminder;
import com.company.JDBCgeneric;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventService extends JDBCgeneric {
    private final String tableName1 = "indoorevents";
    private final String tableName2 = "outdoorevents";

    public boolean isIndoor(Event obj) {
        return obj.getClass().toString().equals("class Objects.Events.IndoorEvent");
    }

    public void addEvent(Event event) {
        String addQuery;

        if(isIndoor(event)) {
            IndoorEvent obj = (IndoorEvent)event;
            addQuery = "insert into " + tableName1 + "(name, location, time, price) values('" +
                obj.getName() + "', '" + obj.getLocation() + "', '" + obj.getEventTime() + "', " + obj.getTicketPrice() + ")";

            executeSQLupdate(addQuery);
        }
        else {
            OutdoorEvent obj = (OutdoorEvent)event;
            addQuery = "insert into " + tableName2 + "(name, location, length, hasCamping) values('" +
                    obj.getName() + "', '" + obj.getLocation() + "', " + obj.getDaysLength() + ", " + obj.hasCamping() + ")";

            executeSQLupdate(addQuery);
        }
    }

    public ArrayList<Event> getEvents() {
        String selectQuery = "select * from " + tableName1;

        ArrayList<Event> events = new ArrayList<Event>();
        ResultSet results = executeSQLquery(selectQuery);

        try {
            while (results.next()) {
                IndoorEvent currentEvent = new IndoorEvent();
                currentEvent.setId(results.getInt("Id"));
                currentEvent.setName(results.getString("name"));
                currentEvent.setLocation(results.getString("location"));
                currentEvent.setEventTime(results.getTime("time"));
                currentEvent.setTicketPrice(results.getInt("price"));

                events.add(currentEvent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        selectQuery = "select * from " + tableName2;
        results = executeSQLquery(selectQuery);

        try {
            while (results.next()) {
                OutdoorEvent currentEvent = new OutdoorEvent();
                currentEvent.setId(results.getInt("Id"));
                currentEvent.setName(results.getString("name"));
                currentEvent.setLocation(results.getString("location"));
                currentEvent.setDaysLength(results.getInt("length"));
                currentEvent.setHasCamping(results.getBoolean("hasCamping"));

                events.add(currentEvent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

}
