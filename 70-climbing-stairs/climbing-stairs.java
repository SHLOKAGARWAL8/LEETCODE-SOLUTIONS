class Solution {
    public int climbStairs(int n) {
        int a=0;
        int b=1;
        int s=a+b;
        for(int i=1;i<=48;i++)
        {
            if(i==n)
            {
                return s;
            }
            a=b;
            b=s;
            s=a+b;
        }
        return 0;
    }
}