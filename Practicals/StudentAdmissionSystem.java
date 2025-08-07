import java.util.ArrayList;
class Student {
    private String name;
    private String course;
    private String aadhaarNumber;

    public Student(String name, String course, String aadhaarNumber) {
        this.name = name;
        this.course = course;
        this.aadhaarNumber = aadhaarNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student other = (Student) obj;
        return this.aadhaarNumber.equals(other.aadhaarNumber);
    }
    @Override
    public int hashCode() {
        return aadhaarNumber.hashCode();
    }
    @Override
    public String toString() {
        return name + " - " + course + " -Aadhaar: " +aadhaarNumber;
    }
    public String getName() {
        return name;
    }
    public String getAadhaar() {
        return aadhaarNumber;
    }
}

public class StudentAdmissionSystem {
    private ArrayList<Student> students = new ArrayList<>();
    public void addStudent(Student newStudent) {
        if (students.contains(newStudent)) {
            System.out.println("Student already exists: " +newStudent.getName() + " (Aadhaar:" + newStudent.getAadhaar() + ")");
        } 
        else {
            students.add(newStudent);
            System.out.println("Student added: " + newStudent.getName() + "(Aadhaar: " + newStudent.getAadhaar() + ")");
        }
    }
    public static void main(String[] args) {
        StudentAdmissionSystem system = new StudentAdmissionSystem();
        Student riya = new Student("Riya", "B.Tech IT", "1234-5678-9012");
        system.addStudent(riya);
        Student priya = new Student("Priya", "BBA", "1234-5678-9012");
        system.addStudent(priya);
        Student anil = new Student("Anil", "B.Tech CSE", "2345-6789-0123");
        system.addStudent(anil);
    }
}
