class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);

        List<Integer> result = new ArrayList<>();

        for (int i = 1; i < nums.length; i++) {
            for (int value = nums[i - 1] + 1; value < nums[i]; value++) {
                result.add(value);
            }
        }

        return result;
    }
}