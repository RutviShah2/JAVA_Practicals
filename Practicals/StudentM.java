import java.util.*;
    
public class StudentM
{
    String name, id;
    List<Course> courses = new ArrayList<>();

    class Course {
        String code, title;
        Course(String code, String title)
        {
            this.code = code;
            this.title = title;
        }

        public String toString()
        {
            return code + " - " + title;
        }
    }

        StudentM (String name, String id)
    {
        this.name = name;
        this.id = id;
    }

    void enroll(String code, String title)
    {
        courses.add(new Course(code, title));
    }

     void semesterEnroll(String semester) 
     {
        class Semester {
            void show() {
                System.out.println(name + " - " + semester);
                for (Course c : courses) System.out.println("  " + c);
            }
        }
        new Semester().show();
    }

     void checkScholarship(double gpa)
      {
        Scholarship s = new Scholarship()
         {
            public boolean isEligible(double gpa) 
        {
                return gpa >= 8.5;
        }
        };
        System.out.println(name + (s.isEligible(gpa) ? " is eligible " : " is not eligible "));
    }
    interface Scholarship {
        boolean isEligible(double gpa);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        return id.equals(((Student) o).id);
    }

    public String toString() {
        return name + " (" + id + ") " + courses;
    }
     public static void main(String[] args) {
        Student s1 = new Student("Rutvi", "001");
        s1.enroll("CS101", "Java");
        s1.enroll("CS102", "DSA");

        s1.semesterEnroll("Sem 3");
        s1.checkScholarship(9.1);

        Student s2 = new Student("Poojan", "001");
        System.out.println("Same student? " + s1.equals(s2));

        System.out.println(s1);


     }
}
