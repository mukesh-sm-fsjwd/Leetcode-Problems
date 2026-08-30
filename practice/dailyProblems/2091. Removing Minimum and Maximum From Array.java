class Solution {

    public int minimumDeletions(int[] nums) {

        int arrayLength = nums.length;

        // Find the positions of the smallest and largest numbers
        int minimumPosition = 0;
        int maximumPosition = 0;

        for (int i = 1; i < arrayLength; i++) {

            if (nums[i] < nums[minimumPosition]) {
                minimumPosition = i;
            }

            if (nums[i] > nums[maximumPosition]) {
                maximumPosition = i;
            }
        }

        // Put the smaller position first
        int firstPosition = Math.min(minimumPosition, maximumPosition);
        int secondPosition = Math.max(minimumPosition, maximumPosition);

        /*
         * There are only three ways to remove both numbers:
         *
         * 1. Remove both from the beginning
         * 2. Remove both from the end
         * 3. Remove the first one from the beginning
         *    and the second one from the end
         */

        // Remove both numbers from the beginning
        int removeFromBeginning = secondPosition + 1;

        // Remove both numbers from the end
        int removeFromEnd = arrayLength - firstPosition;

        // Remove one from the beginning and the other from the end
        int removeFromBothEnds = (firstPosition + 1)
                + (arrayLength - secondPosition);

        // Return the minimum number of deletions
        return Math.min(
                removeFromBeginning,
                Math.min(removeFromEnd, removeFromBothEnds)
        );
    }
}
