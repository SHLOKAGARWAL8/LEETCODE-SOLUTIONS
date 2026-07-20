class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
           int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        k %= total;

        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                int newPosition = i * n + j;
                int oldPosition = (newPosition - k + total) % total;

                row.add(grid[oldPosition / n][oldPosition % n]);
            }

            result.add(row);
        }

        return result;
    }
}