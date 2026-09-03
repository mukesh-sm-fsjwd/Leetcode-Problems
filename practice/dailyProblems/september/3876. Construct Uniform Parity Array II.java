class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;

        // Pass 1: Find the minimum odd number in the array
        for (int num : nums1) {
            if ((num & 1) == 1) { // Optimized bitwise odd check
                if (num < minOdd) {
                    minOdd = num;
                }
            }
        }

        // If no odd numbers exist, the array is already uniformly even
        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }

        // Pass 2: Ensure no even number is smaller than the minimum odd number
        for (int num : nums1) {
            if ((num & 1) == 0) { // Even number check
                if (num <= minOdd) {
                    return false;
                }
            }
        }

        return true;
    }
}
