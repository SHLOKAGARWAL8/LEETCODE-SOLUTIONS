class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (nums[0] + i) % n) {
                increasing = false;
            }

            if (nums[i] != (nums[0] - i + n) % n) {
                decreasing = false;
            }
        }

        if (increasing) {
            int c = nums[0];
            if (c == 0) return 0;
            return Math.min(n - c, c + 2);
        }

        if (decreasing) {
            int k = (nums[0] + 1) % n;
            return 1 + Math.min(k, (n - k) % n);
        }

        return -1;
    }
}