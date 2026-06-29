class Solution {
    public boolean isPalindrome(int x) {
        if(x<0 || (x%10==0 && x!=0))
            return false;
        int copy=x,d=0,s=0;
        while(copy>0)
        {
            d=copy%10;
            s=s*10+d;
            copy/=10;
        }
        return s==x;}
}