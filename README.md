# Virtual Classroom Manager - Final

This project implements the Virtual Classroom Manager programming exercise.
Features:
- Console-based CLI app.
- Commands: add_classroom, add_student, enroll_student, schedule_assignment, submit_assignment, list_*.
- Observer pattern: notifications on submission.
- Logger singleton used across services.

How to compile & run (using javac):
```bash
# from project root
javac -d out src/main/java/**/*.java
java -cp out app.App
```

Example session:
```
add_classroom C1 Physics
add_student S1 Alice
enroll_student S1 C1
schedule_assignment C1 A1 2025-10-05 "Chapter 1"
submit_assignment S1 C1 A1
list_submissions C1 A1
exit
```
