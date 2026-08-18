class Solution {
    public int largestInteger(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i <= nums.length - k; i++) {
            Set<Integer> seen = new HashSet<>();

            for (int j = i; j < i + k; j++) {
                seen.add(nums[j]);
            }

            for (int x : seen) {
                freq.put(x, freq.getOrDefault(x, 0) + 1);
            }
        }

        int ans = -1;

        for (int x : freq.keySet()) {
            if (freq.get(x) == 1) {
                ans = Math.max(ans, x);
            }
        }

        return ans;
    }
}