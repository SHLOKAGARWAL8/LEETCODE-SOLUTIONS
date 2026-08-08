class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] last = new int[m];
        int j = m - 1;

        for (int i = n - 1; i >= 0 && j >= 0; i--) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
        }

        int[] ans = new int[m];
        int k = 0;
        boolean changed = false;

        for (int i = 0; i < n && k < m; i++) {
            if (word1.charAt(i) == word2.charAt(k)) {
                ans[k++] = i;
            } else if (!changed && (k == m - 1 || i < last[k + 1])) {
                ans[k++] = i;
                changed = true;
            }
        }

        if (k < m) {
            return new int[0];
        }

        return ans;
    }
}