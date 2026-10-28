class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[2][n][1024];

        for (int j = 0; j < n; j++) {
            for (int k = 0; k < 1024; k++) {
                dp[0][j][k] = dp[1][j][k] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            int curr = i & 1;
            int prev = curr ^ 1;

            for (int j = 0; j < n; j++) {
                java.util.Arrays.fill(dp[curr][j], 0);

                if (i == 0 && j == 0) {
                    dp[curr][j][grid[i][j]] = 1;
                    continue;
                }

                if (i > 0) {
                    for (int x = 0; x < 1024; x++) {
                        if (dp[prev][j][x] != 0) {
                            dp[curr][j][x ^ grid[i][j]] = 1;
                        }
                    }
                }

                if (j > 0) {
                    for (int x = 0; x < 1024; x++) {
                        if (dp[curr][j - 1][x] != 0) {
                            dp[curr][j][x ^ grid[i][j]] = 1;
                        }
                    }
                }
            }
        }

        int ans = 0;
        while (ans < 1024 && dp[(m - 1) & 1][n - 1][ans] == 0) {
            ans++;
        }
        return ans;
    }
}