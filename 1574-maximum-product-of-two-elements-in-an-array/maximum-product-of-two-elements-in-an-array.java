class Solution {
    public int maxProduct(int[] nums) {
        int p=1,f=0;
        for(int i=0;i<nums.length;i++)
        {
            for(int j=i+1;j<nums.length;j++)
            {
                p=(nums[i]-1)*(nums[j]-1);
                if(p>f)
                    f=p;
            }
        }
        return f;
    }
}