package FileNIO;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        String filename = "input.txt";  // your text file
        Map<String, Integer> wordCount = new HashMap<>();

        // Step 1: Read file and count words
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null) {
                // Normalize: lowercase, remove punctuation
                line = line.toLowerCase().replaceAll("[^a-z0-9\\s]", "");
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Step 2: Sort by frequency (descending)
        List<Entry<String, Integer>> sortedList = new ArrayList<>(wordCount.entrySet());
        sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Step 3: Display top 10
        System.out.println("Top 10 most frequent words:");
        for (int i = 0; i < Math.min(10, sortedList.size()); i++) {
            Entry<String, Integer> entry = sortedList.get(i);
            System.out.println((i+1) + ". " + entry.getKey() + " → " + entry.getValue());
        }
        System.out.println("24DCS120_RUTVI SHAH"); 
    }
}
