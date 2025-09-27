import java.util.*;

public class WordCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input paragraph
        System.out.println("Enter a paragraph:");
        String paragraph = sc.nextLine().toLowerCase();

        // Remove punctuation & split into words
        String[] words = paragraph.replaceAll("[^a-zA-Z0-9 ]", "").split("\\s+");

        // 1️⃣ Count frequency using HashMap
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String word : words) {
            hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
        }

        // 2️⃣ Maintain insertion order using LinkedHashMap
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        for (String word : words) {
            if (!linkedHashMap.containsKey(word)) {
                linkedHashMap.put(word, hashMap.get(word));
            }
        }

        // 3️⃣ Sort by key using TreeMap
        TreeMap<String, Integer> treeMap = new TreeMap<>(hashMap);

        // Display results
        System.out.println("\nWord Frequency using HashMap (no order):");
        System.out.println(hashMap);

        System.out.println("\nWord Frequency using LinkedHashMap (insertion order):");
        System.out.println(linkedHashMap);

        System.out.println("\nWord Frequency using TreeMap (sorted by key):");
        System.out.println(treeMap);

        sc.close();
        System.out.println("24DCS120_RUTVI SHAH");
    }
}
