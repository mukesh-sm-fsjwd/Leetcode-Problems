class Solution {
    public long[] resultArray(int[] nums, int k) {
        int n = nums.length;
        long[] ans = new long[k];
        
        // currentCounts[r] tracks how many subarrays ending at the 
        // previous index have a product modulo k equal to r.
        long[] currentCounts = new long[k];
        
        for (int i = 0; i < n; i++) {
            // Take the current number modulo k
            int currentMod = nums[i] % k;
            
            // Temporary array to store transitions for the current element
            long[] nextCounts = new long[k];
            
            // 1. A new subarray starting exactly at index i
            nextCounts[currentMod]++;
            
            // 2. Extend all existing subarrays ending at index i - 1
            for (int r = 0; r < k; r++) {
                if (currentCounts[r] > 0) {
                    int nextMod = (r * currentMod) % k;
                    nextCounts[nextMod] += currentCounts[r];
                }
            }
            
            // 3. Accumulate the active counts into the final answer array
            for (int r = 0; r < k; r++) {
                ans[r] += nextCounts[r];
            }
            
            // Move to the next element
            currentCounts = nextCounts;
        }
        
        return ans;
    }
}
