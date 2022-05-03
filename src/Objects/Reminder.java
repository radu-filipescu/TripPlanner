package Objects;

public class Reminder {
    private static int IdIncrement = 0;
    private final int Id;
    private String title;
    private String description;

    public Reminder() {
        this.Id = ++IdIncrement;
    }

    public Reminder(String title, String description) {
        this.Id = ++IdIncrement;
        this.title = title;
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void prettyPrint() {
        System.out.println("*********************");
        System.out.println(title);
        System.out.println(description + "\n");
    }
}
