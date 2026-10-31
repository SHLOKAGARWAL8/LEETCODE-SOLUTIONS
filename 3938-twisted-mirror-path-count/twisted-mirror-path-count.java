class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int MOD = 1_000_000_007;

        long[][] right = new long[m][n];
        long[][] down = new long[m][n];

        right[0][0] = 1;
        down[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                long fromLeft = j > 0 ? right[i][j - 1] : 0;
                long fromTop = i > 0 ? down[i - 1][j] : 0;

                if (grid[i][j] == 0) {
                    right[i][j] = (fromLeft + fromTop) % MOD;
                    down[i][j] = (fromLeft + fromTop) % MOD;
                } else {
                    right[i][j] = fromTop;
                    down[i][j] = fromLeft;
                }
            }
        }

        return (int) ((right[m - 1][n - 1]) % MOD);
    }
}