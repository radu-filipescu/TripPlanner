package Objects;

public class Accommodation {
    private int Id;
    private String name;
    private String location;
    private int pricePerNight;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public int getPrice() {
        return pricePerNight;
    }

    public void setPrice(int price) {
        this.pricePerNight = price;
    }

    public void prettyPrint() {
        System.out.println("---------------------");
        System.out.println(Id + ". *** " + name + " ***");
        System.out.println("location: " + location);
        System.out.println("price " + pricePerNight + " /night");
    }
}
