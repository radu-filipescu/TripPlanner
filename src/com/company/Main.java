package com.company;

import java.io.IOException;
import java.util.*;

public class Main {
    public static ArrayList<String> commands = new ArrayList<String>(Arrays.asList("add objective", "list objectives", "mark objective as seen", "show details for objective",
            "add travel method", "show travel methods", "remove travel method", "add reminder", "show reminders", "mark reminder as done", "remove reminder", "logs"));

    public static void main(String[] args) throws IOException {
        boolean done = false;
        Scanner in = new Scanner(System.in);

        var mainService = new MainService();

        System.out.println("\nWelcome to trip-planner!\n");

        while(!done) {
            System.out.println("Insert command:");
            System.out.println("input 'help' to see commands\n");
            String command = in.nextLine().toLowerCase(Locale.ROOT);

            switch (command) {
                case "add objective" -> mainService.addObjective(in);
                case "list objectives" -> mainService.listObjectives();
                case "mark objective as seen" -> mainService.markObjectiveSeen(in);
                case "show details for objective" -> mainService.showDetailsForObjective(in);
                case "add travel method" -> mainService.addTravelMethod(in);
                case "show travel methods" -> mainService.showTravelMethods();
                case "remove travel method" -> mainService.removeTravelMethod(in);
                case "add reminder" -> mainService.addReminder(in);
                case "show reminders" -> mainService.showReminders(in);
                case "mark reminder as done" -> mainService.markReminderDone(in);
                case "remove reminder" -> mainService.removeReminder(in);
                case "logs" -> mainService.printLogs(in);
                case "help" -> Main.printCommands();
                case "quit" -> done = true;
                default -> System.out.println("invalid command!\n");
            }
        }
    }

    private static void printCommands() {
        for (String command: commands) {
            System.out.println(command);
        }
        System.out.println();
    }
}
