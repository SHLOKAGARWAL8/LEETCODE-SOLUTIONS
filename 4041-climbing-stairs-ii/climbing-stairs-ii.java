class Solution {
    public int climbStairs(int n, int[] costs) {

        int[] dp = new int[4];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {

            int ans = Integer.MAX_VALUE;

            for (int jump = 1; jump <= 3; jump++) {
                if (i >= jump) {
                    ans = Math.min(ans,
                        dp[(i - jump) % 4]
                        + costs[i - 1]
                        + jump * jump
                    );
                }
            }

            dp[i % 4] = ans;
        }

        return dp[n % 4];
    }
}