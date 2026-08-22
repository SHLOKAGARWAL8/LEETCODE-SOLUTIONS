class Solution {
    public boolean checkDivisibility(int n) {
       int p=1,s=0,c=n,d=0,f=0;
       while(c>0)
       {
            d=c%10;
            c=c/10;
            p=p*d;
            s=s+d;
       }
       f=p+s;
        if(n%f==0)
            return true;
        else
            return false;
    }
}