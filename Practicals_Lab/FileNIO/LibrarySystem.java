package FileNIO;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class LibraryLogger {
    private static final String LOG_FILE = "library_logs.txt";

    // Method to add log entry
    public static void logAction(String action, String bookName, String studentName) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            out.println(timestamp + " - Book \"" + bookName + "\" " + action + " by " + studentName);

        } catch (IOException e) {
            System.out.println("Error writing log: " + e.getMessage());
        }
    }

    // Method to read all logs
    public static void displayLogs() {
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            System.out.println("\n---- Library Logs ----");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("----------------------\n");
        } catch (IOException e) {
            System.out.println("No logs found or error reading file.");
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n Library Logging System");
            System.out.println("1. Issue Book");
            System.out.println("2. Return Book");
            System.out.println("3. View Logs");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String student1 = sc.nextLine();
                    System.out.print("Enter book name: ");
                    String book1 = sc.nextLine();
                    LibraryLogger.logAction("issued", book1, student1);
                    System.out.println(" Book issued and logged successfully!");
                    break;

                case 2:
                    System.out.print("Enter student name: ");
                    String student2 = sc.nextLine();
                    System.out.print("Enter book name: ");
                    String book2 = sc.nextLine();
                    LibraryLogger.logAction("returned", book2, student2);
                    System.out.println(" Book returned and logged successfully!");
                    break;

                case 3:
                    LibraryLogger.displayLogs();
                    break;

                case 4:
                    System.out.println("Exiting system... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again!");
            }
        } while (choice != 4);

        sc.close();
        System.out.println("24DCS120_RUTVI SHAH");
    }
}
