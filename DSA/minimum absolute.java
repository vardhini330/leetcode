class Solution {
    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length; 
        int ans = n; 
        Map<Integer, Integer> lastIndex = new HashMap<>(n,1.0f);

        for(int j =0; j < n ; j++){
            int x = nums[j];
            Integer i = lastIndex.get(x); 

            if(i!= null){  
                int dist = j-i;  
                ans = Math.min(ans, dist);  
            }
            int t = x;                   
            int rev = 0;                         
            while (t > 0) {           
                int digit = t % 10;     
                rev = rev * 10 + digit;  
                t = t / 10;  
            }
            lastIndex.put(rev, j);  

        }
        if (ans < n) {  
            return ans;  
        } else {          
            return -1; 
        }
        
    }
}
