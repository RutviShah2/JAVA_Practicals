// EventNotificationSystem.java

import java.util.*;

// ---------------- Functional Interface ----------------
@FunctionalInterface
interface Notifier {
    void send(String message);  // Abstract method

    // Private helper method (for internal formatting)
    private String formatMessage(String msg) {
        return "[NOTIFICATION] " + msg;
    }

    // Default method (uses private method)
    default void notifyUser(String msg) {
        String formatted = formatMessage(msg);
        log(formatted);
        send(formatted);
    }

    // Static method (utility)
    static void log(String msg) {
        System.out.println("LOG: " + msg);
    }
}

// ---------------- Marker Interface ----------------
interface UrgentNotification {
    // Empty → used only for classification
}

// ---------------- Notification Channels ----------------
class EmailNotification implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("📧 Email sent: " + message);
    }
}

// SMS is urgent
class SMSNotification implements Notifier, UrgentNotification {
    @Override
    public void send(String message) {
        System.out.println("📱 SMS sent: " + message);
    }
}

class PushNotification implements Notifier {
    @Override
    public void send(String message) {
        System.out.println(" Push Notification: " + message);
    }
}

// ---------------- Main System ----------------
public class EventNotificationSystem {
    public static void main(String[] args) {
        // Create Notifier objects
        List<Notifier> notifiers = new ArrayList<>();
        notifiers.add(new EmailNotification());
        notifiers.add(new SMSNotification());       // urgent
        notifiers.add(new PushNotification());

        // Add Lambda-based notifier dynamically
        Notifier lambdaNotifier = msg -> System.out.println("Lambda Notifier: " + msg);
        notifiers.add(lambdaNotifier);

        // Trigger notifications
        System.out.println("---- Sending Notifications ----");
        for (Notifier notifier : notifiers) {
            notifier.notifyUser("Event starts at 7 PM today!");
        }

        // Identify urgent notifications
        System.out.println("\n---- Checking Urgent Notifications ----");
        for (Notifier notifier : notifiers) {
            if (notifier instanceof UrgentNotification) {
                System.out.println("Urgent channel detected: " + notifier.getClass().getSimpleName());
            }
        }

        // Using static method directly
        Notifier.log("All notifications processed.");

        System.out.println("24DCS120_RUTVI SHAH");
    }
}
