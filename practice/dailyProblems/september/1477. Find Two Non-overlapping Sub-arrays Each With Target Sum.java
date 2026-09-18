public class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        // minLen[i] stores the minimum length of a subarray that sums to target 
        // within the index range from 0 to i.
        int[] minLen = new int[n];
        // Fill the array with a large value representing "not found"
        Arrays.fill(minLen, Integer.MAX_VALUE);

        int left = 0;
        int currentSum = 0;
        int minTotalSum = Integer.MAX_VALUE;
        int currentMinLen = Integer.MAX_VALUE;

        // Sliding window: expand the right pointer
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];

            // If the sum is too large, shrink the window from the left
            while (currentSum > target && left <= right) {
                currentSum -= arr[left];
                left++;
            }

            // If we found a valid subarray that sums exactly to target
            if (currentSum == target) {
                int len = right - left + 1;

                // Check if there is a valid non-overlapping subarray to the left
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    minTotalSum = Math.min(minTotalSum, len + minLen[left - 1]);
                }

                // Update our running minimum length found so far
                currentMinLen = Math.min(currentMinLen, len);
            }

            // Save the best length found up to the current right index
            minLen[right] = currentMinLen;
        }

        // If minTotalSum wasn't updated, it means two non-overlapping subarrays don't exist
        return minTotalSum == Integer.MAX_VALUE ? -1 : minTotalSum;
    }
}
