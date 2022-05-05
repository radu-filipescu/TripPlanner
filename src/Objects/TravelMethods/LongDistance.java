package Objects.TravelMethods;

// this class will inherit from the base class TravelMethod
// and it's meant to represent the means of travel used to get
// to a certain destination ( airplane/ship/car )

import Objects.TravelMethod;

import java.sql.Time;

public class LongDistance extends TravelMethod {
    private Time departureTime;
    private Time returnTime;
    private boolean requiresPassport;
    private boolean requiresCheckIn;

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Time returnTime) {
        this.returnTime = returnTime;
    }

    public boolean isRequiresPassport() {
        return requiresPassport;
    }

    public void setRequiresPassport(boolean requiresPassport) {
        this.requiresPassport = requiresPassport;
    }

    public boolean isRequiresCheckIn() {
        return requiresCheckIn;
    }

    public void setRequiresCheckIn(boolean requiresCheckIn) {
        this.requiresCheckIn = requiresCheckIn;
    }
}
