import java.util.Scanner;

public class ToLowerCaseExample 
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        String lowercase = input.toLowerCase();

      System.out.println("Lowercase version: " + lowercase);
    }
}
