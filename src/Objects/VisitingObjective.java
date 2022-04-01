package Objects;

import java.util.List;

public class VisitingObjective {
    private static int IdIncrement = 0;
    private final int Id;
    private String name;
    private String location;
    private double estimatedTimeToVisit;
    private List<Remainder> toDoDependencies;

    public VisitingObjective() {
        this.Id = ++IdIncrement;
    }

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

    public List<Remainder> getToDoDependencies() {
        return toDoDependencies;
    }

    public void setToDoDependencies(List<Remainder> toDoDependencies) {
        this.toDoDependencies = toDoDependencies;
    }
}
