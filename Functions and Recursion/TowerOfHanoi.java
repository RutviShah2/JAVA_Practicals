public public class TowerOfHanoi {

    static void solveHanoi(int n, char source, char helper, char destination) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + source + " to " + destination);
            return;
        }

        solveHanoi(n - 1, source, destination, helper);
        System.out.println("Move disk " + n + " from " + source + " to " + destination);
        solveHanoi(n - 1, helper, source, destination);
    }

    public static void main(String[] args) {
        int n = 3; // number of disks
        solveHanoi(n, 'A', 'B', 'C');
    }
}
 {
    
}
