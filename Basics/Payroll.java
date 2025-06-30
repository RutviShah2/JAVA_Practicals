import java.util.Scanner;

class Employee {
    int empId;
    String name;
    double basic;

    void accept(int id, String n, double b) {
        empId = id;
        name = n;
        basic = b;
    }

    void generateSalarySlip() {
        double hra = 0.2 * basic;
        double da = 0.1 * basic;
        double total = basic + hra + da;

        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Basic Salary: ₹" + basic);
        System.out.println("HRA (20%): ₹" + hra);
        System.out.println("DA (10%): ₹" + da);
        System.out.println("Total Salary: ₹" + total);
        System.out.println("--------------------------");
    }
}

public class Payroll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee[] emp = new Employee[2];

        for (int i = 0; i < 2; i++) {
            emp[i] = new Employee();
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Basic Salary: ");
            double basic = sc.nextDouble();

            emp[i].accept(id, name, basic);
        }

        System.out.println("\n--- Salary Slips ---");
        for (Employee e : emp) {
            e.generateSalarySlip();
        }
    }
}
