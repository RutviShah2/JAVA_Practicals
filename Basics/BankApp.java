import java.util.Scanner;

class Bank {
    String name;
    int accNo;
    double balance;

    void openAccount(String n, int a, double b) {
        name = n;
        accNo = a;
        balance = b;
    }

    void deposit(double amt) {
        balance += amt;
        System.out.println("₹" + amt + " deposited.");
    }

    void withdraw(double amt) {
        if (amt <= balance) {
            balance -= amt;
            System.out.println("₹" + amt + " withdrawn.");
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    void display() {
        System.out.println("\nAccount Holder: " + name);
        System.out.println("Account No: " + accNo);
        System.out.println("Balance: ₹" + balance);
    }
}

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank acc = new Bank();

        acc.openAccount("Rutvii Shah", 1001, 5000);

        int choice;
        do {
            System.out.println("\n--- BANK MENU ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Display Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double d = sc.nextDouble();
                    acc.deposit(d);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double w = sc.nextDouble();
                    acc.withdraw(w);
                    break;
                case 3:
                    acc.display();
                    break;
                case 4:
                    System.out.println("Thank you, Rutvii!");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        } while (choice != 4);
    }
}
