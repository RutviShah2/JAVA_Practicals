package Practicals_Lab.ClassFundamentals;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Student {
    private int id;
    private String name;
    private List<Course> courses;

    // Inner class Course
    class Course {
        private String courseId;
        private String courseName;

        Course(String courseId, String courseName) {
            this.courseId = courseId;
            this.courseName = courseName;
        }

        @Override
        public String toString() {
            return "[" + courseId + ": " + courseName + "]";
        }
    }

    // Constructor
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    // Method to add a course
    public void addCourse(String courseId, String courseName) {
        Course c = new Course(courseId, courseName);
        courses.add(c);
    }

    // Method-local inner class for semester enrollment
    public void enrollSemesterCourses(String semester, String[] courseList) {
        class SemesterEnrollment {
            void showEnrollment() {
                System.out.println("Enrolled in " + semester + " Courses:");
                for (String course : courseList) {
                    addCourse("C-" + course.hashCode(), course);
                    System.out.println(" - " + course);
                }
            }
        }
        SemesterEnrollment se = new SemesterEnrollment();
        se.showEnrollment();
    }

    // Anonymous inner class for scholarship eligibility
    public void checkScholarship(double gpa) {
        ScholarshipEligibility eligibility = new ScholarshipEligibility() {
            @Override
            public boolean isEligible() {
                return gpa > 8.0;
            }
        };
        System.out.println("Scholarship Eligible? " + eligibility.isEligible());
    }

    // Overriding equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // same reference
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return id == student.id; // compare by ID
    }

    // Overriding hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Overriding toString
    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', courses=" + courses + "}";
    }
}

// Functional interface for scholarship eligibility
interface ScholarshipEligibility {
    boolean isEligible();
}

// Main Class
public class StudentPortal {
    public static void main(String[] args) {
        // Create student objects (Heap memory)
        Student s1 = new Student(1, "Rutvi");
        Student s2 = new Student(2, "Poojan");

        // Enroll semester courses (Method-local inner class)
        s1.enrollSemesterCourses("Semester 1", new String[]{"Java", "DBMS"});
        s2.enrollSemesterCourses("Semester 1", new String[]{"Python", "Networks"});

        // Add courses directly (Inner class usage)
        s1.addCourse("C101", "Data Structures");

        // Anonymous inner class (Scholarship logic)
        s1.checkScholarship(9.1);
        s2.checkScholarship(7.5);

        // Object methods
        System.out.println(s1.toString());
        System.out.println(s1.equals(s2));
        System.out.println("HashCode of s1: " + s1.hashCode());

        // Garbage Collection Demo
        Student s3 = new Student(3, "Pooja");
        s3 = null; // Now eligible for Garbage Collection
        System.gc(); // Suggest GC (not guaranteed)

        System.out.println("Program Finished");
        System.out.println("24DCS120 RUTVI SHAH");
    }
}
