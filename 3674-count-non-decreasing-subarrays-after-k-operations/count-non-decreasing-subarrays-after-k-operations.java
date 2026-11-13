
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int LOG = 1;

        while ((1 << LOG) <= n) {
            LOG++;
        }

        // Prefix sums
        long[] pref = new long[n + 1];

        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + nums[i];
        }

        // Next strictly greater element
        int[] nge = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            nge[i] = (top == -1) ? -1 : stack[top];
            stack[++top] = i;
        }

        // Binary lifting tables
        int[][] up = new int[LOG][n];
        long[][] sum = new long[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = nge[i];

            if (nge[i] != -1) {
                sum[0][i] =
                    (long) nums[i] * (nge[i] - i);
            }
        }

        for (int p = 1; p < LOG; p++) {
            for (int i = 0; i < n; i++) {
                int mid = up[p - 1][i];

                if (mid != -1) {
                    up[p][i] = up[p - 1][mid];

                    sum[p][i] =
                        sum[p - 1][i] + sum[p - 1][mid];
                } else {
                    up[p][i] = -1;
                }
            }
        }

        // Sliding window
        long ans = 0;
        int right = -1;

        for (int left = 0; left < n; left++) {

            if (right < left) {
                right = left;
            }

            while (right + 1 < n &&
                   getCost(left, right + 1,
                           nums, pref, up, sum, LOG) <= k) {
                right++;
            }

            ans += right - left + 1;
        }

        return ans;
    }

    // Minimum operations required for [l, r]
    private long getCost(
        int l,
        int r,
        int[] nums,
        long[] pref,
        int[][] up,
        long[][] sum,
        int LOG
    ) {
        long maxSum = 0;
        int cur = l;

        // Sum of prefix maximums using binary lifting
        for (int p = LOG - 1; p >= 0; p--) {
            int nxt = up[p][cur];

            if (nxt != -1 && nxt <= r) {
                maxSum += sum[p][cur];
                cur = nxt;
            }
        }

        // Remaining segment
        maxSum += (long) nums[cur] * (r - cur + 1);

        long actualSum = pref[r + 1] - pref[l];

        return maxSum - actualSum;
    }
}
