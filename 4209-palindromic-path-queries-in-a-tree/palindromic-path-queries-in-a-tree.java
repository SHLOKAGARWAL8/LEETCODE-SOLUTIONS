
import java.util.*;

class Solution {

    int[] bit;
    int[] tin, tout, depth, parent, value;
    int[][] up;
    int LOG = 16;

    void add(int i, int val, int n) {
        while (i <= n + 1) {
            bit[i] ^= val;
            i += i & -i;
        }
    }

    int get(int i) {
        int ans = 0;

        while (i > 0) {
            ans ^= bit[i];
            i -= i & -i;
        }

        return ans;
    }

    void update(int u, int change, int n) {
        add(tin[u], change, n);
        add(tout[u] + 1, change, n);
    }

    int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int diff = depth[a] - depth[b];

        for (int k = LOG; k >= 0; k--) {
            if ((diff & (1 << k)) != 0) {
                a = up[k][a];
            }
        }

        if (a == b) {
            return a;
        }

        for (int k = LOG; k >= 0; k--) {
            if (up[k][a] != up[k][b]) {
                a = up[k][a];
                b = up[k][b];
            }
        }

        return parent[a];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {

        List<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        value = new int[n];

        up = new int[LOG + 1][n];
        bit = new int[n + 2];

        int[] stack = new int[n];
        int[] iterator = new int[n];

        int top = 0;
        int timer = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {

            int u = stack[top - 1];

            if (iterator[u] == 0) {

                tin[u] = ++timer;
                value[u] = 1 << (s.charAt(u) - 'a');

                up[0][u] = parent[u];

                for (int k = 1; k <= LOG; k++) {
                    up[k][u] = up[k - 1][up[k - 1][u]];
                }
            }

            if (iterator[u] < adj[u].size()) {

                int v = adj[u].get(iterator[u]++);

                if (v != parent[u]) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    stack[top++] = v;
                }

            } else {

                tout[u] = timer;
                top--;
            }
        }

        for (int i = 0; i < n; i++) {
            update(i, value[i], n);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {

            String[] p = q.split(" ");

            if (p[0].equals("update")) {

                int u = Integer.parseInt(p[1]);
                int newValue = 1 << (p[2].charAt(0) - 'a');

                int change = value[u] ^ newValue;

                value[u] = newValue;

                update(u, change, n);

            } else {

                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int w = lca(u, v);

                int mask = get(tin[u]) ^ get(tin[v]) ^ value[w];

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }
}
