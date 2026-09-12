class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // [left, right, weight, originalIndex]
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by right endpoint
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1])
                return Integer.compare(a[1], b[1]);
            return Integer.compare(a[3], b[3]);
        });

        State[][] dp = new State[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0L, new ArrayList<>());
            }
        }

        for (int i = 1; i <= n; i++) {
            int[] cur = arr[i - 1];

            // Find last interval with right < current left
            int prev = findPrevious(arr, i - 1, cur[0]);

            for (int k = 1; k <= 4; k++) {

                // Don't take current interval
                State best = dp[i - 1][k];

                // Take current interval
                State previous = dp[prev + 1][k - 1];

                List<Integer> indices =
                        new ArrayList<>(previous.indices);

                indices.add(cur[3]);
                Collections.sort(indices);

                State take = new State(
                        previous.weight + (long) cur[2],
                        indices
                );

                if (better(take, best)) {
                    best = take;
                }

                dp[i][k] = best;
            }
        }

        return dp[n][4].indices
                .stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private int findPrevious(int[][] arr, int end, int left) {
        int lo = 0;
        int hi = end - 1;
        int ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid][1] < left) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean better(State a, State b) {
        // Higher score is better
        if (a.weight != b.weight) {
            return a.weight > b.weight;
        }

        // Same score -> lexicographically smaller indices
        return lexicographicallySmaller(a.indices, b.indices);
    }

    private boolean lexicographicallySmaller(
            List<Integer> a,
            List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }

    static class State {
        long weight;              // IMPORTANT: long
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }
}