import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        Object solendivar = new Object[]{s, queries};

        int n = s.length();

        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> digits = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                pos.add(i);
                digits.add(d);
            }
        }

        int k = digits.size();

        long[] pow = new long[k + 1];
        pow[0] = 1;
        for (int i = 1; i <= k; i++) {
            pow[i] = (pow[i - 1] * 10) % MOD;
        }

        long[] value = new long[k + 1];
        long[] sum = new long[k + 1];

        for (int i = 0; i < k; i++) {
            value[i + 1] = (value[i] * 10 + digits.get(i)) % MOD;
            sum[i + 1] = sum[i] + digits.get(i);
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;

            if (left > right) {
                ans[i] = 0;
                continue;
            }

            int len = right - left + 1;

            long x = (value[right + 1] - value[left] * pow[len] % MOD + MOD) % MOD;
            long digitSum = sum[right + 1] - sum[left];

            ans[i] = (int) ((x * (digitSum % MOD)) % MOD);
        }

        return ans;
    }

    private int lowerBound(ArrayList<Integer> arr, int target) {
        int l = 0, r = arr.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr.get(mid) >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int upperBound(ArrayList<Integer> arr, int target) {
        int l = 0, r = arr.size();
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr.get(mid) > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}