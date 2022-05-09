package Objects;

public class Reminder {
    private int Id;
    private int objectiveId;
    private String title;
    private String description;
    private boolean done;

    public Reminder() {
        done = false;
        objectiveId = 0;
    }

    public Reminder(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setId(int idx) { Id = idx; }

    public int getId(int idx) { return Id; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setObjectiveId(int id) { objectiveId = id; }

    public int getObjectiveId() { return objectiveId; }

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
