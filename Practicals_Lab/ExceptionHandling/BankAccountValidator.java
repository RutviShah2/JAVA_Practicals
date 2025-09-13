package ExceptionHandling;

// Custom checked exception for invalid transactions
class InvalidTransactionException extends Exception {
    public InvalidTransactionException(String message) {
        super(message);
    }
}

// Custom unchecked exception for insufficient funds
class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

// BankAccount class
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) throws InvalidTransactionException {
        if (amount <= 0) {
            throw new InvalidTransactionException("Deposit amount must be positive!");
        }
        balance += amount;
        System.out.println("Deposited: " + amount + " | Current Balance: " + balance);
    }

    // Withdraw method
    public void withdraw(double amount) throws InvalidTransactionException {
        if (amount <= 0) {
            throw new InvalidTransactionException("Withdrawal amount must be positive!");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient balance! Current balance: " + balance);
        }
        balance -= amount;
        System.out.println("Withdrew: " + amount + " | Current Balance: " + balance);
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }
}

// Main class to test
public class BankAccountValidator {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(5000); // starting balance

        try {
            account.deposit(2000);    
            account.withdraw(1000);   
            account.deposit(-500);   
            account.withdraw(0);     
            account.withdraw(10000);
        } 
        catch (InvalidTransactionException e) {
            System.out.println("Invalid Transaction: " + e.getMessage());
        } 
        catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
        finally {
            System.out.println("Transaction session finished. Final Balance: " + account.getBalance());
        }
        System.out.println("24DCS120_RUTVI_SHAH" );
    }
}
