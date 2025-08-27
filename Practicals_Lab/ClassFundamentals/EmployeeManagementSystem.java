package Practicals_Lab.ClassFundamentals;

// Base Employee Class
class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    // Static variable to count employees
    private static int employeeCount = 0;

    // Default constructor
    public Employee() {
        this(0, "Unknown", "Not Assigned", 0.0); // Constructor chaining
    }

    // Parameterized constructor
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        employeeCount++; // Increment count whenever an employee is created
    }

    // Getters and Setters (Encapsulation)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public static int getEmployeeCount() {
        return employeeCount;
    }

    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name +
                ", Dept: " + department + ", Salary: " + salary);
    }
}

// PermanentEmployee subclass
class PermanentEmployee extends Employee {
    private String benefits;

    public PermanentEmployee(int id, String name, String dept, double salary, String benefits) {
        super(id, name, dept, salary);  // Call parent constructor
        this.benefits = benefits;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Permanent, Benefits: " + benefits);
    }
}

// ContractEmployee subclass
class ContractEmployee extends Employee {
    private int contractDuration; // months

    public ContractEmployee(int id, String name, String dept, double salary, int contractDuration) {
        super(id, name, dept, salary);
        this.contractDuration = contractDuration;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Contract, Duration: " + contractDuration + " months");
    }
}

// Main Class
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        // Using default constructor
        Employee e1 = new Employee();
        e1.displayInfo();

        // Using parameterized constructor
        Employee e2 = new Employee(101, "Rutvi", "HR", 50000);
        e2.displayInfo();

        // Permanent employee
        Employee p1 = new PermanentEmployee(102, "Poojan", "Finance", 60000, "Health Insurance");
        p1.displayInfo();

        // Contract employee
        Employee c1 = new ContractEmployee(103, "Raj", "IT", 40000, 12);
        c1.displayInfo();

        // Using instanceof
        if (p1 instanceof PermanentEmployee) {
            System.out.println(p1.getName() + " is a Permanent Employee");
        }
        if (c1 instanceof ContractEmployee) {
            System.out.println(c1.getName() + " is a Contract Employee");
        }

        // Show employee count
        System.out.println("Total Employees Created: " + Employee.getEmployeeCount());
        System.out.println("24DCS120 RUTVI SHAH");
    }
}
