import java.util.*;

public class PalindromeHighlighter {

    // Check if string is palindrome (ignoring case/punctuation/spaces)
    private static boolean isPalindrome(String text) {
        if (text == null || text.isEmpty()) return false;
        String cleaned = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return cleaned.equals(new StringBuilder(cleaned).reverse().toString()) && cleaned.length() > 1;
    }

    // Extract palindrome words
    private static List<String> findPalindromeWords(String paragraph) {
        List<String> result = new ArrayList<>();
        String[] words = paragraph.split("\\s+");
        for (String word : words) {
            String cleaned = word.replaceAll("[^a-zA-Z0-9]", "");
            if (isPalindrome(cleaned)) {
                result.add(cleaned);
            }
        }
        return result;
    }

    // Extract palindrome sentences
    private static List<String> findPalindromeSentences(String paragraph) {
        List<String> result = new ArrayList<>();
        String[] sentences = paragraph.split("[.!?]");
        for (String sentence : sentences) {
            if (isPalindrome(sentence)) {
                result.add(sentence.trim());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String paragraph = "Madam Arora teaches malayalam. Wow! Was it a car or a cat I saw? This is not a palindrome.";

        List<String> palindromeWords = findPalindromeWords(paragraph);
        List<String> palindromeSentences = findPalindromeSentences(paragraph);

        System.out.println("Palindrome Words Found:");
        for (String word : palindromeWords) {
            System.out.println(" - " + word);
        }

        System.out.println("\nPalindrome Sentences Found:");
        for (String sentence : palindromeSentences) {
            System.out.println(" - " + sentence);

        }
        System.out.println("24DCS120 RUTVI SHAH");
    }
}
