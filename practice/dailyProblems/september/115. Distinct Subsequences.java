class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // If s is shorter than t, it's impossible to form t as a subsequence
        if (m < n) {
            return 0;
        }
        
        // dp[j] stores the number of distinct subsequences of s that equal t[0...j-1]
        int[] dp = new int[n + 1];
        
        // Base case: There is exactly 1 way to form an empty target string
        dp[0] = 1; 
        
        // Iterate through each character of s
        for (int i = 0; i < m; i++) {
            char charS = s.charAt(i);
            
            // Traverse t from right to left to prevent using the same character 
            // multiple times within the same iteration step.
            for (int j = n; j > 0; j--) {
                if (charS == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return dp[n];
    }
}
