package Objects;

import java.sql.Time;
import java.util.List;
import java.util.Set;

public class TravelMethod {
    private static int IdIncrement = 0;
    private final int Id;
    private String name;
    private String price;

    public TravelMethod() {
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
}
