package com.company;

import Objects.Event;
import Objects.Accommodation;
import Objects.Events.IndoorEvent;
import Objects.Events.OutdoorEvent;
import Objects.Reminder;
import Objects.TravelMethod;
import Objects.TravelMethods.LongDistance;
import Objects.TravelMethods.ShortDistance;
import Objects.VisitingObjective;
import com.company.services.*;

import java.sql.Array;
import java.sql.Time;
import java.util.*;

public class MainService {
    ObjectiveService objectiveService = new ObjectiveService();
    ReminderService reminderService = new ReminderService();
    TravelMethodService travelService = new TravelMethodService();
    AuditService auditService = new AuditService();
    AccommodationService accommodationService = new AccommodationService();
    EventService eventService = new EventService();

    private ArrayList<VisitingObjective> objectives = new ArrayList<VisitingObjective>();
    private ArrayList<Reminder> reminders = new ArrayList<Reminder>();
    private ArrayList<TravelMethod> travelMethods = new ArrayList<TravelMethod>();
    private ArrayList<Accommodation> accommodations = new ArrayList<Accommodation>();
    private ArrayList<Event> events = new ArrayList<Event>();

    public void addObjective(Scanner in) {
        VisitingObjective newObjective = new VisitingObjective();

        System.out.println("enter objective name: ");
        newObjective.setName(in.nextLine());

        System.out.println("enter objective location: ");
        newObjective.setLocation(in.nextLine());

        System.out.println("enter estimated time to visit (hours):");
        newObjective.setEstimatedTimeToVisit(Integer.parseInt(in.nextLine()));

        System.out.println("enter number of things to do before visiting this (0 if there are none):");
        int reminderCount = Integer.parseInt(in.nextLine());

        ArrayList<Reminder> objectiveRemList = new ArrayList<Reminder>();

        for(int i = 0; i < reminderCount; ++i) {
            Reminder currentReminder = new Reminder();

            System.out.println("enter reminder title:");
            currentReminder.setTitle(in.nextLine());

            System.out.println("enter reminder description: (or press enter to leave blank)");
            currentReminder.setDescription(in.nextLine());

            System.out.println("reminder " + Integer.toString(i + 1) + " for current objective added!\n");

            objectiveRemList.add(currentReminder);
        }

        newObjective.setToDoDependencies(objectiveRemList);

        // in-memory version (first stage of the project)
        // objectives.add(newObjective);

        // JDBC persistence version
        objectiveService.addObjective(newObjective);

        System.out.println("objective added succesfully!\n");
        auditService.addLog("objective added");
    }

    public void listObjectives() {
        objectives = objectiveService.getObjectives();
        for (VisitingObjective obj: objectives) {
            // prints unseen objectives
            if(!obj.getSeen()) {
                System.out.println(obj.getId() + ". " + obj.getName());
            }
        }
        System.out.println();

        auditService.addLog("objectives printed");
    }

    public void markObjectiveSeen(Scanner in) {
        objectives = objectiveService.getObjectives();
        System.out.println("enter the number of the objective you want to mark as seen");

        int idx = Integer.parseInt(in.nextLine());

        objectiveService.markAsSeen(idx);
        System.out.println("objective number " + idx + " marked as visited!");

        auditService.addLog("objective number " + idx + " marked seen");
    }

    public void showDetailsForObjective(Scanner in) {
        objectives = objectiveService.getObjectives();
        System.out.println("enter number of the objective you want to see details about");
        int idx = Integer.parseInt(in.nextLine());

        objectiveService.getObjectiveById(idx).prettyPrint();
        auditService.addLog("show details for objective " + idx);
    }

