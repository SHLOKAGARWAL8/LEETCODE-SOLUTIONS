class Solution {
    public int findGCD(int[] nums) 
    {
        int s=nums[0];
        int l=nums[0];
        for(int i=0;i<nums.length;i++)
        {
            if(l>nums[i])
            {
                l=nums[i];
            }
            if(s<nums[i])
            {
                s=nums[i];
            }
        }
        while(s!=0)
        {
            int t=s;
            s=l%s;
            l=t;
        }
        return l;
    }
}