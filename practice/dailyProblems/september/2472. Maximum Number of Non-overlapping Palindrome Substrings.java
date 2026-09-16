class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        if (k == 1) return n; // Every character is a palindrome of length 1
        
        // dp[i] stores the maximum number of valid palindromes in the first i characters
        int[] dp = new int[n + 1];
        char[] chars = s.toCharArray();
        
        for (int i = 1; i <= n; i++) {
            // By default, the answer for the first i characters is at least the answer for the first i-1 characters
            dp[i] = dp[i - 1];
            
            // Case 1: Check if there's a palindrome of length k ending at index i-1
            if (i >= k && isPalindrome(chars, i - k, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k] + 1);
            }
            
            // Case 2: Check if there's a palindrome of length k + 1 ending at index i-1
            if (i >= k + 1 && isPalindrome(chars, i - k - 1, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k - 1] + 1);
            }
        }
        
        return dp[n];
    }
    
    // Helper method to check if a substring is a palindrome
    private boolean isPalindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
