class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> numbers = new HashSet<>();

        int n = digits.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {

                    // Each copy of a digit can be used only once
                    if (i == j || j == k || i == k) {
                        continue;
                    }

                    // No leading zero
                    if (digits[i] == 0) {
                        continue;
                    }

                    // Number must be even
                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];

                    numbers.add(num);
                }
            }
        }

        return numbers.size();
    }
}