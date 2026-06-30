class Solution {
    public int countPrimes(int n) {
        if (n <= 2) return 0;
        boolean[] composite = new boolean[n];
        int count = 1; // prime number 2
        for (int i = 3; i < n; i += 2)
            count++;
        for (int i = 3; i * i < n; i += 2) {
            if (!composite[i]) {
                for (int j = i * i; j < n; j += i << 1) {
                    if (!composite[j]) {
                        composite[j] = true;
                        count--;
                    }
                }
            }
        }
        return count;
    }
}