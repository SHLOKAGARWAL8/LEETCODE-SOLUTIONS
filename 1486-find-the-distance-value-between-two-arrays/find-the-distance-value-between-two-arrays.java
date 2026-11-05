
import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);

        int count = 0;

        for (int num : arr1) {
            int idx = Arrays.binarySearch(arr2, num);

            if (idx >= 0) {
                continue;
            }

            idx = -idx - 1;

            boolean valid = true;

            if (idx < arr2.length && Math.abs(num - arr2[idx]) <= d) {
                valid = false;
            }

            if (idx > 0 && Math.abs(num - arr2[idx - 1]) <= d) {
                valid = false;
            }

            if (valid) {
                count++;
            }
        }

        return count;
    }
}
