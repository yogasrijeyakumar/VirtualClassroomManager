package app;

import model.Assignment;
import model.Classroom;
import model.Student;
import model.Submission;
import patterns.observer.ConsoleNotificationListener;
import service.ClassroomService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ClassroomService service = new ClassroomService();
        service.registerListener(new ConsoleNotificationListener());
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        printHelp();
        while (running) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if (line == null) break;
            line = line.trim();
            if (line.isEmpty()) continue;
            try {
                String[] parts = line.split(" ", 2);
                String cmd = parts[0].toLowerCase();
                String rest = parts.length>1 ? parts[1].trim() : "";
                switch (cmd) {
                    case "help": printHelp(); break;
                    case "exit": running = false; break;
                    case "add_classroom": {
                        String[] p = rest.split(" ",2);
                        service.addClassroom(p[0], p.length>1? p[1] : "");
                        System.out.println("OK"); break;
                    }
                    case "add_student": {
                        String[] p = rest.split(" ",2);
                        service.addStudent(p[0], p.length>1? p[1] : "");
                        System.out.println("OK"); break;
                    }
                    case "enroll_student": {
                        String[] p = rest.split(" ",2);
                        service.enrollStudentToClassroom(p[0], p[1]);
                        System.out.println("OK"); break;
                    }
                    case "schedule_assignment": {
                        String[] p = rest.split(" ",4);
                        String classId = p[0], assignmentId = p[1];
                        LocalDate due = LocalDate.parse(p[2]);
                        String desc = p.length>3? p[3] : "";
                        service.scheduleAssignment(classId, assignmentId, desc, due);
                        System.out.println("OK"); break;
                    }
                    case "submit_assignment": {
                        String[] p = rest.split(" ",3);
                        service.submitAssignment(p[0], p[1], p[2]);
                        System.out.println("OK"); break;
                    }
                    case "list_classrooms": {
                        List<Classroom> cls = service.listClassrooms();
                        if (cls.isEmpty()) System.out.println("(none)");
                        cls.forEach(c -> System.out.println(c));
                        break;
                    }
                    case "list_students": {
                        List<Student> ss = service.listStudents(rest);
                        if (ss.isEmpty()) System.out.println("(none)");
                        ss.forEach(s -> System.out.println(s));
                        break;
                    }
                    case "list_assignments": {
                        List<Assignment> as = service.listAssignments(rest);
                        if (as.isEmpty()) System.out.println("(none)");
                        as.forEach(a -> System.out.println(a));
                        break;
                    }
                    case "list_submissions": {
                        String[] p = rest.split(" ",2);
                        List<Submission> subs = service.listSubmissions(p[0], p[1]);
                        if (subs.isEmpty()) System.out.println("(none)");
                        subs.forEach(s -> System.out.println(s));
                        break;
                    }
                    default:
                        System.out.println("Unknown command. Type 'help'.");
                }
            } catch (Exception ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
        scanner.close();
        System.out.println("Exiting.");
    }

    private static void printHelp() {
        System.out.println("Commands:");
        System.out.println("  add_classroom <id> <name>");
        System.out.println("  add_student <id> <name>");
        System.out.println("  enroll_student <studentId> <classId>");
        System.out.println("  schedule_assignment <classId> <assignmentId> <dueDate yyyy-MM-dd> <description>");
        System.out.println("  submit_assignment <studentId> <classId> <assignmentId>");
        System.out.println("  list_classrooms");
        System.out.println("  list_students <classId>");
        System.out.println("  list_assignments <classId>");
        System.out.println("  list_submissions <classId> <assignmentId>");
        System.out.println("  help");
        System.out.println("  exit");
    }
}
