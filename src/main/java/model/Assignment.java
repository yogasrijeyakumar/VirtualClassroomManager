package model;

import java.time.LocalDate;

public class Assignment {
    private final String id;
    private String description;
    private LocalDate dueDate;

    public Assignment(String id, String description, LocalDate dueDate) {
        this.id = id;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getId() { return id; }
    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }

    @Override
    public String toString() {
        return "Assignment{id='" + id + "', description='" + description + "', dueDate=" + dueDate + "}";
    }
}