    public void addTravelMethod(Scanner in) {
        System.out.println("is this a long-distance travel mean?");
        System.out.println("type 'yes' or 'no'\n");

        String answer = in.nextLine();

        if(!answer.equals("yes") && !answer.equals("no")) {
            System.out.println("input error");
        }
        else {
            if(answer.equals("yes")) {
                LongDistance newTravelMethod = new LongDistance();

                System.out.println("what is this travel method?");
                newTravelMethod.setName(in.nextLine());

                System.out.println("what is the price for this?");
                newTravelMethod.setPrice(in.nextLine());

                System.out.println("what is the departure time? (hh:mm)");
                answer = in.nextLine() + ":00";
                newTravelMethod.setDepartureTime(Time.valueOf(answer));

                System.out.println("what is the return time?");
                answer = in.nextLine() + ":00";
                newTravelMethod.setReturnTime(Time.valueOf(answer));

                System.out.println("does this require a passport?");
                System.out.println("type 'yes' or 'no'\n");

                answer = in.nextLine();
                while(!answer.equals("yes") && !answer.equals("no")) {
                    System.out.println("input error\n");
                    System.out.println("type 'yes' or 'no'\n");
                    answer = in.nextLine();
                }

                if(answer.equals("yes"))
                    newTravelMethod.setRequiresPassport(true);
                else
                    newTravelMethod.setRequiresPassport(false);

                System.out.println("does this require prior checking-in?");
                System.out.println("type 'yes' or 'no'\n");
                answer = in.nextLine();
                while(!answer.equals("yes") && !answer.equals("no")) {
                    System.out.println("input error\n");
                    System.out.println("type 'yes' or 'no'\n");
                    answer = in.nextLine();
                }

                if(answer.equals("yes"))
                    newTravelMethod.setRequiresCheckIn(true);
                else
                    newTravelMethod.setRequiresCheckIn(false);

                travelService.addTravelMethod(newTravelMethod);

                System.out.println("travel mean added succesfully!");
            }
            else {
                ShortDistance newTravelMethod = new ShortDistance();

                System.out.println("what is this travel method?");
                newTravelMethod.setName(in.nextLine());

                System.out.println("what is the price for this?");
                newTravelMethod.setPrice(in.nextLine());

                System.out.println("how many schedule times do you want to enter for this mean of transportation?");

                int timeTableCount = Integer.parseInt(in.nextLine());

                TreeSet<Time> timeTable = new TreeSet<Time>();
                for(int i = 0; i < timeTableCount; ++i) {
                    System.out.println("enter time: (hh:mm)");
                    answer = in.nextLine() + ":00";
                    Time newTime = Time.valueOf(answer);

                    timeTable.add(newTime);
                }

                newTravelMethod.setTimeTable(timeTable);
                travelService.addTravelMethod(newTravelMethod);

                System.out.println("travel mean added succesfully!");
            }
            auditService.addLog("travel method added");
        }
    }

    public void showTravelMethods() {
        travelMethods = travelService.getTravelMethods();

        for(int i = 0; i < travelMethods.size(); ++i) {
            TravelMethod currentMethod = travelMethods.get(i);

            System.out.println("######");
            System.out.println(Integer.toString(i + 1) + ". " + currentMethod.getName());
            System.out.println("price: " + currentMethod.getPrice());

            if(currentMethod.getClass().toString().equals("class Objects.TravelMethods.LongDistance")) {
                LongDistance currentLD = (LongDistance)currentMethod;
                System.out.println("departure time: " + currentLD.getDepartureTime());
                System.out.println("return time: " + currentLD.getReturnTime());

                if(currentLD.isRequiresPassport())
                    System.out.println("do not forget to bring your passport!");
                if(currentLD.isRequiresCheckIn())
                    System.out.println("do not forget to do the check-in a day prior to leaving!");
            }
            else {
                ShortDistance currentSD = (ShortDistance)currentMethod;

                System.out.println("the timetable is:");
                for (Time t: currentSD.getTimeTable()) {
                    System.out.println(t);
                }
            }

            System.out.println();
        }
        auditService.addLog("printed travel methods");
    }

    public void removeTravelMethod(Scanner in) {
        System.out.println("do you want to remove a long-distance travel method?");
        System.out.println("type 'yes' or 'no'\n");

        String answer = in.nextLine();
        if(!answer.equals("yes") && !answer.equals("no")) {
            System.out.println("input error");
        }
        else {
            System.out.println("enter number of the travel mean you want to remove");
            int idx = Integer.parseInt(in.nextLine());

            if(answer.equals("yes"))
                travelService.removeLongDistance(idx);
            else
                travelService.removeShortDistance(idx);

            auditService.addLog("remove travel method");
        }
    }

    public void addReminder(Scanner in) {
        Reminder newReminder = new Reminder();

        System.out.println("add a title for this reminder:");
        newReminder.setTitle(in.nextLine());

        System.out.println("enter a description for this reminder: (or press enter to leave blank)");
        newReminder.setDescription(in.nextLine());

        // in-memory version (first stage of the project)
        // reminders.add(newReminder);

        // JDBC persistence version
        reminderService.addReminder(newReminder);

        System.out.println("reminder added!");
        auditService.addLog("reminder added");
    }

    public void showReminders(Scanner in) {
        reminders = reminderService.getReminders();

        System.out.println("do you want to show completed reminders too?");
        System.out.println("type 'yes' or 'no'\n");

        String answer = in.nextLine();

        if(!answer.equals("yes") && !answer.equals("no")) {
            System.out.println("input error");
            return;
        }

        for(int i = 0; i < reminders.size(); ++i) {
            Reminder currentReminder = reminders.get(i);

            if(answer.equals("yes") || !currentReminder.isDone()) {
                currentReminder.prettyPrint();
                System.out.println();
            }
        }

        auditService.addLog("printed reminders");
    }

