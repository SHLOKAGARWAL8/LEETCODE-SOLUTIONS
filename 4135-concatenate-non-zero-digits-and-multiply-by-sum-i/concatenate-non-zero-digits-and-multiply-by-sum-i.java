class Solution {
    public long sumAndMultiply(int n) {
        int x = 0;
        int sum = 0;
        int p = 1;

        while (n > 0) {
            int digit = n % 10;

            if (digit != 0) {
                x += digit * p;
                sum += digit;
                p *= 10;
            }

            n /= 10;
        }

        return 1L * x * sum;
    }
}