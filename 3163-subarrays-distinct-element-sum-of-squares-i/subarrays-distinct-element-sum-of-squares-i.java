class Solution {
    public int sumCounts(java.util.List<Integer> nums) {
        int n = nums.size();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            java.util.Set<Integer> set = new java.util.HashSet<>();

            for (int j = i; j < n; j++) {
                set.add(nums.get(j));
                int distinct = set.size();
                ans += distinct * distinct;
            }
        }

        return ans;
    }
}