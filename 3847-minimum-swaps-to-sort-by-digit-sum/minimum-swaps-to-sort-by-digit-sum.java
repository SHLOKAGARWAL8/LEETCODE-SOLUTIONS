import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Integer[] order = new Integer[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> {
            int sumA = digitSum(nums[a]);
            int sumB = digitSum(nums[b]);

            if (sumA != sumB) {
                return Integer.compare(sumA, sumB);
            }
            return Integer.compare(nums[a], nums[b]);
        });

        int[] target = new int[n];
        for (int i = 0; i < n; i++) {
            target[order[i]] = i;
        }

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i] || target[i] == i) {
                continue;
            }

            int cycleLength = 0;
            int j = i;

            while (!visited[j]) {
                visited[j] = true;
                j = target[j];
                cycleLength++;
            }

            swaps += cycleLength - 1;
        }

        return swaps;
    }

    private int digitSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }
}