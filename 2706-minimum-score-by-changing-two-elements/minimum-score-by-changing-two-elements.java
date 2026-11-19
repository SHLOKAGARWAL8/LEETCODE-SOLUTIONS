
import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {

        // Sort the array
        Arrays.sort(nums);

        int n = nums.length;

        // Case 1: Change two smallest elements
        int a = nums[n - 1] - nums[2];

        // Case 2: Change smallest and second largest
        int b = nums[n - 2] - nums[1];

        // Case 3: Change two largest elements
        int c = nums[n - 3] - nums[0];

        // Return minimum score
        return Math.min(a, Math.min(b, c));
    }
}
