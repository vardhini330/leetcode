class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if((long)m*k>bloomDay.length){
            return -1;
        }
        int l=Integer.MAX_VALUE;
        int r=Integer.MIN_VALUE;
        for(int x:bloomDay){
            l=Math.min(x,l);
            r=Math.max(x,r);
        }
        while(l<=r){
            int f=0;
            int b=0;
            int mid=l+(r-l)/2;
            for(int x:bloomDay){
                if(x<=mid){
                    f++;
                }
                else{
                    f=0;
                }
                if(f==k){
                    b++;
                    f=0;
                }
            }
            if(b>=m){
                r=mid-1;
            }
            else{
                l=mid+1;
            }
        }
        return l;
    }
}
