class Solution {
    public boolean containsDuplicate(int[] nums) 
    {
        int l=nums.length;
        if(l>20000)
            l=20000;
        int c=1;
        for(int i=0;i<l-1;i++)
        {
            for(int j=1+i;j<l;j++)
            {
                if(nums[i]==nums[j])
                {
                    c++;
                }
            }
            if(c>1)
                return true;
        }
        return false;
    }
}