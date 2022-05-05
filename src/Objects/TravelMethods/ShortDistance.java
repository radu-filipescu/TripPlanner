package Objects.TravelMethods;

// this class will inherit from the base class TravelMethod
// and it's meant to represent the means of travel used for
// short distances (metro/city bus/taxi)

import Objects.TravelMethod;

import java.sql.Time;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ShortDistance extends TravelMethod {
    private TreeSet<Time> timeTable;

    public TreeSet<Time> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TreeSet<Time> timeTable) {
        this.timeTable = timeTable;
    }

    public void addEntryToTimeTable(Time t) {
        TreeSet<Time> temporary = this.getTimeTable();
        temporary.add(t);
        this.setTimeTable(temporary);
    }
}
