class Solution {
    public int numPrimeArrangements(int n) {
        int c=0,d=0;
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=i;j++)
            {
                if(i%j==0)
                {
                    d++;
                }
            }
            if(d==2)
            {
                c++;
            }
            d=0;
        }
        int x=n-c;
        long s=1,t=1,y=0;
        long m=1000000007;
        for(int i=1;i<=x;i++)
        {
            s=(s*i)%m;
        }
        for(int i=1;i<=c;i++)
        {
            t=(t*i)%m;
        }
        y=t*s%m;
        return (int) y;
    }
}