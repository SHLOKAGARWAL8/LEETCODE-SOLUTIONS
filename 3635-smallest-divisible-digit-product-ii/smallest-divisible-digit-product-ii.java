class Solution {
     private static final int[][] FACT = {
        {0,0,0,0},
        {0,0,0,0},
        {1,0,0,0},
        {0,1,0,0},
        {2,0,0,0},
        {0,0,1,0},
        {1,1,0,0},
        {0,0,0,1},
        {3,0,0,0},
        {0,2,0,0}
    };

    private int[][] dp;

    public String smallestNumber(String num, long t) {
        int[] need = new int[4];

        while (t % 2 == 0) {
            need[0]++;
            t /= 2;
        }

        while (t % 3 == 0) {
            need[1]++;
            t /= 3;
        }

        while (t % 5 == 0) {
            need[2]++;
            t /= 5;
        }

        while (t % 7 == 0) {
            need[3]++;
            t /= 7;
        }

        if (t != 1) return "-1";

        buildDP(need[0], need[1]);

        int n = num.length();

        int[][] prefix = new int[n + 1][4];
        int firstZero = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                prefix[i + 1][j] = prefix[i][j];
            }

            int d = num.charAt(i) - '0';

            if (d == 0) {
                if (firstZero == -1) firstZero = i;
            } else if (firstZero == -1) {
                for (int j = 0; j < 4; j++) {
                    prefix[i + 1][j] += FACT[d][j];
                }
            }
        }

        if (firstZero == -1 && enough(prefix[n], need)) {
            return num;
        }

        int start = firstZero == -1 ? n - 1 : firstZero;

        for (int i = start; i >= 0; i--) {
            int current = num.charAt(i) - '0';
            int begin = current == 0 ? 1 : current + 1;

            for (int d = begin; d <= 9; d++) {
                int r2 = Math.max(0, need[0] - prefix[i][0] - FACT[d][0]);
                int r3 = Math.max(0, need[1] - prefix[i][1] - FACT[d][1]);
                int r5 = Math.max(0, need[2] - prefix[i][2] - FACT[d][2]);
                int r7 = Math.max(0, need[3] - prefix[i][3] - FACT[d][3]);

                int required = dp[r2][r3] + r5 + r7;
                int remaining = n - i - 1;

                if (required <= remaining) {
                    StringBuilder ans = new StringBuilder();

                    ans.append(num, 0, i);
                    ans.append(d);

                    String suffix = makeSuffix(r2, r3, r5, r7, remaining);
                    ans.append(suffix);

                    return ans.toString();
                }
            }
        }

        int minimum = dp[need[0]][need[1]] + need[2] + need[3];
        int len = Math.max(n + 1, minimum);

        return makeSuffix(
            need[0],
            need[1],
            need[2],
            need[3],
            len
        );
    }

    private void buildDP(int max2, int max3) {
        dp = new int[max2 + 1][max3 + 1];

        for (int a = 0; a <= max2; a++) {
            for (int b = 0; b <= max3; b++) {
                if (a == 0 && b == 0) continue;

                int best = Integer.MAX_VALUE;

                for (int d = 2; d <= 9; d++) {
                    if (d == 5 || d == 7) continue;

                    int na = Math.max(0, a - FACT[d][0]);
                    int nb = Math.max(0, b - FACT[d][1]);

                    if (na == a && nb == b) continue;

                    best = Math.min(best, 1 + dp[na][nb]);
                }

                dp[a][b] = best;
            }
        }
    }

    private String makeSuffix(int a, int b, int c, int d, int length) {
        List<Integer> digits = new ArrayList<>();

        while (a > 0 || b > 0) {
            for (int digit = 2; digit <= 9; digit++) {
                if (digit == 5 || digit == 7) continue;

                int na = Math.max(0, a - FACT[digit][0]);
                int nb = Math.max(0, b - FACT[digit][1]);

                if (na == a && nb == b) continue;

                if (dp[a][b] == 1 + dp[na][nb]) {
                    digits.add(digit);
                    a = na;
                    b = nb;
                    break;
                }
            }
        }

        while (c-- > 0) digits.add(5);
        while (d-- > 0) digits.add(7);

        Collections.sort(digits);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length - digits.size(); i++) {
            result.append('1');
        }

        for (int digit : digits) {
            result.append(digit);
        }

        return result.toString();
    }

    private boolean enough(int[] have, int[] need) {
        for (int i = 0; i < 4; i++) {
            if (have[i] < need[i]) return false;
        }

        return true;   
    }
}