class Solution {
    int[] valid={0,1,2,5,6,8,9};
    int[][][] dp;
    private int f(int i,int tight,int diff,String N){
        if(i==N.length()){
            if(diff==1) return 1;
            return 0;
        }
        if(dp[i][tight][diff]!=-1) return dp[i][tight][diff];
        int lim=(tight==1)?N.charAt(i)-'0':9;
        int ans=0;
        for(int d:valid){
            if(d>lim) continue;
            int nt=(tight==1 && lim==d)?1:0;
            if(d==0 || d==1 || d==8){
                ans+=f(i+1,nt,diff,N);
            }else{
                ans+=f(i+1,nt,1,N);
            }
        }
        return dp[i][tight][diff]=ans;
    }
    public int rotatedDigits(int n) {
        String s=String.valueOf(n);
        dp=new int[s.length()+1][2][2];
        for(int i=0;i<=s.length();i++){
            Arrays.fill(dp[i][0],-1);
            Arrays.fill(dp[i][1],-1);
        }
        return f(0,1,0,s);
    }
}
