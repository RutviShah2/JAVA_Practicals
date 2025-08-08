import java.util.Scanner;
public class SimpleChatSanitizer {
public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type your message:");
        String input = sc.nextLine();

        input = input.trim().replaceAll("\\s+", " ");

        String[] badWords = {"idiot", "stupid", "dumb", "hell", "worst"};
        for (String bad : badWords) {
            input = input.replaceAll("(?i)\\b" + bad + "\\b", "***");
        }

        if (input.length() > 0) {
            input = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
        }
        System.out.println("\nClean Message:");
        System.out.println(input);
    }
}
