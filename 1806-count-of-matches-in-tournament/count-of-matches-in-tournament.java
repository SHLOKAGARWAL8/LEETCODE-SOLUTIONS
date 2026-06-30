class Solution {
    public int numberOfMatches(int n) {
      int m=0;
      while(n>1)
      {
        if(n%2!=0)
        {
            n--;
            m=m+n/2;
            n/=2;
            n++;
        }
        else
        {
            m=m+n/2;
            n/=2;
        }
      }
      return m;   
    }
}