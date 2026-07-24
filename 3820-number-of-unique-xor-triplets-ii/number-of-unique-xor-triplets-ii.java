class Solution {
    public int uniqueXorTriplets(int[] nums) {
         
         int max = 0;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        int size = 1;
        while (size <= max) {
            size <<= 1;
        }

        long[] frequency = new long[size];

        for (int num : nums) {
            frequency[num] = 1;
        }

        transform(frequency);

        for (int i = 0; i < size; i++) {
            frequency[i] = frequency[i]
                         * frequency[i]
                         * frequency[i];
        }

        transform(frequency);

        int answer = 0;
        for (long count : frequency) {
            if (count / size > 0) {
                answer++;
            }
        }

        return answer;
    }

    private void transform(long[] values) {
        for (int length = 1; length < values.length; length <<= 1) {
            for (int start = 0; start < values.length; start += length << 1) {
                for (int i = 0; i < length; i++) {
                    long left = values[start + i];
                    long right = values[start + i + length];

                    values[start + i] = left + right;
                    values[start + i + length] = left - right;
                }
            }
        }
    }
}