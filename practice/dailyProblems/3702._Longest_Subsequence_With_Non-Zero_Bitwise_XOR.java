class Solution {
    public int longestSubsequence(int[] nums) {
        int total = 0 , n = nums.length;
        boolean nonZero = false;

        for(int num : nums){
            nonZero |= num > 0;
            total ^= num;
        }

        if(!nonZero) return 0;
        return total == 0 ? n - 1 : n;
    }
}

