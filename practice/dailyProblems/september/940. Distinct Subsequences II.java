class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        
        // addedCount[i] stores the number of unique subsequences 
        // that were newly created by the last occurrence of character ('a' + i)
        int[] addedCount = new int[26];
        int total = 0; // Total distinct subsequences found so far
        
        for (char c : s.toCharArray()) {
            int charIdx = c - 'a';
            
            // Subsequences added by current char = (total - previous_contribution_of_this_char + 1)
            int newlyAdded = (total - addedCount[charIdx] + 1) % MOD;
            
            // Handle negative results due to modulo operations in Java
            if (newlyAdded < 0) {
                newlyAdded += MOD;
            }
            
            // Update total and record this character's latest contribution
            total = (total + newlyAdded) % MOD;
            addedCount[charIdx] = (addedCount[charIdx] + newlyAdded) % MOD;
        }
        
        return total;
    }
}
