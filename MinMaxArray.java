public public class MinMaxArray {

    static int findMin(int[] arr) {
        int min = arr[0];
        for (int num : arr) {
            if (num < min) min = num;
        }
        return min;
    }

    static int findMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {4, 9, 2, 15, 7};

        System.out.println("Minimum: " + findMin(arr));
        System.out.println("Maximum: " + findMax(arr));
    }
}
 {
    
}
