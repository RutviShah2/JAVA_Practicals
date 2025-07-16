import java.util.Scanner;

public class kmToM {
    public static void main(String [] args)
    {
Scanner sc = new Scanner(System.in);
System.out.println("Enter the kilometers");
float   number= sc.nextFloat();
float result = number * 0.621f;
System.out.println("The miles are: ");
System.out.println(result);
    }
}
