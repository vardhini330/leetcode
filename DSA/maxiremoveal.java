class Solution {
    public int maximumRemovals(String s, String p, int[] removable) {
        int n = s.length();
        int m = p.length();
        int low = 1, high = removable.length;
        char[] brr = p.toCharArray();
        int ans = 0;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(isSubsequence(s, brr, mid, removable, n, m)){
                ans = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return ans;
    }
    public static boolean isSubsequence(String s, char[] brr, int k, int[] removable, int n, int m){
        char[] arr = s.toCharArray();
        for(int i = 0; i < k; i++){
            arr[removable[i]] = '*';
        }
        int i = 0, j = 0;
        while(i < n && j < m){
            if(arr[i] == brr[j]){
                j++;
            }
            i++;
        }
        return j == m;
    }
}
