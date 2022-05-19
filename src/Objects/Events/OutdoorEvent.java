package Objects.Events;

import Objects.Event;

public class OutdoorEvent extends Event {
    private int daysLength;
    private boolean hasCamping;

    public int getDaysLength() {
        return daysLength;
    }

    public void setDaysLength(int daysLength) {
        this.daysLength = daysLength;
    }

    public boolean hasCamping() {
        return hasCamping;
    }

    public void setHasCamping(boolean hasCamping) {
        this.hasCamping = hasCamping;
    }

    public void prettyPrint() {
        System.out.println("_-_-_-_-_-_-_-_");
        System.out.println(Id + ". " + name);
        System.out.println("type: outdoor event");
        System.out.println("location: " + location);
        System.out.println("it takes " + daysLength + " days");
        if(hasCamping)
            System.out.println("it has camping facilities");
    }
}
