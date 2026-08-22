class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        int[][] dp = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;

                for (int mid = l; mid < r; mid++) {
                    int left = prefix[mid + 1] - prefix[l];
                    int right = prefix[r + 1] - prefix[mid + 1];

                    if (left < right) {
                        dp[l][r] = Math.max(dp[l][r], left + dp[l][mid]);
                    } else if (right < left) {
                        dp[l][r] = Math.max(dp[l][r], right + dp[mid + 1][r]);
                    } else {
                        dp[l][r] = Math.max(
                            dp[l][r],
                            left + Math.max(dp[l][mid], dp[mid + 1][r])
                        );
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}