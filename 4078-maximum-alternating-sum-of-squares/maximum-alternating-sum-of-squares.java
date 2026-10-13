class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] squares = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        java.util.Arrays.sort(squares);

        long sum = 0;
        for (int i = 0; i < squares.length; i++) {
            if (i < squares.length / 2) {
                sum -= squares[i];
            } else {
                sum += squares[i];
            }
        }

        return sum;
    }
}