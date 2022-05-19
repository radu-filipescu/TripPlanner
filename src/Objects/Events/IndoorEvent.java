package Objects.Events;
import Objects.Event;

import java.sql.Time;

public class IndoorEvent extends Event {
    private Time eventTime;
    private int ticketPrice;

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void prettyPrint() {
        System.out.println("_-_-_-_-_-_-_-_");
        System.out.println(Id + ". " + name);
        System.out.println("type: indoor event at " + eventTime);
        System.out.println("location: " + location);
        System.out.println("price: " + ticketPrice);
    }
}
