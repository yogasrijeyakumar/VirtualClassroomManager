package patterns.observer;

import model.Assignment;
import model.Classroom;
import model.Student;
import model.Submission;
import patterns.singleton.LoggerSingleton;

import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class ConsoleNotificationListener implements SubmissionListener {
    private final Logger logger = LoggerSingleton.getLogger();

    @Override
    public void onSubmission(Submission submission, Classroom classroom, Student student, Assignment assignment) {
        String msg = String.format("Notification: Student '%s' (id=%s) submitted assignment '%s' (id=%s) for classroom '%s' at %s",
                student.getName(), student.getId(),
                assignment.getDescription(), assignment.getId(),
                classroom.getName(),
                submission.getSubmittedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(msg);
        logger.info(msg);
    }
}
