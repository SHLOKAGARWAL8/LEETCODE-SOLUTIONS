class Solution {
    int n, k;
    long[][] cnt;
    int[] prod;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        cnt = new long[4 * n][k];
        prod = new int[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            update(1, 0, n - 1, index, value);

            Node res = query(1, 0, n - 1, start, n - 1);
            ans[q] = (int) res.cnt[x];
        }

        return ans;
    }

    void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            prod[node] = nums[l] % k;
            cnt[node][prod[node]] = 1;
            return;
        }

        int mid = (l + r) / 2;
        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);
        pull(node);
    }

    void update(int node, int l, int r, int idx, int value) {
        if (l == r) {
            prod[node] = value % k;

            for (int i = 0; i < k; i++)
                cnt[node][i] = 0;

            cnt[node][prod[node]] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid)
            update(node * 2, l, mid, idx, value);
        else
            update(node * 2 + 1, mid + 1, r, idx, value);

        pull(node);
    }

    void pull(int node) {
        int left = node * 2;
        int right = node * 2 + 1;

        prod[node] = (prod[left] * prod[right]) % k;

        for (int i = 0; i < k; i++) {
            cnt[node][i] = cnt[left][i];

            for (int j = 0; j < k; j++) {
                int rem = (prod[left] * j) % k;
                if (rem == i)
                    cnt[node][i] += cnt[right][j];
            }
        }
    }

    Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return new Node(prod[node], cnt[node], k);
        }

        int mid = (l + r) / 2;

        if (qr <= mid)
            return query(node * 2, l, mid, ql, qr);

        if (ql > mid)
            return query(node * 2 + 1, mid + 1, r, ql, qr);

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    Node merge(Node a, Node b) {
        long[] c = new long[k];

        for (int i = 0; i < k; i++)
            c[i] = a.cnt[i];

        for (int j = 0; j < k; j++) {
            int rem = (a.prod * j) % k;
            c[rem] += b.cnt[j];
        }

        return new Node((a.prod * b.prod) % k, c, k);
    }

    static class Node {
        int prod;
        long[] cnt;

        Node(int prod, long[] cnt, int k) {
            this.prod = prod;
            this.cnt = cnt.clone();
        }
    }
}