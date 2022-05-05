package com.company;

import Objects.Reminder;
import Objects.TravelMethod;
import Objects.TravelMethods.LongDistance;
import Objects.TravelMethods.ShortDistance;
import Objects.VisitingObjective;
import com.company.services.ObjectiveService;

import java.sql.Time;
import java.util.*;

public class MainService {
    ObjectiveService objectiveService = new ObjectiveService();

    private ArrayList<VisitingObjective> objectives = new ArrayList<VisitingObjective>();
    private ArrayList<Reminder> reminders = new ArrayList<Reminder>();
    private ArrayList<TravelMethod> travelMethods = new ArrayList<TravelMethod>();

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
    }

    public void markObjectiveSeen(Scanner in) {
        System.out.println("enter the number of the objective you want to mark as seen");

        int idx = Integer.parseInt(in.nextLine());

        if(idx < 1 || idx > objectives.size()) {
            System.out.println("error, there is no objective with that number");
        }
        else {
            objectives.get(idx - 1).setSeen();

            System.out.println("objective number " + idx + " marked as visited!");
        }
    }

    public void showDetailsForObjective(Scanner in) {
        System.out.println("enter number of the objective you want to see details about");
        int idx = Integer.parseInt(in.nextLine());

        if(idx < 1 || idx > objectives.size()) {
            System.out.println("error, there is no objective with that number");
        }
        else {
            objectives.get(idx - 1).prettyPrint();
        }
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

                travelMethods.add(newTravelMethod);

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
                travelMethods.add(newTravelMethod);

                System.out.println("travel mean added succesfully!");
            }
        }
    }

    public void showTravelMethods() {
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
    }

    public void removeTravelMethod(Scanner in)
    {
        System.out.println("enter number of the travel mean you want to remove");
        int idx = Integer.parseInt(in.nextLine());

        if(idx < 1 || idx > travelMethods.size()) {
            System.out.println("error, there is no travel method with that number");
        }
        else {
            travelMethods.remove(idx);
        }
    }

    public void addReminder(Scanner in) {
        Reminder newReminder = new Reminder();

        System.out.println("add a title for this reminder:");
        newReminder.setTitle(in.nextLine());

        System.out.println("enter a description for this reminder: (or press enter to leave blank)");
        newReminder.setDescription(in.nextLine());

        reminders.add(newReminder);
        System.out.println("reminder added!");
    }

    public void showReminders(Scanner in) {
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
    }

    public void markReminderDone(Scanner in) {
        System.out.println("enter the number of the reminder you want to mark as done");

        int idx = Integer.parseInt(in.nextLine());

        if(idx < 1 || idx > reminders.size()) {
            System.out.println("error, there is no reminder with that number");
        }
        else {
            reminders.get(idx - 1).setAsDone();

            System.out.println("reminder number " + idx + " marked as done!");
        }
    }
}
