
class Solution {
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;

        long even = 1; // Empty prefix sum is even
        long odd = 0;
        long prefixSum = 0;
        long count = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 == 0) {
                count += odd;
                even++;
            } else {
                count += even;
                odd++;
            }

            count %= MOD;
        }

        return (int) count;
    }
}
