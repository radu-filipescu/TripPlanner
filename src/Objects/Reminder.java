package Objects;

public class Reminder {
    private static int IdIncrement = 0;
    private final int Id;
    private String title;
    private String description;
    private boolean done;

    public Reminder() {
        this.Id = ++IdIncrement;
        done = false;
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

    public boolean isDone() { return done; }

    public void setAsDone() { done = true; }

    public void prettyPrint() {
        System.out.println("*********************");
        System.out.println(Id + ". " + title);
        if(description.length() > 0)
            System.out.println(description);
        if(done) System.out.println("- completed -");
        else System.out.println("- not completed -");
    }
}
