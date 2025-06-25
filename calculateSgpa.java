import java.util.Scanner;

public class calculateSgpa
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter credits for Subject 1: ");
        int credit1 = sc.nextInt();
        System.out.print("Enter grade point for Subject 1: ");
        int grade1 = sc.nextInt();

        System.out.print("Enter credits for Subject 2: ");
        int credit2 = sc.nextInt();
        System.out.print("Enter grade point for Subject 2: ");
        int grade2 = sc.nextInt();

        System.out.print("Enter credits for Subject 3: ");
        int credit3 = sc.nextInt();
        System.out.print("Enter grade point for Subject 3: ");
        int grade3 = sc.nextInt();

        int totalCredits = credit1 + credit2 + credit3;
        int totalPoints = (credit1 * grade1) + (credit2 * grade2) + (credit3 * grade3);

        double sgpa = (double) totalPoints / totalCredits;

        System.out.printf("Your SGPA is: %.2f\n", sgpa);

        sc.close();
    }
}