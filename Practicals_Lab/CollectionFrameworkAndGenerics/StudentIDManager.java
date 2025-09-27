import java.util.*;

public class StudentIDManager {
    private static HashSet<Integer> hashSet = new HashSet<>();
    private static TreeSet<Integer> treeSet = new TreeSet<>();
    private static LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
    private static Scanner sc = new Scanner(System.in);

    // Add a new Student ID
    public static void addID() {
        System.out.print("Enter Student ID to add: ");
        int id = sc.nextInt();
        if (hashSet.contains(id)) {
            System.out.println(" ID already exists! Duplicates not allowed.");
        } else {
            hashSet.add(id);
            treeSet.add(id);
            linkedHashSet.add(id);
            System.out.println("ID added successfully.");
        }
    }

    // Remove an existing Student ID
    public static void removeID() {
        System.out.print("Enter Student ID to remove: ");
        int id = sc.nextInt();
        if (hashSet.contains(id)) {
            hashSet.remove(id);
            treeSet.remove(id);
            linkedHashSet.remove(id);
            System.out.println("ID removed successfully.");
        } else {
            System.out.println("ID not found.");
        }
    }

    // Check if an ID exists
    public static void checkID() {
        System.out.print("Enter Student ID to check: ");
        int id = sc.nextInt();
        if (hashSet.contains(id)) {
            System.out.println(" ID exists in the collection.");
        } else {
            System.out.println("ID not found.");
        }
    }

    // Display all IDs
    public static void displayIDs() {
        System.out.println("1) HashSet (Unordered): " + hashSet);
        System.out.println("2) TreeSet (Sorted): " + treeSet);
        System.out.println("3) LinkedHashSet (Insertion order): " + linkedHashSet);
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Student ID Manager ---");
            System.out.println("1. Add Student ID");
            System.out.println("2. Remove Student ID");
            System.out.println("3. Check if ID exists");
            System.out.println("4. Display all IDs");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: addID(); break;
                case 2: removeID(); break;
                case 3: checkID(); break;
                case 4: displayIDs(); break;
                case 5: System.out.println("Exiting... Goodbye!"); break;
                default: System.out.println(" Invalid choice. Try again.");
            }
        } while (choice != 5);
        System.out.println("24DCS120_RUTVI SHAH");
    }
}
