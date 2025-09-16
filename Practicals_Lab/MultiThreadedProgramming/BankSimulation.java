package MultithreadedProgramming;
class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(int amount) {
        System.out.println(Thread.currentThread().getName() + " is depositing " + amount);
        balance += amount;
        System.out.println("Balance after deposit: " + balance);
    }

    public synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " is trying to withdraw " + amount);
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Balance after withdrawal: " + balance);
        } else {
            System.out.println("Insufficient funds! Withdrawal failed.");
        }
    }

    public int getBalance() {
        return balance;
    }
}

class DepositTask implements Runnable {
    private BankAccount account;
    private int amount;

    public DepositTask(BankAccount account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        account.deposit(amount);
    }
}

class WithdrawTask implements Runnable {
    private BankAccount account;
    private int amount;

    public WithdrawTask(BankAccount account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        account.withdraw(amount);
    }
}

public class BankSimulation {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(100); 

        Thread t1 = new Thread(new DepositTask(account, 50), "Thread-Deposit-1");
        Thread t2 = new Thread(new WithdrawTask(account, 30), "Thread-Withdraw-1");
        Thread t3 = new Thread(new WithdrawTask(account, 150), "Thread-Withdraw-2");
        Thread t4 = new Thread(new DepositTask(account, 200), "Thread-Deposit-2");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        System.out.println("24DCS120_RUTVI SHAH");
    }
}
