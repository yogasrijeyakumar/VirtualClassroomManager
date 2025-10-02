package service;

import model.Assignment;
import model.Classroom;
import model.Student;
import model.Submission;
import patterns.observer.SubmissionListener;
import patterns.singleton.LoggerSingleton;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;

public class ClassroomService {
    private final Map<String, Classroom> classrooms = new HashMap<>();
    private final Map<String, Student> students = new HashMap<>();
    private final List<Submission> submissions = new ArrayList<>();
    private final List<SubmissionListener> listeners = new ArrayList<>();
    private final Logger logger = LoggerSingleton.getLogger();

    public void addClassroom(String id, String name) {
        validateId(id);
        if (classrooms.containsKey(id)) throw new IllegalArgumentException("Classroom id exists: " + id);
        classrooms.put(id, new Classroom(id, name));
        logger.info("Added classroom: " + id);
    }

    public void addStudent(String id, String name) {
        validateId(id);
        if (students.containsKey(id)) throw new IllegalArgumentException("Student id exists: " + id);
        students.put(id, new Student(id, name));
        logger.info("Added student: " + id);
    }

    public void enrollStudentToClassroom(String studentId, String classroomId) {
        Student s = students.get(studentId);
        Classroom c = classrooms.get(classroomId);
        if (s == null) throw new IllegalArgumentException("Unknown student: " + studentId);
        if (c == null) throw new IllegalArgumentException("Unknown classroom: " + classroomId);
        c.addStudent(s);
        logger.info("Enrolled " + studentId + " to " + classroomId);
    }

    public void scheduleAssignment(String classroomId, String assignmentId, String description, LocalDate dueDate) {
        validateId(assignmentId);
        Classroom c = classrooms.get(classroomId);
        if (c == null) throw new IllegalArgumentException("Unknown classroom: " + classroomId);
        for (Assignment a : c.getAssignments()) {
            if (a.getId().equals(assignmentId)) throw new IllegalArgumentException("Assignment id exists: " + assignmentId);
        }
        Assignment a = new Assignment(assignmentId, description, dueDate);
        c.addAssignment(a);
        logger.info("Scheduled assignment " + assignmentId + " for " + classroomId);
    }

    public void submitAssignment(String studentId, String classroomId, String assignmentId) {
        Student s = students.get(studentId);
        Classroom c = classrooms.get(classroomId);
        if (s == null) throw new IllegalArgumentException("Unknown student: " + studentId);
        if (c == null) throw new IllegalArgumentException("Unknown classroom: " + classroomId);
        Assignment assignment = null;
        for (Assignment a : c.getAssignments()) {
            if (a.getId().equals(assignmentId)) assignment = a;
        }
        if (assignment == null) throw new IllegalArgumentException("Unknown assignment: " + assignmentId);
        Submission sub = new Submission(studentId, assignmentId, classroomId, LocalDateTime.now());
        submissions.add(sub);
        logger.info("Submission recorded: " + sub);
        for (SubmissionListener l : listeners) {
            try { l.onSubmission(sub, c, s, assignment); } catch (Exception ex) { logger.warning(ex.getMessage()); }
        }
    }

    public List<Classroom> listClassrooms() { return new ArrayList<>(classrooms.values()); }
    public List<Student> listStudents(String classroomId) {
        Classroom c = classrooms.get(classroomId);
        if (c == null) throw new IllegalArgumentException("Unknown classroom: " + classroomId);
        return new ArrayList<>(c.getStudents());
    }
    public List<Assignment> listAssignments(String classroomId) {
        Classroom c = classrooms.get(classroomId);
        if (c == null) throw new IllegalArgumentException("Unknown classroom: " + classroomId);
        return new ArrayList<>(c.getAssignments());
    }
    public List<Submission> listSubmissions(String classroomId, String assignmentId) {
        List<Submission> res = new ArrayList<>();
        for (Submission s : submissions) {
            if (s.getClassroomId().equals(classroomId) && s.getAssignmentId().equals(assignmentId)) res.add(s);
        }
        return res;
    }

    public void registerListener(SubmissionListener listener) {
        if (listener == null) return;
        listeners.add(listener);
    }

    private void validateId(String id) {
        if (id == null || id.trim().isEmpty()) throw new IllegalArgumentException("id must not be empty");
        if (id.contains(" ")) throw new IllegalArgumentException("id must not contain spaces");
    }
}
