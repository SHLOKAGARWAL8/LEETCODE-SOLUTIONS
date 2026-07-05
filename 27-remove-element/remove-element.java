class Solution {
    public int removeElement(int[] nums, int val) {
        int l=nums.length;
        int c=0,e=0;
        for(int i=0;i<l;i++)
        {
            if(nums[i]!=val)
            {
                nums[i-e]=nums[i];
                c++;
            }
            else
            {
                e++;
            }
        }
        return c;
    }
}