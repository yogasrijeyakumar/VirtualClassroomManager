package patterns.observer;

import model.Assignment;
import model.Classroom;
import model.Student;
import model.Submission;

public interface SubmissionListener {
    void onSubmission(Submission submission, Classroom classroom, Student student, Assignment assignment);
}
