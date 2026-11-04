
import java.util.*;

class Solution {
    static class Fenwick {
        int n;
        int[] cnt;
        long[] sum;

        Fenwick(int n) {
            this.n = n;
            cnt = new int[n + 1];
            sum = new long[n + 1];
        }

        void add(int i, int c, long s) {
            while (i <= n) {
                cnt[i] += c;
                sum[i] += s;
                i += i & -i;
            }
        }

        int queryCount(int i) {
            int res = 0;
            while (i > 0) {
                res += cnt[i];
                i -= i & -i;
            }
            return res;
        }

        long querySum(int i) {
            long res = 0;
            while (i > 0) {
                res += sum[i];
                i -= i & -i;
            }
            return res;
        }

        int kth(int k) {
            int idx = 0;
            for (int bit = Integer.highestOneBit(n);
                 bit > 0; bit >>= 1) {
                int next = idx + bit;
                if (next <= n && cnt[next] < k) {
                    idx = next;
                    k -= cnt[next];
                }
            }
            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;
        for (int v : sorted) {
            if (m == 0 || sorted[m - 1] != v) {
                sorted[m++] = v;
            }
        }

        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(sorted, 0, m, nums[i]) + 1;
        }

        // Cost of making each window of length x equal
        Fenwick bit = new Fenwick(m);
        long[] cost = new long[n - x + 1];
        long windowSum = 0;

        for (int i = 0; i < n; i++) {
            bit.add(rank[i], 1, nums[i]);
            windowSum += nums[i];

            if (i >= x) {
                bit.add(rank[i - x], -1, -(long) nums[i - x]);
                windowSum -= nums[i - x];
            }

            if (i >= x - 1) {
                int medianRank = bit.kth((x + 1) / 2);
                long median = sorted[medianRank - 1];

                int leftCount = bit.queryCount(medianRank);
                long leftSum = bit.querySum(medianRank);

                int rightCount = x - leftCount;
                long rightSum = windowSum - leftSum;

                cost[i - x + 1] =
                    median * leftCount - leftSum
                    + rightSum - median * rightCount;
            }
        }

        // DP: minimum cost for exactly t non-overlapping windows
        long INF = Long.MAX_VALUE / 4;

        // Zero windows can be selected from any prefix.
        long[] prev = new long[n + 1];

        for (int t = 1; t <= k; t++) {
            long[] curr = new long[n + 1];
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                // Skip element i-1
                curr[i] = curr[i - 1];

                // Select window [i-x, i-1]
                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            prev = curr;
        }

        return prev[n];
    }
}
