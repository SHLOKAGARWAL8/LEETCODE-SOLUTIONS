       import java.math.BigInteger;
class Solution {
    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];
        char middle = 0;

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int[] half = new int[26];
        int length = s.length() / 2;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;

            if (freq[i] % 2 == 1) {
                middle = (char) ('a' + i);
            }
        }

        BigInteger total = BigInteger.ONE;
        int used = 0;

        for (int count : half) {
            total = total.multiply(combination(used + count, count));
            used += count;
        }

        BigInteger rank = BigInteger.valueOf(k);

        if (total.compareTo(rank) < 0) {
            return "";
        }

        StringBuilder left = new StringBuilder();
        int remaining = length;

        while (remaining > 0) {
            for (int i = 0; i < 26; i++) {
                if (half[i] == 0) {
                    continue;
                }

                BigInteger ways = total
                        .multiply(BigInteger.valueOf(half[i]))
                        .divide(BigInteger.valueOf(remaining));

                if (ways.compareTo(rank) >= 0) {
                    left.append((char) ('a' + i));
                    half[i]--;
                    total = ways;
                    remaining--;
                    break;
                }

                rank = rank.subtract(ways);
            }
        }

        StringBuilder result = new StringBuilder(left);

        if (middle != 0) {
            result.append(middle);
        }

        result.append(new StringBuilder(left).reverse());

        return result.toString();
    }

    private BigInteger combination(int n, int r) {
        r = Math.min(r, n - r);
        BigInteger result = BigInteger.ONE;

        for (int i = 1; i <= r; i++) {
            result = result
                    .multiply(BigInteger.valueOf(n - r + i))
                    .divide(BigInteger.valueOf(i));
        }

        return result;
    }
}