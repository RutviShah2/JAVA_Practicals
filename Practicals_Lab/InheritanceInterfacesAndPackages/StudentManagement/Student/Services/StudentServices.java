package StudentManagement.Student.Services;

import StudentManagement.Student.Details.Student;
// Update the import to match the actual package of GradeCalculator
// For example, if GradeCalculator is in StudentManagement.Student.Utility:
import StudentManagement.Student.Utility.GradeCalculator;
import java.util.ArrayList;

public class StudentServices {
    private ArrayList<Student> students = new ArrayList<>();

    public void registerStudent(Student s) {
        students.add(s);
        System.out.println("Student registered: " + s.getName());
    }

    public void listStudents() {
        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            s.display();
            System.out.println("Grade: " + GradeCalculator.calculateGrade(s.getMarks()));
        }
    }

    public void searchById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("\nStudent Found:");
                s.display();
                return;
            }
        }
        System.out.println("Student not found with ID: " + id);
    }
}
