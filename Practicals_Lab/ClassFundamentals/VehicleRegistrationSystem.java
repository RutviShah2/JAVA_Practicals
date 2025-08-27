package Practicals_Lab.ClassFundamentals;

import java.lang.annotation.*;
import java.lang.reflect.*;

// Step 1: Define custom annotation
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface VehicleInfo {
    String value() default "Important Field";
}

// Step 2: Base Vehicle class
abstract class Vehicle {
    private final String registrationNumber; // Immutable field
    @VehicleInfo("Owner of the Vehicle")
    private String ownerName;
    @VehicleInfo("Model of the Vehicle")
    private String model;

    // Constructor
    public Vehicle(String registrationNumber, String ownerName, String model) {
        this.registrationNumber = registrationNumber;
        this.ownerName = ownerName;
        this.model = model;
    }

    // Final method (cannot be overridden)
    public final String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getOwnerName() { return ownerName; }
    public String getModel() { return model; }

    // Can be overridden by subclasses
    public void displayDetails() {
        System.out.println("Reg No: " + registrationNumber + ", Owner: " + ownerName + ", Model: " + model);
    }
}

// Step 3: Subclasses
class Car extends Vehicle {
    public Car(String regNo, String owner, String model) {
        super(regNo, owner, model);
    }
    @Override
    public void displayDetails() {
        System.out.println("Car => " + getRegistrationNumber() + ", Owner: " + getOwnerName() + ", Model: " + getModel());
    }
}

class Bike extends Vehicle {
    public Bike(String regNo, String owner, String model) {
        super(regNo, owner, model);
    }
    @Override
    public void displayDetails() {
        System.out.println("Bike => " + getRegistrationNumber() + ", Owner: " + getOwnerName() + ", Model: " + getModel());
    }
}

class Truck extends Vehicle {
    public Truck(String regNo, String owner, String model) {
        super(regNo, owner, model);
    }
    @Override
    public void displayDetails() {
        System.out.println("Truck => " + getRegistrationNumber() + ", Owner: " + getOwnerName() + ", Model: " + getModel());
    }
}

// Step 4: Final utility class
final class VehicleUtils {
    public static void printAnnotatedFields(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println("Inspecting annotations for class: " + clazz.getSimpleName());

        // Walk up hierarchy (since Vehicle fields are private)
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(VehicleInfo.class)) {
                    VehicleInfo annotation = field.getAnnotation(VehicleInfo.class);
                    field.setAccessible(true);
                    try {
                        System.out.println(annotation.value() + " = " + field.get(obj));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
    }
}

// Step 5: Main class
public class VehicleRegistrationSystem {
    public static void main(String[] args) {
        Vehicle car = new Car("CAR123", "Rutvi", "Tesla Model 3");
        Vehicle bike = new Bike("BIKE456", "Pooja", "Yamaha R15");
        Vehicle truck = new Truck("TRUCK789", "Poojan", "Volvo FH16");

        car.displayDetails();
        bike.displayDetails();
        truck.displayDetails();

        VehicleUtils.printAnnotatedFields(car);
        VehicleUtils.printAnnotatedFields(bike);
        VehicleUtils.printAnnotatedFields(truck);
        System.out.println("24DCS120 RUTVI SHAH");
    }
}
