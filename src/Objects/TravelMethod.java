package Objects;

import java.sql.Time;
import java.util.List;
import java.util.Set;

public class TravelMethod {
    private static int IdIncrement = 0;
    private final int Id;
    private String name;
    private String price;
    private Set<Time> timeTable;

    TravelMethod() {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Set<Time> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(Set<Time> timeTable) {
        this.timeTable = timeTable;
    }

    public void addEntryToTimeTable(Time t) {
        Set<Time> temporary = this.getTimeTable();
        temporary.add(t);
        this.setTimeTable(temporary);
    }
}
