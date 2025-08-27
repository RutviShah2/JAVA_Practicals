import java.util.*;

public class SimplePatternFinder {

    // Naive substring search
    public static List<Integer> findOccurrences(String text, String pattern) {
        List<Integer> positions = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        for (int i = 0; i <= n - m; i++) {
            // Check substring of length m
            if (text.substring(i, i + m).equals(pattern)) {
                positions.add(i); // match found
            }
        }
        return positions;
    }

    public static void main(String[] args) {
        String text = "ababcabcabababd";
        String pattern = "ab";

        List<Integer> matches = findOccurrences(text, pattern);

        if (matches.isEmpty()) {
            System.out.println("Pattern \"" + pattern + "\" not found in the text.");
        } else {
            System.out.println("Pattern \"" + pattern + "\" found at positions: " + matches);
        }
        System.out.println("24DCS120 RUTVI SHAH");
    }
}
