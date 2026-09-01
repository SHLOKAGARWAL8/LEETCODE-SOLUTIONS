class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        // Try to keep the prefix equal to target.
        for (int i = 0; i < n; i++) {
            int x = target.charAt(i) - 'a';

            // We can match target[i]
            if (cnt[x] > 0) {
                cnt[x]--;
                continue;
            }

            // Cannot match target[i], so try a larger character here.
            String ans = build(target, i, x, cnt);
            if (!ans.isEmpty()) {
                return ans;
            }

            // No larger character here, so backtrack.
            for (int j = i - 1; j >= 0; j--) {
                int prev = target.charAt(j) - 'a';

                // Put target[j] back.
                cnt[prev]++;

                ans = build(target, j, prev, cnt);

                if (!ans.isEmpty()) {
                    return ans;
                }
            }

            return "";
        }

        // target itself can be formed.
        // We need a strictly greater permutation,
        // so backtrack from the last position.
        for (int i = n - 1; i >= 0; i--) {
            int x = target.charAt(i) - 'a';

            cnt[x]++;

            String ans = build(target, i, x, cnt);

            if (!ans.isEmpty()) {
                return ans;
            }
        }

        return "";
    }

    private String build(String target, int pos, int current, int[] cnt) {

        // Find the smallest character strictly greater than target[pos].
        int greater = -1;

        for (int c = current + 1; c < 26; c++) {
            if (cnt[c] > 0) {
                greater = c;
                break;
            }
        }

        if (greater == -1) {
            return "";
        }

        cnt[greater]--;

        StringBuilder ans = new StringBuilder();

        // Prefix remains equal to target.
        ans.append(target, 0, pos);

        // Make this position strictly greater.
        ans.append((char) ('a' + greater));

        // Fill the remaining positions with smallest possible characters.
        for (int c = 0; c < 26; c++) {
            while (cnt[c] > 0) {
                ans.append((char) ('a' + c));
                cnt[c]--;
            }
        }

        return ans.toString();
    }
}