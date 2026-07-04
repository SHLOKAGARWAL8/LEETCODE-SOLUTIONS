class Solution {
    public int minScore(int n, int[][] roads) {
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] r : roads) {
            graph.get(r[0]).add(new int[]{r[1], r[2]});
            graph.get(r[1]).add(new int[]{r[0], r[2]});
        }

        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();

        q.add(1);
        visited[1] = true;

        int ans = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int[] edge : graph.get(node)) {
                ans = Math.min(ans, edge[1]);

                if (!visited[edge[0]]) {
                    visited[edge[0]] = true;
                    q.add(edge[0]);
                }
            }
        }

        return ans;
    }
}