    public void markReminderDone(Scanner in) {
        System.out.println("enter the number of the reminder you want to mark as done");
        int idx = Integer.parseInt(in.nextLine());

        reminderService.markReminderDone(idx);
        System.out.println("reminder number " + idx + " marked as done!");

        auditService.addLog("reminder number " + idx + " marked as done");
    }

    public void removeReminder(Scanner in) {
        System.out.println("enter the number of the reminder you want to mark as done");
        int idx = Integer.parseInt(in.nextLine());

        reminderService.removeReminder(idx);
        System.out.println("reminder removed!");
        auditService.addLog("remove reminder");
    }

    public void addAccommodation(Scanner in) {
        Accommodation newAccommodation = new Accommodation();

        System.out.println("enter the name of this accommodation:");
        newAccommodation.setName(in.nextLine());

        System.out.println("enter the location of this accommodation:");
        newAccommodation.setLocation(in.nextLine());

        System.out.println("what is the price per night?");
        newAccommodation.setPrice(Integer.parseInt(in.nextLine()));

        accommodationService.addAccommodation(newAccommodation);
        System.out.println("accommodation added!");
        auditService.addLog("accommodation added");
    }

    public void listAccommodations() {
        accommodations = accommodationService.getAccommodations();

        for(int i = 0; i < accommodations.size(); ++i)
            accommodations.get(i).prettyPrint();
    }

    public void removeAccommodation(Scanner in) {
        System.out.println("enter the number of the accommodation you want to remove:");
        int idx = Integer.parseInt(in.nextLine());

        accommodationService.removeAccommodation(idx);
        System.out.println("accommodation removed!");
        auditService.addLog("remove accommodation");
    }

    public void printLogs(Scanner in) {
        System.out.println("how many logs to fetch? (leave blank for all)");
        String answer = in.nextLine();

        if(answer.length() == 0) {
            auditService.printLogs();
        }
        else {
            auditService.printLogs(Integer.parseInt(answer));
        }
    }

    public void addEvent(Scanner in) {
        System.out.println("is this a indoor event?");
        System.out.println("type 'yes' or 'no'\n");

        String answer = in.nextLine();

        if(!answer.equals("yes") && !answer.equals("no")) {
            System.out.println("input error");
        }
        else {
            if(answer.equals("yes")) {
                IndoorEvent newIndoorEvent = new IndoorEvent();

                System.out.println("what is the name of the event?");
                newIndoorEvent.setName(in.nextLine());

                System.out.println("what is the location of this event?");
                newIndoorEvent.setLocation(in.nextLine());

                System.out.println("what is the entry time? (hh:mm)");
                answer = in.nextLine() + ":00";
                newIndoorEvent.setEventTime(Time.valueOf(answer));

                System.out.println("what is the price for the ticket?");
                newIndoorEvent.setTicketPrice(Integer.parseInt(in.nextLine()));

                eventService.addEvent(newIndoorEvent);
                System.out.println("indoor event added succesfully!");
            }
            else {
                OutdoorEvent newOutdoorEvent = new OutdoorEvent();

                System.out.println("what is the name of the event?");
                newOutdoorEvent.setName(in.nextLine());

                System.out.println("what is the location of this event?");
                newOutdoorEvent.setLocation(in.nextLine());

                System.out.println("how many days does this event take place?");
                newOutdoorEvent.setDaysLength(Integer.parseInt(in.nextLine()));

                System.out.println("does this event have camping?");
                System.out.println("type 'yes' or 'no'\n");

                answer = in.nextLine();
                while(!answer.equals("yes") && !answer.equals("no")) {
                    System.out.println("input error\n");
                    System.out.println("type 'yes' or 'no'\n");
                    answer = in.nextLine();
                }
                newOutdoorEvent.setHasCamping(answer == "yes");

                eventService.addEvent(newOutdoorEvent);
                System.out.println("outdoor event added succesfully!");
            }
            auditService.addLog("event added");
        }
    }

    public void showEvents() {
        events = eventService.getEvents();

        for(int i = 0; i < events.size(); ++i) {
            if(eventService.isIndoor(events.get(i))) {
                IndoorEvent event = (IndoorEvent)events.get(i);
                event.prettyPrint();
            }
            else {
                OutdoorEvent event = (OutdoorEvent)events.get(i);
                event.prettyPrint();
            }
        }
    }
}
