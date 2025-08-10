class pract1
 {
    private String id;
    private String name;
    private String department;
    private double salary;

    static int employeeCount=0;

    public pract1(String id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        employeeCount++; 
    }

    public pract1()
    {
      this("0","xyz", "not assigned",0.0);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public void displayDetails() {
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Department: " + this.department);
        System.out.println("Salary: ₹" + this.salary);
    }

    public class ContractEmployee extends pract1 
    {
    private int contractDuration;

    public ContractEmployee(String 
    id, String name, String department, double salary, int contractDuration)
     {
        super(id, name, department, salary);
        this.contractDuration = contractDuration;
     }

    public void showContractDetails()
    {
        System.out.println("Contract Duration: " + contractDuration + " months");
    }
  }

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Employee emp1 = new Employee();
        emp1.displayDetails();

        System.out.println();
        Employee emp2 = new Employee(101, "Rutvi", "IT", 50000);
        emp2.displayDetails();

        System.out.println();
        Employee emp3 = new PermanentEmployee(102, "Poojan", "HR", 60000, "Health, Bonus");
        emp3.displayDetails();

        if (emp3 instanceof PermanentEmployee) {
            ((PermanentEmployee) emp3).showBenefits();
        }

        System.out.println();

         pract1 emp4 = new ContractEmployee(103, "xyz", "Marketing", 40000, 12);
        emp4.displayDetails();

        if (emp4 instanceof ContractEmployee) {
            ((ContractEmployee) emp4).showContractDetails();
        }

        System.out.println("\nTotal Employees Created: " + Employee.employeeCount);
    }
}

}

    


