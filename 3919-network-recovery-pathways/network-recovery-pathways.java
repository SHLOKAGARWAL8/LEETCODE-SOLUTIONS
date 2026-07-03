import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        List<int[]>[] graph = new ArrayList[n];
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int high = 0;

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            high = Math.max(high, e[2]);
        }

        Queue<Integer> q = new ArrayDeque<>();
        int[] deg = indegree.clone();
        List<Integer> topo = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);
            for (int[] e : graph[u]) {
                if (--deg[e[0]] == 0) {
                    q.offer(e[0]);
                }
            }
        }

        int ans = -1;
        int lo = 0, hi = high;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            long[] dist = new long[n];
            Arrays.fill(dist, Long.MAX_VALUE);
            dist[0] = 0;

            for (int u : topo) {
                if (dist[u] == Long.MAX_VALUE) {
                    continue;
                }

                if (u != 0 && u != n - 1 && !online[u]) {
                    continue;
                }

                for (int[] e : graph[u]) {
                    int v = e[0];
                    int w = e[1];

                    if (w < mid) {
                        continue;
                    }

                    if (v != n - 1 && !online[v]) {
                        continue;
                    }

                    if (dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                    }
                }
            }

            if (dist[n - 1] <= k) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
}