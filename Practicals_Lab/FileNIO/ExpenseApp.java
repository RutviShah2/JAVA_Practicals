package FileNIO;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class Expense {
    String date;
    String category;
    double amount;
    String description;

    Expense(String category, double amount, String description) {
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String toString() {
        return date + " | " + category + " | " + amount + " | " + description;
    }
}

class ExpenseTracker {
    private static final String EXPENSE_FILE = "expenses.txt";

    // Log a new expense entry
    public static void logExpense(Expense e) {
        try (FileWriter fw = new FileWriter(EXPENSE_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(e.toString());
            System.out.println(" Expense logged successfully!");
        } catch (IOException ex) {
            System.out.println("Error logging expense: " + ex.getMessage());
        }
    }

    // Generate monthly summary
    public static void generateMonthlySummary() {
        Map<String, Double> categoryTotals = new HashMap<>();
        double total = 0;
        int daysCount = 0;
        String currentMonth = new SimpleDateFormat("yyyy-MM").format(new Date());

        try (BufferedReader br = new BufferedReader(new FileReader(EXPENSE_FILE))) {
            String line;
            Set<String> uniqueDays = new HashSet<>();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String date = parts[0].trim();
                    String category = parts[1].trim();
                    double amount = Double.parseDouble(parts[2].trim());

                    // Only include current month's entries
                    if (date.startsWith(currentMonth)) {
                        total += amount;
                        categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + amount);
                        uniqueDays.add(date);
                    }
                }
            }
            daysCount = uniqueDays.size();

            // Write summary to file
            String summaryFile = "summary_" + currentMonth + ".txt";
            try (PrintWriter out = new PrintWriter(new FileWriter(summaryFile))) {
                out.println(" Monthly Summary for " + currentMonth);
                out.println("Total Spent: " + total);
                out.println("Category Breakdown:");
                for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
                    out.println(" - " + entry.getKey() + ": " + entry.getValue());
                }
                out.println("Average Daily Spending: " + (daysCount > 0 ? total / daysCount : 0));
            }

            System.out.println("Monthly summary generated: " + summaryFile);

        } catch (IOException e) {
            System.out.println("Error generating summary: " + e.getMessage());
        }
    }
}

public class ExpenseApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nExpense Tracker");
            System.out.println("1. Add Expense");
            System.out.println("2. Generate Monthly Summary");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter category (Food, Travel, Shopping, etc.): ");
                    String category = sc.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();

                    Expense e = new Expense(category, amount, desc);
                    ExpenseTracker.logExpense(e);
                    break;

                case 2:
                    ExpenseTracker.generateMonthlySummary();
                    break;

                case 3:
                    System.out.println("Exiting... Byeee ");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 3);

        sc.close();

        System.out.println("24DCS120_RUTVI SHAH");
    }
}
