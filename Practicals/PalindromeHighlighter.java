import java.util.*;

public class PalindromeHighlighter {
    static String clean(String text) {
        return text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

    static boolean isPalindrome(String text) {
        String cleaned = clean(text);
        return cleaned.length() > 1 && cleaned.equals(new StringBuilder(cleaned).reverse().toString());
    }
     public static void main(String[] args) {
        String paragraph = "Wow! Madam how are you heloh mom!";
        System.out.println("Palindromic Words:");
        for (String word : paragraph.split("\\s+")) {
            if (isPalindrome(word)) {
                System.out.println(" - " + word);
            }
        }
        System.out.println("\nPalindromic Sentences:");
        for (String sentence : paragraph.split("[.!?]")) {
            if (isPalindrome(sentence.trim())) {
                System.out.println(" - " + sentence.trim());
            }
        }
    }
}