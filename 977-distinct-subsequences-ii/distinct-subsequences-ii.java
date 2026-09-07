class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1_000_000_007L;

        long[] dp = new long[s.length() + 1];
        int[] last = new int[26];

        dp[0] = 1;

        for (int i = 1; i <= s.length(); i++) {
            int c = s.charAt(i - 1) - 'a';

            dp[i] = (2 * dp[i - 1]) % MOD;

            if (last[c] > 0) {
                dp[i] = (dp[i] - dp[last[c] - 1] + MOD) % MOD;
            }

            last[c] = i;
        }

        return (int) ((dp[s.length()] - 1 + MOD) % MOD);
    }
}