package ExceptionHandling;

import java.util.Scanner;

public class RobustCalculator {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // Taking first number
            System.out.print("Enter first number: ");
            double num1 = Double.parseDouble(sc.nextLine());

            // Taking operator
            System.out.print("Enter operator (+, -, *, /): ");
            char operator = sc.nextLine().charAt(0);

            // Taking second number
            System.out.print("Enter second number: ");
            double num2 = Double.parseDouble(sc.nextLine());

            double result;

            // Performing calculation
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    System.out.println("Result: " + result);
                    break;
                case '-':
                    result = num1 - num2;
                    System.out.println("Result: " + result);
                    break;
                case '*':
                    result = num1 * num2;
                    System.out.println("Result: " + result);
                    break;
                case '/':
                    if (num2 == 0) {
                        throw new ArithmeticException("Cannot divide by zero!");
                    }
                    result = num1 / num2;
                    System.out.println("Result: " + result);
                    break;
                default:
                    System.out.println("Invalid operator!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numeric values.");
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Calculation finished.");
        }
        System.out.println("24DCS120_RUTVI SHAH");

    }
}
