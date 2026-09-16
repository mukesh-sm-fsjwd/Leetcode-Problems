class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        
        int[] dp = new int[k + 1];
        int[] sum = new int[k + 1];
        
        dp[0] = 1;
        sum[0] = 1;
        
        for (int i = 1; i < n; i++) {
            for (int j = k; j > 0; j--) {
                dp[j] = (dp[j] + sum[j - 1]) % MOD;
            }
            for (int j = 0; j <= k; j++) {
                sum[j] = (sum[j] + dp[j]) % MOD;
            }
        }
        
        return dp[k];
    }
}
