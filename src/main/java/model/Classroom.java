package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
    private final String id;
    private String name;
    private final List<Student> students = new ArrayList<>();
    private final List<Assignment> assignments = new ArrayList<>();

    public Classroom(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Student> getStudents() { return students; }
    public List<Assignment> getAssignments() { return assignments; }

    public void addStudent(Student s) {
        if (s == null) return;
        if (!students.contains(s)) students.add(s);
    }

    public void addAssignment(Assignment a) {
        if (a == null) return;
        if (!assignments.contains(a)) assignments.add(a);
    }

    @Override
    public String toString() {
        return "Classroom{id='" + id + "', name='" + name + "', students=" + students.size() + ", assignments=" + assignments.size() + "}";
    }
}
