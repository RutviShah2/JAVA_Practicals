import java.util.ArrayList;
import java.util.Scanner;

public class ToDoListApp {
    private static ArrayList<String> tasks = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    // Add a new task
    public static void addTask() {
        System.out.print("Enter task to add: ");
        String task = sc.nextLine();
        tasks.add(task);
        System.out.println("Task added successfully!");
    }

    // Display all tasks
    public static void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("\n--- To-Do List ---");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    // Edit an existing task
    public static void editTask() {
        displayTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to edit: ");
        int index = sc.nextInt();
        sc.nextLine(); // consume newline

        if (index > 0 && index <= tasks.size()) {
            System.out.print("Enter new task description: ");
            String newTask = sc.nextLine();
            tasks.set(index - 1, newTask);
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    // Delete a task
    public static void deleteTask() {
        displayTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to delete: ");
        int index = sc.nextInt();
        sc.nextLine(); // consume newline

        if (index > 0 && index <= tasks.size()) {
            tasks.remove(index - 1);
            System.out.println("Task deleted successfully!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    // Main menu
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- To-Do List Menu ---");
            System.out.println("1. Add Task");
            System.out.println("2. Display Tasks");
            System.out.println("3. Edit Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    displayTasks();
                    break;
                case 3:
                    editTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);
        System.out.println("24DCS120_RUTVI SHAH");
    }
}
