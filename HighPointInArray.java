import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * 
 */
public class HighPointInArray {


    /**
     * We are given an array of integers with values in ascending but not contiguos order,
     * followed by descending values also not in continuous order.
     * A single high value is guaranteed.
     * 
     * Brute force approach.
     * 
     * Execution: O(n) - Space: O(1)
     */
    static public int findHigh0(int[] arr) {

        // **** traverse array looking for max val - O(n) ****
        for (int i = 0; i < arr.length; i++) {

            // **** check for i in range - O(n) ****
            if ((i > 0 && i < arr.length - 1) &&

                // **** check previous against current against next ****
                (arr[i - 1] < arr[i] && arr[i] > arr[i + 1])) {

                // **** this value meets condition ****
                return arr[i];
            }
        }

        // **** did not find a high value ****
        return Integer.MIN_VALUE;
    }


    /**
     * We are given an array of integers with values in ascending but not contiguos order,
     * followed by descending values also not in continuous order.
     * A single high value is guaranteed.
     * 
     * Binary search approach
     * 
     * Execution: O(log(n)) - Space: O(1)
     */
    static public int findHigh1(int[] arr) {

        // **** sanity check(s) ****
        if (arr.length < 3) return Integer.MIN_VALUE;

        // **** initialization ****
        int len = arr.length;
        int l   = 0;
        int r   = len - 1;

        // **** binary search ****
        while (l <= r) {

            // ???? ????
            System.out.println("<<< l: " + l + " r: " + r);

            // **** compute mid point ****
            int mid = l + (r - l) / 2;

            // ???? ????
            System.out.println("<<< arr[" + mid + "]: " + arr[mid]);

            // **** check for high element ****
            if ((mid > 0 && mid < len - 1) &&
                (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]))
                return arr[mid];

            // **** go left ****
            if (arr[mid - 1] > arr[mid])
                r = mid - 1;

            // **** go right ****
            else
                l = mid + 1;
        }

        // **** high value NOT found ****
        return Integer.MIN_VALUE;
    }


    /**
     * We are given an array of integers with values in ascending but not contiguos order,
     * followed by descending values also not in continuous order.
     * A single high value is guaranteed.
     * 
     * Some values in the int[] arr repeated.
     * 
     * Binary search approach
     * 
     * Execution: O(log(n) * k) - Space: O(1)
     */
    static public int findHigh(int[] arr) {

        // **** sanity check(s) ****
        if (arr.length < 3) return Integer.MIN_VALUE;

        // **** initialization ****
        int len = arr.length;
        int l   = 0;
        int r   = len - 1;

        // ???? ????
        System.out.println("<<< len: " + len);

        // **** binary search - O(log(n) + k)****
        while (l <= r) {

            // ???? ????
            System.out.println("<<< l: " + l + " r: " + r);

            // **** compute mid ****
            int mid = l + (r - l) / 2;

            // ???? ????
            System.out.println("<<< arr[" + mid + "]: " + arr[mid]);

            // **** check for high element ****
            if ((mid > 0 && mid < len - 1) &&
                (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1]))
                return arr[mid];

            // **** go left ****
            if (arr[mid - 1] > arr[mid]) {
                r = mid - 1;
            }

            // **** go right ****
            else if (arr[mid] < arr[mid + 1] ) {
                l = mid + 1;
            }

            // **** determine which direction to go - O(k) ****
            else {

                // ???? ????
                System.out.println("<<< (" + arr[mid - 1] + "," + arr[mid] + "," + arr[mid + 1] + ")");

                // **** expand check left and right ****
                for (int i = 2; mid - i > 0 && mid + i < len; i++) {

                    // ???? ????
                    System.out.println("<<< i: " + i + " (" + arr[mid - i] + "," + arr[mid + i] + ")");

                    // **** go left ****
                    if (arr[mid - i] > arr[mid]) {
                        r = mid - i;
                        break;
                    }

                    // *** go right ****
                    else if (arr[mid] > arr[mid + i]) {
                        l = mid + i;
                        break;
                    }
                }
            }
        }

        // **** high value NOT found ****
        return Integer.MIN_VALUE;
    }


    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read int[] arr ****
        int[] arr = Arrays.stream(br.readLine().trim().split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< arr: " + Arrays.toString(arr));

        // **** call function of interest and display result ****
        System.out.println("main <<< result: " + findHigh0(arr));

        // **** call function of interest and display result ****
        System.out.println("main <<< result: " + findHigh(arr));
    }
}