class Student {
    String name = "Rutvii";
    void display() {
        System.out.println("Name: " + name);
    }
}
public class TestStudent {
    public static void main(String[] args) {
        Student s = new Student();
        s.display();
    }
}
