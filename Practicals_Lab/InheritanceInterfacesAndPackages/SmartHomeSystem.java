// SmartHomeSystem.java

// ---------------- Interfaces ----------------

// Switchable interface
interface Switchable {
    void turnOn();
    void turnOff();

    // Default method
    default void status() {
        System.out.println("Checking device power status...");
    }

    // Static utility method
    static void resetDevice() {
        System.out.println("Device is being reset to factory settings.");
    }
}

// Schedulable interface
interface Schedulable {
    void schedule(String time);

    default void cancelSchedule() {
        System.out.println("Schedule cancelled.");
    }
}

// SensorEnabled interface
interface SensorEnabled {
    void detectMotion();

    default void alert() {
        System.out.println("Alert! Motion detected.");
    }
}

// ---------------- Devices ----------------

// Light supports switching and scheduling
class Light implements Switchable, Schedulable {
    private String name;

    Light(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + " Light is ON.");
    }

    @Override
    public void turnOff() {
        System.out.println(name + " Light is OFF.");
    }

    @Override
    public void schedule(String time) {
        System.out.println(name + " Light scheduled at " + time);
    }
}

// Fan supports switching only
class Fan implements Switchable {
    private String name;

    Fan(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + " Fan is ON.");
    }

    @Override
    public void turnOff() {
        System.out.println(name + " Fan is OFF.");
    }
}

// Thermostat supports scheduling only
class Thermostat implements Schedulable {
    private String name;

    Thermostat(String name) {
        this.name = name;
    }

    @Override
    public void schedule(String time) {
        System.out.println(name + " Thermostat set to adjust temperature at " + time);
    }
}

// Camera supports switching + motion detection
class Camera implements Switchable, SensorEnabled {
    private String name;

    Camera(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + " Camera is ON and monitoring.");
    }

    @Override
    public void turnOff() {
        System.out.println(name + " Camera is OFF.");
    }

    @Override
    public void detectMotion() {
        System.out.println(name + " Camera detected motion!");
        alert(); // calling default method
    }
}

// ---------------- Main System ----------------

public class SmartHomeSystem {
    public static void main(String[] args) {
        // Devices
        Light livingRoomLight = new Light("Living Room");
        Fan ceilingFan = new Fan("Ceiling");
        Thermostat homeThermostat = new Thermostat("Nest");
        Camera securityCamera = new Camera("Security");

        // Use Light
        livingRoomLight.turnOn();
        livingRoomLight.schedule("8:00 PM");
        livingRoomLight.status(); // default method

        System.out.println();

        // Use Fan
        ceilingFan.turnOn();
        ceilingFan.status();
        Switchable.resetDevice(); // static method

        System.out.println();

        // Use Thermostat
        homeThermostat.schedule("6:30 AM");
        homeThermostat.cancelSchedule();

        System.out.println();

        // Use Camera
        securityCamera.turnOn();
        securityCamera.detectMotion();
        securityCamera.turnOff();

        System.out.println("24DCS120_RUTVI SHAH");
    }
}