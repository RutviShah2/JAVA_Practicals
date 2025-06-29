class bankAccount {
    String name;
    double balance;

    void openAccount(String n, double bal) {
        name = n;
        balance = bal;
    }

    void deposit(double amt) {
        balance += amt;
    }

    void withdraw(double amt) {
        if (balance >= amt)
            balance -= amt;
        else
            System.out.println("Insufficient Balance!");
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Balance: ₹" + balance);
    }

    public static void main(String[] args) {
        bankAccount acc = new bankAccount();
        acc.openAccount("Rutvi", 5000);
        acc.deposit(1000);
        acc.withdraw(2000);
        acc.display();
    }
}
