class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Store each number along with its original index
        int[][] numberWithIndex = new int[n][2];

        for (int i = 0; i < n; i++) {
            numberWithIndex[i][0] = nums[i];
            numberWithIndex[i][1] = i;
        }

        // Sort the numbers based on their values
        Arrays.sort(numberWithIndex, (a, b) -> Integer.compare(a[0], b[0]));

        int[] answer = new int[n];

        int groupStart = 0;

        while (groupStart < n) {
            int groupEnd = groupStart;

            // Find all numbers that can be connected using the limit
            while (groupEnd + 1 < n &&
                   numberWithIndex[groupEnd + 1][0] - numberWithIndex[groupEnd][0] <= limit) {
                groupEnd++;
            }

            int groupSize = groupEnd - groupStart + 1;

            // Store the original indexes of this group
            int[] originalIndexes = new int[groupSize];

            for (int i = 0; i < groupSize; i++) {
                originalIndexes[i] = numberWithIndex[groupStart + i][1];
            }

            // Sort indexes so that smaller values can go to smaller indexes
            Arrays.sort(originalIndexes);

            // Put the sorted values into the sorted indexes
            for (int i = 0; i < groupSize; i++) {
                int index = originalIndexes[i];
                int value = numberWithIndex[groupStart + i][0];

                answer[index] = value;
            }

            // Move to the next group
            groupStart = groupEnd + 1;
        }

        return answer;
    }
}
