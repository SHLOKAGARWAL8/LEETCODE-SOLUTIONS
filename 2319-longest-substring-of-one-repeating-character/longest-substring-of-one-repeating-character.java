class Solution {
    int[] best, prefix, suffix;
    char[] leftChar, rightChar;
    int n;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        n = s.length();

        best = new int[4 * n];
        prefix = new int[4 * n];
        suffix = new int[4 * n];
        leftChar = new char[4 * n];
        rightChar = new char[4 * n];

        build(s.toCharArray(), 1, 0, n - 1);

        int[] ans = new int[queryIndices.length];
        char[] arr = s.toCharArray();

        for (int i = 0; i < queryIndices.length; i++) {
            int idx = queryIndices[i];

            arr[idx] = queryCharacters.charAt(i);

            update(idx, arr[idx], 1, 0, n - 1);

            ans[i] = best[1];
        }

        return ans;
    }

    private void build(char[] arr, int node, int l, int r) {
        if (l == r) {
            best[node] = 1;
            prefix[node] = 1;
            suffix[node] = 1;
            leftChar[node] = arr[l];
            rightChar[node] = arr[l];
            return;
        }

        int mid = (l + r) / 2;

        build(arr, node * 2, l, mid);
        build(arr, node * 2 + 1, mid + 1, r);

        merge(node, node * 2, node * 2 + 1, mid - l + 1, r - mid);
    }

    private void update(int idx, char ch, int node, int l, int r) {
        if (l == r) {
            best[node] = 1;
            prefix[node] = 1;
            suffix[node] = 1;
            leftChar[node] = ch;
            rightChar[node] = ch;
            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid) {
            update(idx, ch, node * 2, l, mid);
        } else {
            update(idx, ch, node * 2 + 1, mid + 1, r);
        }

        merge(node, node * 2, node * 2 + 1, mid - l + 1, r - mid);
    }

    private void merge(int node, int L, int R, int lenL, int lenR) {
        leftChar[node] = leftChar[L];
        rightChar[node] = rightChar[R];

        prefix[node] = prefix[L];
        suffix[node] = suffix[R];

        best[node] = Math.max(best[L], best[R]);

        if (rightChar[L] == leftChar[R]) {
            best[node] = Math.max(best[node], suffix[L] + prefix[R]);

            if (prefix[L] == lenL) {
                prefix[node] = lenL + prefix[R];
            }

            if (suffix[R] == lenR) {
                suffix[node] = lenR + suffix[L];
            }
        }
    }
}