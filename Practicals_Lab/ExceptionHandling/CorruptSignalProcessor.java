package ExceptionHandling;
import java.util.Scanner;

public class CorruptSignalProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter signals (space-separated): ");
        String input = sc.nextLine();
        String[] signals = input.split(" ");

        int sum = 0;
        int corruptCount = 0;

        for (String signal : signals) {
            try {
                int value = Integer.parseInt(signal);

                // Check valid range [1, 1000]
                if (value < 1 || value > 1000) {
                    throw new IllegalArgumentException("Out-of-range signal: " + value);
                }

                sum += value; // add valid signal

            } catch (NumberFormatException e) {
                System.out.println("Malformed signal: " + signal);
                corruptCount++;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                corruptCount++;
            }
        }

        // Final results
        System.out.println("Sum of valid signals: " + sum);
        System.out.println("Total corrupt signals: " + corruptCount);
        System.out.println("24DCS120_RUTVI SHAH");

        sc.close();
    }
}
