class Solution 
{
    public int subtractProductAndSum(int n) 
    {
        int d=0,p=1,s=0;
        while(n!=0)
        {
            d=n%10;
            n/=10;
            s=s+d;
            p=p*d;
        }   
        return p-s;
    }
}