package model;

import java.time.LocalDateTime;

public class Submission {
    private final String studentId;
    private final String assignmentId;
    private final String classroomId;
    private final LocalDateTime submittedAt;

    public Submission(String studentId, String assignmentId, String classroomId, LocalDateTime submittedAt) {
        this.studentId = studentId;
        this.assignmentId = assignmentId;
        this.classroomId = classroomId;
        this.submittedAt = submittedAt;
    }

    public String getStudentId() { return studentId; }
    public String getAssignmentId() { return assignmentId; }
    public String getClassroomId() { return classroomId; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }

    @Override
    public String toString() {
        return "Submission{studentId='" + studentId + "', assignmentId='" + assignmentId + "', classroomId='" + classroomId + "', submittedAt=" + submittedAt + "}";
    }
}
