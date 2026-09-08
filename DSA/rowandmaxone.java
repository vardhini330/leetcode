class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int maxone=0;
        int ri=0;
        for(int i=0;i<mat.length;i++){
            int count=0;
            for(int j=0;j<mat[i].length;j++){
                if(mat[i][j] == 1){
                    count++;
                }
            }
            if(count > maxone){
                maxone=count;
                ri=i;
            }
        } 
        return new int[]{ri,maxone};
    }
}
