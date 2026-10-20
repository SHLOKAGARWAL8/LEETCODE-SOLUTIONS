
import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean valid = true;

            for (int start = 0; start < n && valid; start += k) {
                int[] target = Arrays.copyOfRange(sorted, start, start + k);

                boolean rotationFound = false;

                for (int shift = 0; shift < k && !rotationFound; shift++) {
                    boolean matches = true;

                    for (int i = 0; i < k; i++) {
                        if (nums[start + i] != target[(i + shift) % k]) {
                            matches = false;
                            break;
                        }
                    }

                    if (matches) rotationFound = true;
                }

                if (!rotationFound) valid = false;
            }

            if (valid) ans += k;
        }

        return ans;
    }
}