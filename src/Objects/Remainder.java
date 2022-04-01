package Objects;

import java.awt.image.IndexColorModel;

public class Remainder {
    private static int IdIncrement = 0;
    private final int Id;
    private String title;

    public Remainder() {
        this.Id = ++IdIncrement;
    }

    public Remainder(String title, String description) {
        this.Id = ++IdIncrement;
        this.title = title;
        this.description = description;
    }

    private String description;

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
}
