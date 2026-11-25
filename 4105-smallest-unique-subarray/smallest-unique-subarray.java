
import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] sa = new int[n];
        int[] rank = new int[n];
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = nums[i];
        }

        // Build suffix array
        for (int k = 1; k < n; k *= 2) {
            final int len = k;
            final int[] currentRank = rank;

            Integer[] order = new Integer[n];

            for (int i = 0; i < n; i++) {
                order[i] = sa[i];
            }

            Arrays.sort(order, (a, b) -> {
                if (currentRank[a] != currentRank[b]) {
                    return Integer.compare(currentRank[a], currentRank[b]);
                }

                int ra = (a + len < n) ? currentRank[a + len] : -1;
                int rb = (b + len < n) ? currentRank[b + len] : -1;

                return Integer.compare(ra, rb);
            });

            for (int i = 0; i < n; i++) {
                sa[i] = order[i];
            }

            temp[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                boolean different =
                    currentRank[a] != currentRank[b] ||
                    (a + len < n ? currentRank[a + len] : -1) !=
                    (b + len < n ? currentRank[b + len] : -1);

                temp[b] = temp[a] + (different ? 1 : 0);
            }

            int[] swap = rank;
            rank = temp;
            temp = swap;

            if (rank[sa[n - 1]] == n - 1) {
                break;
            }

            if (k > n / 2) {
                break;
            }
        }

        // Build inverse suffix array
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[sa[i]] = i;
        }

        // Kasai's LCP algorithm
        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            if (r == 0) {
                h = 0;
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) {
                h--;
            }
        }

        // Find minimum unique subarray length
        int ans = n;

        for (int r = 0; r < n; r++) {
            int start = sa[r];
            int maxLCP = 0;

            if (r > 0) {
                maxLCP = Math.max(maxLCP, lcp[r]);
            }

            if (r + 1 < n) {
                maxLCP = Math.max(maxLCP, lcp[r + 1]);
            }

            int len = maxLCP + 1;

            if (len <= n - start) {
                ans = Math.min(ans, len);
            }
        }

        return ans;
    }
}
