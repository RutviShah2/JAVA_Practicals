
public interface Scholarship {

}








import java.util.*;
import java.util.regex.*;

public class ChatFilter {

    // Task 1: Basic Filtering (**** replacement)
    public static String basicFilter(String message, List<String> offensiveWords) {
        String filteredMessage = message;

        for (String word : offensiveWords) {
            String pattern = "\\b" + Pattern.quote(word) + "\\b"; // word boundary
            Pattern regex = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = regex.matcher(filteredMessage);
            filteredMessage = matcher.replaceAll("****");
        }

        System.out.println("----- Task 1: Basic Filtering -----");
        System.out.println("Original: " + message);
        System.out.println("Filtered: " + filteredMessage);
        System.out.println();
        return filteredMessage;
    }

    // Task 2: Count and Report Offensive Words
    public static String filterAndCount(String message, List<String> offensiveWords) {
        String filteredMessage = message;
        int count = 0;

        for (String word : offensiveWords) {
            String pattern = "\\b" + Pattern.quote(word) + "\\b";
            Pattern regex = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = regex.matcher(filteredMessage);

            while (matcher.find()) {
                count++;
            }

            filteredMessage = matcher.replaceAll("****");
        }

        System.out.println("----- Task 2: Count Offensive Words -----");
        System.out.println("Original: " + message);
        System.out.println("Filtered: " + filteredMessage);
        System.out.println("Number of offensive words detected: " + count);
        System.out.println();
        return filteredMessage;
    }

    // Task 3: Bonus - Replace with matching-length asterisks
    public static String smartFilter(String message, List<String> offensiveWords) {
        String filteredMessage = message;
        int count = 0;

        for (String word : offensiveWords) {
            String pattern = "\\b" + Pattern.quote(word) + "\\b";
            Pattern regex = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = regex.matcher(filteredMessage);

            StringBuffer sb = new StringBuffer();

            while (matcher.find()) {
                count++;
                String matchedWord = matcher.group();
                String stars = "*".repeat(matchedWord.length());
                matcher.appendReplacement(sb, stars);
            }

            matcher.appendTail(sb);
            filteredMessage = sb.toString(); // update message for next word
        }

        System.out.println("----- Task 3: Smart Filtering -----");
        System.out.println("Original: " + message);
        System.out.println("Filtered: " + filteredMessage);
        System.out.println("Number of offensive words detected: " + count);
        System.out.println();
        return filteredMessage;
    }

    // Main Method to Test All Tasks
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample offensive word list
        List<String> offensiveWords = Arrays.asList("damn", "shit", "bloody");

        // Input message
        System.out.print("Enter your message: ");
        String message = sc.nextLine();

        System.out.println("\n========== FILTERING RESULTS ==========\n");

        basicFilter(message, offensiveWords);         // Task 1
        filterAndCount(message, offensiveWords);      // Task 2
        smartFilter(message, offensiveWords);         // Task 3
    }
}

