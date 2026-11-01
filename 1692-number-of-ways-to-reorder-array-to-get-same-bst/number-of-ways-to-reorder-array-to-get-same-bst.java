
class Solution {
    static final long MOD = 1000000007;

    long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            comb[i][i] = 1;

            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        return (int) ((solve(nums) - 1 + MOD) % MOD);
    }

    private long solve(int[] nums) {
        int n = nums.length;

        if (n <= 2) {
            return 1;
        }

        int root = nums[0];

        int[] left = new int[n];
        int[] right = new int[n];

        int l = 0;
        int r = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < root) {
                left[l++] = nums[i];
            } else {
                right[r++] = nums[i];
            }
        }

        int[] leftArr = new int[l];
        int[] rightArr = new int[r];

        System.arraycopy(left, 0, leftArr, 0, l);
        System.arraycopy(right, 0, rightArr, 0, r);

        long ways = comb[l + r][l];

        ways = (ways * solve(leftArr)) % MOD;
        ways = (ways * solve(rightArr)) % MOD;

        return ways;
    }
}
