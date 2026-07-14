class Solution {

    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int max = 0;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        long[][] dp = new long[max + 1][max + 1];
        dp[0][0] = 1;

        for (int num : nums) {
            long[][] next = new long[max + 1][max + 1];

            for (int gcd1 = 0; gcd1 <= max; gcd1++) {
                for (int gcd2 = 0; gcd2 <= max; gcd2++) {
                    if (dp[gcd1][gcd2] == 0) {
                        continue;
                    }

                    next[gcd1][gcd2] = (next[gcd1][gcd2] + dp[gcd1][gcd2]) % MOD;

                    int newGcd1 = gcd(gcd1, num);
                    next[newGcd1][gcd2] = (next[newGcd1][gcd2] + dp[gcd1][gcd2]) % MOD;

                    int newGcd2 = gcd(gcd2, num);
                    next[gcd1][newGcd2] = (next[gcd1][newGcd2] + dp[gcd1][gcd2]) % MOD;
                }
            }

            dp = next;
        }

        long answer = 0;

        for (int gcd = 1; gcd <= max; gcd++) {
            answer = (answer + dp[gcd][gcd]) % MOD;
        }

        return (int) answer;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    
    }
}