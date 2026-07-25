class Solution {
    public int maxProduct(int n) {
        int copy=n,d=0,p=0,f=0,c=0;

        while(n>0)
        {
           n=n/10;
            c++;
        }
        int a[]=new int[c];
    for(int i=0;i<c;i++)
    {
        d=copy%10;
        copy=copy/10;
        a[i]=d;
    }
    for(int i=0;i<c;i++)
    {
        for(int j=i+1;j<c;j++)
        {
            p=a[i]*a[j];
            if(p>f)
                f=p;
        }
    }
    return f;
    }
}