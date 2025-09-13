// HospitalManagement.java

// Base Class
class Staff {
    String name;
    int id;

    // Constructor
    Staff(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Method to be overridden
    void work() {
        System.out.println("Staff is working in the hospital.");
    }
}

// Doctor Class
class Doctor extends Staff {
    String specialization;

    Doctor(String name, int id, String specialization) {
        super(name, id); // Call parent constructor
        this.specialization = specialization;
    }

    @Override
    void work() {
        System.out.println("Doctor " + name + " (Specialization: " + specialization + 
                           ") is treating patients.");
    }
}

// Nurse Class
class Nurse extends Staff {
    Nurse(String name, int id) {
        super(name, id); // Call parent constructor
    }

    @Override
    void work() {
        System.out.println("Nurse " + name + " is assisting doctors and caring for patients.");
    }
}

// Administrative Staff Class
class AdminStaff extends Staff {
    AdminStaff(String name, int id) {
        super(name, id); // Call parent constructor
    }

    @Override
    void work() {
        System.out.println("Admin Staff " + name + " is managing hospital records and appointments.");
    }
}

// Main Class
public class HospitalManagement {
    public static void main(String[] args) {
        // Polymorphism: base class reference to child objects
        Staff s1 = new Doctor("Dr. Mehta", 101, "Cardiology");
        Staff s2 = new Nurse("Sita", 102);
        Staff s3 = new AdminStaff("Ramesh", 103);

        // Dynamic Method Dispatch
        s1.work(); // Doctor's version
        s2.work(); // Nurse's version
        s3.work(); // Admin's version

        System.out.println("\n24DCS120_RUTVI SHAH");
    }
}