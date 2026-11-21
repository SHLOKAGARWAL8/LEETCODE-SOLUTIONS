
import java.util.*;

class Solution {
    List<Integer>[] tree;
    List<Integer>[] newTree;
    int[] ans;
    int[] last;
    String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;

        tree = new ArrayList[n];
        newTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        // Build original tree
        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        last = new int[26];
        Arrays.fill(last, -1);

        // Find new parents using original tree
        buildNewTree(0);

        // Calculate subtree sizes
        ans = new int[n];
        calculateSize(0);

        return ans;
    }

    void buildNewTree(int node) {
        int ch = s.charAt(node) - 'a';

        int old = last[ch];
        last[ch] = node;

        for (int child : tree[node]) {
            int c = s.charAt(child) - 'a';

            int newParent = last[c];

            if (newParent == -1) {
                newParent = node;
            }

            newTree[newParent].add(child);

            buildNewTree(child);
        }

        last[ch] = old;
    }

    int calculateSize(int node) {
        int size = 1;

        for (int child : newTree[node]) {
            size += calculateSize(child);
        }

        ans[node] = size;
        return size;
    }
}
