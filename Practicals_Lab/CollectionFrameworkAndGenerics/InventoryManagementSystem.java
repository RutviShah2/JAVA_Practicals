import java.util.*;

// Sellable interface
interface Sellable {
    double getPrice();
    int getQuantity();
    void setQuantity(int quantity);
    String getName();
}

// Electronics, Clothing, Groceries implementing Sellable
class Electronics implements Sellable {
    private String name;
    private double price;
    private int quantity;

    public Electronics(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Electronics: " + name + " | Price: " + price + " | Qty: " + quantity;
    }
}

class Clothing implements Sellable {
    private String name;
    private double price;
    private int quantity;

    public Clothing(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Clothing: " + name + " | Price: " + price + " | Qty: " + quantity;
    }
}

class Groceries implements Sellable {
    private String name;
    private double price;
    private int quantity;

    public Groceries(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Grocery: " + name + " | Price: " + price + " | Qty: " + quantity;
    }
}

// Generic Inventory class
class Inventory<T extends Sellable> {
    private List<T> items = new ArrayList<>();

    // Add item
    public void addItem(T item) {
        items.add(item);
        System.out.println(" Added: " + item);
    }

    // Update quantity
    public void updateItem(String name, int newQty) {
        for (T item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                item.setQuantity(newQty);
                System.out.println(" Updated: " + item);
                return;
            }
        }
        System.out.println(" Item not found: " + name);
    }

    // Delete item
    public void deleteItem(String name) {
        Iterator<T> it = items.iterator();
        while (it.hasNext()) {
            T item = it.next();
            if (item.getName().equalsIgnoreCase(name)) {
                it.remove();
                System.out.println("🗑 Deleted: " + item);
                return;
            }
        }
        System.out.println(" Item not found: " + name);
    }

    // Display items
    public void displayItems() {
        System.out.println("\n Inventory List:");
        for (T item : items) {
            System.out.println(item);
        }
    }

    public List<T> getItems() {
        return items;
    }
}
class InventoryUtils {
    public static double calculateTotalValue(List<? extends Sellable> items) {
        double total = 0;
        for (Sellable item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}

//  Type Erasure Demo
class TypeErasureDemo<T> {
    // Can't create generic arrays
    // T[] arr = new T[10];   // Compile-time error

    // Workaround: use Object[] or ArrayList<T>
    private Object[] arr;

    public TypeErasureDemo(int size) {
        arr = new Object[size];
    }

    public void set(int index, T value) {
        arr[index] = value;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) arr[index]; // unchecked cast
    }
}

// Main class
public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory<Electronics> electronicsInventory = new Inventory<>();
        Inventory<Clothing> clothingInventory = new Inventory<>();
        Inventory<Groceries> groceryInventory = new Inventory<>();

        // Adding items
        electronicsInventory.addItem(new Electronics("Laptop", 60000, 5));
        clothingInventory.addItem(new Clothing("T-Shirt", 500, 20));
        groceryInventory.addItem(new Groceries("Rice", 60, 50));

        // Updating
        clothingInventory.updateItem("T-Shirt", 25);

        // Deleting
        groceryInventory.deleteItem("Rice");

        // Display
        electronicsInventory.displayItems();
        clothingInventory.displayItems();
        groceryInventory.displayItems();

        // Calculate total value using Wildcards
        double total = 0;
        total += InventoryUtils.calculateTotalValue(electronicsInventory.getItems());
        total += InventoryUtils.calculateTotalValue(clothingInventory.getItems());
        total += InventoryUtils.calculateTotalValue(groceryInventory.getItems());

        System.out.println("\n Total Inventory Value: " + total);

        // Type Erasure Demo
        TypeErasureDemo<String> demo = new TypeErasureDemo<>(3);
        demo.set(0, "Hello");
        demo.set(1, "World");
        System.out.println("\n Type Erasure Demo Output: " + demo.get(0) + " " + demo.get(1));
        System.out.println("24DCS120_RUTVI SHAH");  
    }
}
