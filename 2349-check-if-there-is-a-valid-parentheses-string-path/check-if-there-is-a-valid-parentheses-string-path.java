class Solution {
    Boolean[][][] dp;

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if ((m + n) % 2 == 0) {
            return false;
        }

        dp = new Boolean[m][n][m + n + 1];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int i, int j, int balance) {
        int m = grid.length;
        int n = grid[0].length;

        if (grid[i][j] == '(') {
            balance++;
        } else {
            balance--;
        }

        if (balance < 0) {
            return false;
        }

        int remaining = (m - 1 - i) + (n - 1 - j);

        if (balance > remaining) {
            return false;
        }

        if (i == m - 1 && j == n - 1) {
            return balance == 0;
        }

        if (dp[i][j][balance] != null) {
            return dp[i][j][balance];
        }

        boolean ans = false;

        if (i + 1 < m) {
            ans = dfs(grid, i + 1, j, balance);
        }

        if (!ans && j + 1 < n) {
            ans = dfs(grid, i, j + 1, balance);
        }

        dp[i][j][balance] = ans;

        return ans;
    }
}