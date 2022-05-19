package com.company.CSVservices;

import Objects.Accommodation;
import Objects.Reminder;
import com.company.CSVgeneric;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReminderServiceCSV extends CSVgeneric {
    public ReminderServiceCSV(String filepath) {
        super(filepath);
    }

    // for auto-increment
    private static int lastId = 0;

    public void addReminder(Reminder reminder) {
        try {
            addObject(reminder);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Reminder> getReminders() {
        ArrayList<Reminder> reminders = new ArrayList<Reminder>();
        ArrayList<String> raw = readAll();

        for(int i = 5; i < raw.size(); i = i + 5) {
            Reminder reminder = new Reminder();

            reminder.setId(Integer.parseInt(raw.get(i)));
            reminder.setTitle(raw.get(i + 1));
            reminder.setDescription(raw.get(i + 2));
            if(raw.get(i + 3) == "1")
                reminder.setAsDone();
            reminder.setObjectiveId(Integer.parseInt(raw.get(i + 4)));

            reminders.add(reminder);
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
        ArrayList<Reminder> reminders = getReminders();

        for(int i = 0; i < reminders.size(); ++i)
            if(reminders.get(i).getId() == idx)
                reminders.get(i).setAsDone();
    }

    public void removeReminder(int idx) {
        ArrayList<Reminder> reminders = getReminders();

        for(int i = 0; i < reminders.size(); ++i)
            if(reminders.get(i).getId() == idx)
                reminders.remove(i);
    }
}
