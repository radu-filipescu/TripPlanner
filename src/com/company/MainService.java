package com.company;

import Objects.Reminder;
import Objects.TravelMethod;
import Objects.VisitingObjective;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainService {
    private ArrayList<VisitingObjective> objectives = new ArrayList<VisitingObjective>();
    private ArrayList<Reminder> reminder = new ArrayList<Reminder>();
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

        // add the new objective to the list
        objectives.add(newObjective);
    }

    public void listObjectives() {
        for (VisitingObjective obj: objectives) {
            obj.prettyPrint();

        }
    }
}
