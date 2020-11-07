public class BinarySearch {

    public static int binarySearch(int[] arr, int target) {
        int high = arr.length - 1;
        int low = 0;
        while (low <= high) {
            int mid = (high + low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        // A perfectly sorted array.
        int[] arr = { 1, 5, 12, 14, 15, 17, 25, 32, 45, 75, 85, 96, 105, 125, 443, 550, 666 };

        int idx = binarySearch(arr, 45);

        System.out.println("The element "+arr[idx]+" is at idx : "+ idx);
    }   
}
