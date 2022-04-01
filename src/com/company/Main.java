package com.company;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static List<String> commands = Arrays.asList("add objective", "list objectives", "mark objective as seen", "show details for objective",
            "add travel method", "show travel methods", "add remainder", "show remainders", "mark remainder as done", "remove remainder");

    public static void main(String[] args) throws IOException {
        boolean done = false;
        Scanner in = new Scanner(System.in);

        var mainService = new MainService();

        while(!done) {
            System.out.println("Insert command:");
            System.out.println("input 'help' to see commands\n");
            String command = in.nextLine().toLowerCase(Locale.ROOT);

            switch (command) {
                case "add objective" -> mainService.addObjective(in);
//                case "list objectives" -> mainService.listObjectives(in);
//                case "mark objective as seen" -> mainService.markObjectiveSeen(in);
//                case "show details for objective" -> mainService.showDetailsObjective(in);
//                case "add travel method" -> mainService.addTravelMethod(in);
//                case "show travel methods" -> mainService.showTravelMethods(in);
//                case "add remainder" -> mainService.addRemainder(in);
//                case "show remainders" -> mainService.showRemainders(in);
//                case "mark remainder as done" -> mainService.markRemainderDone(in);
//                case "remove remainder" -> mainService.removeRemainder(in);
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
