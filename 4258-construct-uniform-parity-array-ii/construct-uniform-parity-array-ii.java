class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;

        for (int num : nums1) {
            if (num % 2 == 0) {
                minEven = Math.min(minEven, num);
            } else {
                minOdd = Math.min(minOdd, num);
            }
        }

        // If all numbers are already odd or all are already even
        if (minOdd == Integer.MAX_VALUE || minEven == Integer.MAX_VALUE) {
            return true;
        }

        // Mixed parity: all even numbers must be
        // greater than the smallest odd number.
        return minOdd < minEven;
    }
}