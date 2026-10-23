class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int k = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                k &= nums[i];
            }
        }

        return k == Integer.MAX_VALUE ? 0 : k;
    }
}