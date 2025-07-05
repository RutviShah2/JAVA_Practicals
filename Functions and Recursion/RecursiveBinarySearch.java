public public class RecursiveBinarySearch {

    static int binarySearch(int[] arr, int left, int right, int key) {
        if (left > right) return -1;
        int mid = left + (right - left) / 2;

        if (arr[mid] == key) return mid;
        if (arr[mid] > key) return binarySearch(arr, left, mid - 1, key);
        return binarySearch(arr, mid + 1, right, key);
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16, 23, 38};
        int key = 23;

        int result = binarySearch(arr, 0, arr.length - 1, key);
        if (result != -1)
            System.out.println("Element found at index: " + result);
        else
            System.out.println("Element not found!");
    }
}
 {
    
}
