class Solution {

    int[] suffix;
    int[][] dp;
    int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;

        suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        dp = new int[n][n + 1];

        return solve(0, 1);
    }

    private int solve(int i, int M) {

        if (i >= n) {
            return 0;
        }

        if (i + 2 * M >= n) {
            return suffix[i];
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int best = 0;
        for (int X = 1; X <= 2 * M; X++) {

            int opponent = solve(i + X, Math.max(M, X));

            int current = suffix[i] - opponent;

            best = Math.max(best, current);
        }

        dp[i][M] = best;

        return best;
    }
}
