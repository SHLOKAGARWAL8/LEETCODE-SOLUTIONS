class Solution {
    public boolean containsDuplicate(int[] nums) {

        int n = nums.length;

        int negCount = 0;
        for (int x : nums) {
            if (x < 0)
                negCount++;
        }

        int[] neg = new int[negCount];
        int[] pos = new int[n - negCount];

        int a = 0, b = 0;

        for (int x : nums) {
            if (x < 0)
                neg[a++] = -x;
            else
                pos[b++] = x;
        }

        radixSort(neg);
        radixSort(pos);

        int k = 0;

        for (int i = neg.length - 1; i >= 0; i--)
            nums[k++] = -neg[i];

        for (int i = 0; i < pos.length; i++)
            nums[k++] = pos[i];

        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1])
                return true;
        }

        return false;
    }

    private void radixSort(int[] arr) {

        if (arr.length == 0)
            return;

        int max = arr[0];

        for (int x : arr)
            if (x > max)
                max = x;

        for (int exp = 1; max / exp > 0; exp *= 10) {

            int[] output = new int[arr.length];
            int[] count = new int[10];

            for (int x : arr)
                count[(x / exp) % 10]++;

            for (int i = 1; i < 10; i++)
                count[i] += count[i - 1];

            for (int i = arr.length - 1; i >= 0; i--) {
                int digit = (arr[i] / exp) % 10;
                output[--count[digit]] = arr[i];
            }

            for (int i = 0; i < arr.length; i++)
                arr[i] = output[i];
        }
    }
}