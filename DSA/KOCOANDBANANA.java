class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l=1;
        int r=0;
        for(int x:piles){
            r=Math.max(x,r);
        }
        while(l<=r){
            int mid = l+(r-l)/2;
            long ho=0;
            for(int x:piles){
                ho+=(x+mid-1)/mid;
            }
            if(ho<=h){
                r=mid-1;
            }
            else{
                l=mid+1;
            }
        }
        return l;
    }
}
