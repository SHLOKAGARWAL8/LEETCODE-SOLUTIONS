class Solution {
    public int maxProduct(int[] nums) {
       int a=0;
       int max2=0;
       for(int num:nums){
        if(num>a){
            max2=a;
            a=num;
        }
        else if(num>max2){
            max2=num;
        }
       }
       return (a - 1)*(max2 -1);
        
    }
}