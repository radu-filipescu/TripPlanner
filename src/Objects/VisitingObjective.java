package Objects;

import java.util.ArrayList;
import java.util.List;

public class VisitingObjective {
    private int Id;
    private String name;
    private String location;
    private double estimatedTimeToVisit;
    private ArrayList<Reminder> toDoDependencies;
    private boolean wasSeen;

    public void setId(int id) { Id = id; }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getEstimatedTimeToVisit() {
        return estimatedTimeToVisit;
    }

    public void setEstimatedTimeToVisit(double estimatedTimeToVisit) {
        this.estimatedTimeToVisit = estimatedTimeToVisit;
    }

    public ArrayList<Reminder> getToDoDependencies() {
        return toDoDependencies;
    }

    public void setToDoDependencies(ArrayList<Reminder> toDoDependencies) {
        this.toDoDependencies = toDoDependencies;
    }

    public void setSeen() { wasSeen = true; }

    public boolean getSeen() { return wasSeen; }

    public void prettyPrint() {
        String delimiter = "#############################################";
        System.out.println(delimiter + "\n");
        System.out.println(Id + ". " + name);

        System.out.println("location: " + location);
        System.out.println("duration: " + estimatedTimeToVisit + "h\n");

        if(toDoDependencies.size() > 0) {
            System.out.println("you have " + toDoDependencies.size() + " reminders set before you visit this");

            for (Reminder rem : toDoDependencies) {
                rem.prettyPrint();
            }
        }

        System.out.println(delimiter);
    }
}
