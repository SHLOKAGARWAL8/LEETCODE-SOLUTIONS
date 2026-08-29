  import java.util.*;
class Solution {
    int[] parent;
          

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> Integer.compare(nums[a], nums[b]));

        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i < n; i++) {
            int prev = indices[i - 1];
            int curr = indices[i];

            if (nums[curr] - nums[prev] <= limit) {
                union(prev, curr);
            }
        }

        Map<Integer, List<Integer>> groups = new HashMap<>();
        Map<Integer, List<Integer>> values = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = find(i);

            groups.computeIfAbsent(root, x -> new ArrayList<>()).add(i);
            values.computeIfAbsent(root, x -> new ArrayList<>()).add(nums[i]);
        }

        for (int root : groups.keySet()) {
            List<Integer> pos = groups.get(root);
            List<Integer> val = values.get(root);

            Collections.sort(pos);
            Collections.sort(val);

            for (int i = 0; i < pos.size(); i++) {
                nums[pos.get(i)] = val.get(i);
            }
        }

        return nums;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            parent[pb] = pa;
        }
    }
}