class Solution {
    public long sumAndMultiply(int n) {
        long x=0,d=0,s=0,y=0;
        while(n!=0)
        {
            d=n%10;
            n/=10;
            if(d!=0)
            {
                    x=x*10+d;
                    s=s+d;
            }
        }
        while(x!=0)
        {
            d=x%10;
            x/=10;
            y=y*10+d;
        }
        return y*s;
    }
}