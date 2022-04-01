package com.company;

import Objects.Remainder;
import Objects.TravelMethod;
import Objects.VisitingObjective;

import java.util.List;
import java.util.Scanner;

public class MainService {
    private List<VisitingObjective> objectives;
    private List<Remainder> remainder;
    private List<TravelMethod> travelMethods;

    public void addObjective(Scanner in) {
        VisitingObjective newObjective = new VisitingObjective();

        System.out.println("enter objective name: ");
        newObjective.setName(in.nextLine());

        System.out.println("enter objective location: ");
        newObjective.setLocation(in.nextLine());

        System.out.println("enter estimated time to visit (hours):");
        newObjective.setEstimatedTimeToVisit(Integer.parseInt(in.nextLine()));

        System.out.println("enter number of things to do before visiting this (0 if there are none):");
        int remainderCount = Integer.parseInt(in.nextLine());


    }
}
