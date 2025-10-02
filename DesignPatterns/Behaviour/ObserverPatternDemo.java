// ObserverPatternDemo.java
import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(String message);
}

class Student implements Observer {
    private String name;
    public Student(String name) { this.name = name; }
    public void update(String message) {
        System.out.println(name + " received update: " + message);
    }
}

class Teacher {
    private List<Observer> students = new ArrayList<>();
    public void addStudent(Observer s) { students.add(s); }
    public void removeStudent(Observer s) { students.remove(s); }
    public void sendNotification(String message) {
        for (Observer s : students) s.update(message);
    }
}

public class ObserverPatternDemo {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        Student s1 = new Student("Alice");
        Student s2 = new Student("Bob");
        teacher.addStudent(s1);
        teacher.addStudent(s2);

        teacher.sendNotification("Homework is due tomorrow!");
    }
}
