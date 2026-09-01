class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // A palindrome can have at most one odd frequency.
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLen = n / 2;
        int[] half = new int[26];

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
        }

        // Target prefix that determines the palindrome's ordering.
        String targetHalf = target.substring(0, halfLen);

        // Try to construct the exact target prefix.
        int[] remaining = half.clone();
        boolean possible = true;

        for (int i = 0; i < halfLen; i++) {
            int c = targetHalf.charAt(i) - 'a';

            if (remaining[c] == 0) {
                possible = false;
                break;
            }

            remaining[c]--;
        }

        // If target's first half is possible, check the exact palindrome.
        if (possible) {
            String candidateHalf = targetHalf;
            String candidate = makePalindrome(candidateHalf, middle, n);

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        /*
         * Find the smallest half-string strictly greater than targetHalf.
         *
         * Start from the right and make one position larger,
         * while keeping the prefix equal to targetHalf.
         */
        for (int pos = halfLen - 1; pos >= 0; pos--) {

            remaining = half.clone();

            // Match target prefix before 'pos'.
            boolean valid = true;

            for (int i = 0; i < pos; i++) {
                int c = targetHalf.charAt(i) - 'a';

                if (remaining[c] == 0) {
                    valid = false;
                    break;
                }

                remaining[c]--;
            }

            if (!valid) {
                continue;
            }

            int targetChar = targetHalf.charAt(pos) - 'a';

            // Pick the smallest character greater than target[pos].
            for (int c = targetChar + 1; c < 26; c++) {

                if (remaining[c] == 0) {
                    continue;
                }

                remaining[c]--;

                StringBuilder halfString = new StringBuilder();

                // Equal prefix
                for (int i = 0; i < pos; i++) {
                    halfString.append(targetHalf.charAt(i));
                }

                // Larger character
                halfString.append((char) ('a' + c));

                // Fill remaining characters in ascending order.
                for (int x = 0; x < 26; x++) {
                    while (remaining[x] > 0) {
                        halfString.append((char) ('a' + x));
                        remaining[x]--;
                    }
                }

                return makePalindrome(
                    halfString.toString(),
                    middle,
                    n
                );
            }
        }

        return "";
    }

    private String makePalindrome(String half, char middle, int n) {
        StringBuilder result = new StringBuilder();

        result.append(half);

        if (n % 2 == 1) {
            result.append(middle);
        }

        for (int i = half.length() - 1; i >= 0; i--) {
            result.append(half.charAt(i));
        }

        return result.toString();
    }
}