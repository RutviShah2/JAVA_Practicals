import java.util.*;
import java.util.regex.*;

public class ChatFilter {

    public static String basicFilter(String message, List<String> offensiveWords) {
        String filteredMessage = message;
        for (String word : offensiveWords) {
            String pattern = "\\b" + Pattern.quote(word) + "\\b";
            Pattern regex = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = regex.matcher(filteredMessage);
            filteredMessage = matcher.replaceAll("****");
        }
        System.out.println("Basic Filtering");
        System.out.println("Original : " + message);
        System.out.println("Filtered : " + filteredMessage);
        System.out.println();
        return filteredMessage;
    }

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

        System.out.println("Offensive Word Count");
        System.out.println("Original : " + message);
        System.out.println("Filtered : " + filteredMessage);
        System.out.println("Number of offensive words detected: " + count);
        System.out.println();
        return filteredMessage;
    }

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
                String replacement = "*".repeat(matchedWord.length());
                matcher.appendReplacement(sb, replacement);
            }
            matcher.appendTail(sb);
            filteredMessage = sb.toString();
        }
        System.out.println("Replacing with matching length asterisks");
        System.out.println("Original : " + message);
        System.out.println("Filtered : " + filteredMessage);
        System.out.println("Number of offensive words detected: " + count);
        System.out.println();
        return filteredMessage;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> offensiveWords = Arrays.asList("damn", "shit", "bloody");
        System.out.print("Enter your message: ");
        String message = sc.nextLine();
        System.out.println("\nFiltering Results\n");
        basicFilter(message, offensiveWords);        
        filterAndCount(message, offensiveWords);    
        smartFilter(message, offensiveWords);       
        sc.close();
    }
}