class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        int[][] dp = new int[n][n];
        int[][] maxLeft = new int[n][n];
        int[][] maxRight = new int[n][n];

        for (int i = 0; i < n; i++) {
            maxLeft[i][i] = stoneValue[i];
            maxRight[i][i] = stoneValue[i];
        }

        for (int l = n - 2; l >= 0; l--) {
            int mid = l;

            for (int r = l + 1; r < n; r++) {

                while (mid < r - 1 && getSum(prefix, l, mid) < getSum(prefix, mid + 1, r)) {
                    mid++;
                }

                int best = 0;

                if (mid > l) {
                    best = Math.max(best, maxLeft[l][mid - 1]);
                }

                int leftSum = getSum(prefix, l, mid);
                int rightSum = getSum(prefix, mid + 1, r);

                if (leftSum < rightSum) {
                    best = Math.max(best, leftSum + dp[l][mid]);
                } else if (leftSum > rightSum) {
                    best = Math.max(best, rightSum + dp[mid + 1][r]);
                } else {
                    best = Math.max(best, leftSum + Math.max(dp[l][mid], dp[mid + 1][r]));
                }

                if (mid + 2 <= r) {
                    best = Math.max(best, maxRight[mid + 2][r]);
                }

                dp[l][r] = best;

                int totalSum = getSum(prefix, l, r);
                maxLeft[l][r] = Math.max(maxLeft[l][r - 1], totalSum + dp[l][r]);
                maxRight[l][r] = Math.max(maxRight[l + 1][r], totalSum + dp[l][r]);
            }
        }

        return dp[0][n - 1];
    }

    private int getSum(int[] prefix, int l, int r) {
        return prefix[r + 1] - prefix[l];
    }
}
