class Solution {
    public int gcdOfOddEvenSums(int n) {
        int sumOdd = 1 * n * n;
        int sumEven = 1 * n * (n + 1);

        while (sumEven != 0) {
            int temp = sumEven;
            sumEven = sumOdd % sumEven;
            sumOdd = temp;
        }

        return sumOdd;
    }
}