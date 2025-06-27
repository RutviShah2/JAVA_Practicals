import java.util.Scanner;

public class ReplaceSpaces {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take user input
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Replace spaces with underscores
        String modified = input.replace(' ', '_');

        // Show the result
        System.out.println("Modified string: " + modified);
    }
}

