import java.util.*;

public class ChatSanitizer {

    // List of inappropriate words
    private static final Set<String> bannedWords = new HashSet<>(Arrays.asList(
            "badword", "slang", "stupid"
    ));

    // Method to sanitize input
    public static String sanitizeMessage(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }

        // 1. Remove extra spaces
        String cleaned = input.trim().replaceAll("\\s+", " ");

        // 2. Replace inappropriate words
        String[] words = cleaned.split(" ");
        for (int i = 0; i < words.length; i++) {
            String wordLower = words[i].toLowerCase().replaceAll("[^a-z]", "");
            if (bannedWords.contains(wordLower)) {
                words[i] = "***"; // replace word
            }
        }
        cleaned = String.join(" ", words);

        // 3. Formatting (capitalize first letter, rest normal)
        cleaned = cleaned.substring(0, 1).toUpperCase() + cleaned.substring(1);

        return cleaned;
    }

    // Main method (simulate chat)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Chat Application (type 'exit' to quit)");
        while (true) {
            System.out.print("User: ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("exit")) break;

            String sanitized = sanitizeMessage(input);
            System.out.println("Sanitized: " + sanitized);
            System.out.println("24DCS120 RUTVI SHAH"); 
        }
    }
}
