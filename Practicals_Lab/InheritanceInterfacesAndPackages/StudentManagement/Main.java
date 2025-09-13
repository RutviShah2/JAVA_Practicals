package StudentManagement;

import StudentManagement.Student.Details.Student;
import StudentManagement.Student.Services.StudentServices;

public class Main {
    public static void main(String[] args) {
        StudentServices service = new StudentServices();

        Student s1 = new Student(1, "Rutvi", 92);
        Student s2 = new Student(2, "Poojan", 76);

        service.registerStudent(s1);
        service.registerStudent(s2);

        service.listStudents();

        service.searchById(2);
        service.searchById(5);

        System.out.println("24DCS120_RUTVI SHAH");
    }
}
