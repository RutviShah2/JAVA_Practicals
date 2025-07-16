import java.util.Scanner;

public class ReplaceSpaces {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        
        String modified = input.replace(' ', '_');
        System.out.println("Modified string: " + modified);
    }
}

