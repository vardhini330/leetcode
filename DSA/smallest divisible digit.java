class Solution {
    private static final int[][] DIGIT_FACTORS = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    public String smallestNumber(String numStr, long t) {
        // Step 1: Prime factorize t
        int req2 = 0, req3 = 0, req5 = 0, req7 = 0;
        long temp = t;
        while (temp % 2 == 0) { req2++; temp /= 2; }
        while (temp % 3 == 0) { req3++; temp /= 3; }
        while (temp % 5 == 0) { req5++; temp /= 5; }
        while (temp % 7 == 0) { req7++; temp /= 7; }
        if (temp > 1) return "-1"; // Invalid factor

        int minReqDigits = getMinDigitsCount(req2, req3, req5, req7);
        char[] num = numStr.toCharArray();
        int n = num.length;

        if (minReqDigits > n) {
            return constructMinimal(req2, req3, req5, req7, minReqDigits);
        }

        // Find first zero position and count factors up to prefix
        int firstZero = -1;
        int pref2 = 0, pref3 = 0, pref5 = 0, pref7 = 0;

        for (int i = 0; i < n; i++) {
            if (num[i] == '0') {
                firstZero = i;
                break;
            }
            int d = num[i] - '0';
            int[] f = DIGIT_FACTORS[d];
            pref2 += f[0]; pref3 += f[1]; pref5 += f[2]; pref7 += f[3];
        }

        // Check if num itself is valid (no zeros and sufficient factors)
        if (firstZero == -1 && pref2 >= req2 && pref3 >= req3 && pref5 >= req5 && pref7 >= req7) {
            return numStr;
        }

        // If a zero exists, limit search to the first zero position
        int limit = (firstZero == -1) ? n - 1 : firstZero;

        // Try replacing digit at index i with a larger digit
        for (int i = limit; i >= 0; i--) {
            int d = num[i] - '0';
            
            // Backtrack: subtract current digit factors from running prefix
            int[] currentF = DIGIT_FACTORS[d];
            pref2 -= currentF[0];
            pref3 -= currentF[1];
            pref5 -= currentF[2];
            pref7 -= currentF[3];

            int spaceRemaining = n - 1 - i;

            for (int bigger = d + 1; bigger <= 9; bigger++) {
                int[] f = DIGIT_FACTORS[bigger];
                int need2 = Math.max(0, req2 - pref2 - f[0]);
                int need3 = Math.max(0, req3 - pref3 - f[1]);
                int need5 = Math.max(0, req5 - pref5 - f[2]);
                int need7 = Math.max(0, req7 - pref7 - f[3]);

                int spaceNeeded = getMinDigitsCount(need2, need3, need5, need7);

                if (spaceNeeded <= spaceRemaining) {
                    char[] res = new char[n];
                    System.arraycopy(num, 0, res, 0, i);
                    res[i] = (char) ('0' + bigger);

                    int idx = i + 1;
                    int onesToPad = spaceRemaining - spaceNeeded;
                    while (onesToPad-- > 0) res[idx++] = '1';

                    fillFactorDigits(res, idx, need2, need3, need5, need7);
                    return new String(res);
                }
            }
        }

        return constructMinimal(req2, req3, req5, req7, Math.max(n + 1, minReqDigits));
    }

    private static int getMinDigitsCount(int c2, int c3, int c5, int c7) {
        int count8 = c2 / 3, rem2 = c2 % 3;
        int count9 = c3 / 2, rem3 = c3 % 2;
        int count4 = rem2 / 2; rem2 %= 2;
        int count2 = rem2, count3 = rem3, count6 = 0;

        if (count2 == 1 && count3 == 1) {
            count2 = 0; count3 = 0; count6 = 1;
        } else if (count3 == 1 && count4 == 1) {
            count2 = 1; count6 = 1; count3 = 0; count4 = 0;
        }

        return count2 + count3 + count4 + c5 + count6 + c7 + count8 + count9;
    }

    private static int fillFactorDigits(char[] res, int idx, int c2, int c3, int c5, int c7) {
        int count8 = c2 / 3, rem2 = c2 % 3;
        int count9 = c3 / 2, rem3 = c3 % 2;
        int count4 = rem2 / 2; rem2 %= 2;
        int count2 = rem2, count3 = rem3, count6 = 0;

        if (count2 == 1 && count3 == 1) {
            count2 = 0; count3 = 0; count6 = 1;
        } else if (count3 == 1 && count4 == 1) {
            count2 = 1; count6 = 1; count3 = 0; count4 = 0;
        }

        while (count2-- > 0) res[idx++] = '2';
        while (count3-- > 0) res[idx++] = '3';
        while (count4-- > 0) res[idx++] = '4';
        while (c5-- > 0)     res[idx++] = '5';
        while (count6-- > 0) res[idx++] = '6';
        while (c7-- > 0)     res[idx++] = '7';
        while (count8-- > 0) res[idx++] = '8';
        while (count9-- > 0) res[idx++] = '9';

        return idx;
    }

    private static String constructMinimal(int c2, int c3, int c5, int c7, int targetLength) {
        char[] res = new char[targetLength];
        int factorLen = fillFactorDigits(res, 0, c2, c3, c5, c7);
        int onesNeeded = targetLength - factorLen;

        System.arraycopy(res, 0, res, onesNeeded, factorLen);
        for (int i = 0; i < onesNeeded; i++) {
            res[i] = '1';
        }

        return new String(res);
    }
}
