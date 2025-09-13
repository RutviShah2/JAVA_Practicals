package ExceptionHandling;

import java.util.*;

// Custom checked exception for product not found
class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

// Custom unchecked exception for insufficient stock
class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}

// Product class
class Product {
    private int id;
    private String name;
    private int quantity;

    public Product(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }

    public void reduceQuantity(int amount) {
        this.quantity -= amount;
    }

    @Override
    public String toString() {
        return "Product[ID=" + id + ", Name=" + name + ", Quantity=" + quantity + "]";
    }
}

// Inventory class
class Inventory {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public void purchaseProduct(int productId, int quantityToPurchase) throws ProductNotFoundException {
        Product product = null;

        // Find product by ID
        for (Product p : products) {
            if (p.getId() == productId) {
                product = p;
                break;
            }
        }

        // If product not found → throw checked exception
        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found!");
        }

        // If insufficient stock → throw unchecked exception
        if (quantityToPurchase > product.getQuantity()) {
            throw new InsufficientStockException("Not enough stock for " + product.getName());
        }

        // Otherwise, reduce stock
        product.reduceQuantity(quantityToPurchase);
        System.out.println("Purchased " + quantityToPurchase + " of " + product.getName() + ". Remaining: " + product.getQuantity());
    }

    public void showInventory() {
        for (Product p : products) {
            System.out.println(p);
        }
    }
}

// Main class to test
public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Add some products
        inventory.addProduct(new Product(101, "Laptop", 5));
        inventory.addProduct(new Product(102, "Phone", 10));
        inventory.addProduct(new Product(103, "Headphones", 2));

        inventory.showInventory();

        try {
            inventory.purchaseProduct(102, 3);  
            inventory.purchaseProduct(105, 1);  
            inventory.purchaseProduct(101, 10);
        } 
        catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("End of main program.");
        System.out.println("24DCS120_RUTVI SHAH");
    }
}
