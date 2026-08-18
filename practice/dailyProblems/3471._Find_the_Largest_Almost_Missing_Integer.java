import java.util.Arrays;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // Handle Case 1: k equals the full array length
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                if (num > maxVal) maxVal = num;
            }
            return maxVal;
        }
        
        // Count global frequencies using a fixed array (Constraint: nums[i] <= 50)
        int[] freq = new int[51];
        for (int num : nums) {
            freq[num]++;
        }
        
        // Handle Case 2: k = 1 (Find the largest globally unique element)
        if (k == 1) {
            int maxVal = -1;
            for (int i = 50; i >= 0; i--) {
                if (freq[i] == 1) return i; 
            }
            return -1;
        }
        
        // Handle Case 3: 1 < k < n (Only boundaries can appear in exactly one window)
        int first = nums[0];
        int last = nums[n - 1];
        int ans = -1;
        
        if (freq[first] == 1) ans = Math.max(ans, first);
        if (freq[last] == 1) ans = Math.max(ans, last);
        
        return ans;
    }
}
