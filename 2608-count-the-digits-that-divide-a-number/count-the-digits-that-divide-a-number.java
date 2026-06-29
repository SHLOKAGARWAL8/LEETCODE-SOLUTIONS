class Solution {
    public int countDigits(int num) {
        int d=0,n=num,c=0;
        if(num>=1 && num<=(int)Math.pow(10,9))
        {
           while(n!=0) 
           {
                d=n%10;
                if(d==0)
                {
                    return 0;
                }
                else if(num%d==0)
                {
                    c++;
                }
                n/=10;
           }
           return c;
        }
        return 0;
    }
}