public class ArmstrongCheck {

    static boolean isArmstrong(int n) {
        int original = n, sum = 0, digits = String.valueOf(n).length();

        while (n > 0) {
            int digit = n % 10;
            sum += Math.pow(digit, digits);
            n /= 10;
        }

        return sum == original;
    }

    public static void main(String[] args) {
        int number = 153;
        if (isArmstrong(number))
            System.out.println(number + " is an Armstrong number 😇");
        else
            System.out.println(number + " is NOT an Armstrong number 😢");
    }
}
