class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n == 1) return 1;
        if (n == 2)
            return (nums[0] == nums[1]) ? 1 : 2;

        int max = nums[0];
        for (int num : nums)
            if (num > max)
                max = num;

        int bitLength = 32 - Integer.numberOfLeadingZeros(max);
        int maxP = 1 << bitLength;

        boolean[] set2 = new boolean[maxP];
        set2[0] = true;
        int set2Size = 1;

        for (int i = 0; i < n; i++) 
        {
            for (int j = i + 1; j < n; j++) 
            {
                int x = nums[i] ^ nums[j];
                if (!set2[x]) 
                {
                    set2[x] = true;
                    set2Size++;
                    if (set2Size == maxP)
                        return maxP;
                }
            }
        }

        boolean[] set3 = new boolean[maxP];
        int set3Size = 0;

        for (int x = 0; x < maxP; x++) 
        {
            if (!set2[x]) 
                continue;

            for (int num : nums) 
            {
                int y = x ^ num;
                if (!set3[y]) {
                    set3[y] = true;
                    set3Size++;
                    if (set3Size == maxP)
                        return maxP;
                }
            }
        }
        return set3Size;
    }
}
