import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;

    private int n;
    private long[] sum;
    private long[] square;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        square = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last.getOrDefault(nums[i], -1);

            update(1, 0, n - 1, prev + 1, i, 1);

            ans = (ans + square[1]) % MOD;
            last.put(nums[i], i);
        }

        return (int) ans;
    }

    private void apply(int node, int left, int right, long delta) {
        long len = right - left + 1L;
        delta %= MOD;

        square[node] = (
            square[node]
            + 2L * delta % MOD * sum[node] % MOD
            + len % MOD * delta % MOD * delta % MOD
        ) % MOD;

        sum[node] = (sum[node] + len % MOD * delta % MOD) % MOD;
        lazy[node] = (lazy[node] + delta) % MOD;
    }

    private void pushDown(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;
        long delta = lazy[node];

        apply(node * 2, left, mid, delta);
        apply(node * 2 + 1, mid + 1, right, delta);

        lazy[node] = 0;
    }

    private void update(int node, int left, int right,
                        int ql, int qr, long delta) {
        if (ql > right || qr < left) {
            return;
        }

        if (ql <= left && right <= qr) {
            apply(node, left, right, delta);
            return;
        }

        pushDown(node, left, right);

        int mid = left + (right - left) / 2;

        update(node * 2, left, mid, ql, qr, delta);
        update(node * 2 + 1, mid + 1, right, ql, qr, delta);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        square[node] = (
            square[node * 2] + square[node * 2 + 1]
        ) % MOD;
    }
}