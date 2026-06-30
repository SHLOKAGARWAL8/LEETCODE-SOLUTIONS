class Solution {
    public double myPow(double x, int n) {
        double r=0.0;
     if(x>-100 && x<100 && x!=0 )
        r=Math.pow(x,n);
      if(r>=-10000 && r<=10000)
            return r;
     return 0;
    }  
}