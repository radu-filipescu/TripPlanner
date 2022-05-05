package Objects;

import java.sql.Time;
import java.util.List;
import java.util.Set;

public class TravelMethod {
    private int Id;
    private String name;
    private String price;

    public void setId(int id) { Id = id; }

    public int getId() { return Id; }

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
