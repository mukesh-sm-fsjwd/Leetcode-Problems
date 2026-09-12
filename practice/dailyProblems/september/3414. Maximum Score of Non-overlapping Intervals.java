class Solution {
    // Custom wrapper class to store interval details and track original indices
    private static class Interval {
        int start, end, weight, id;
        Interval(int start, int end, int weight, int id) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.id = id;
        }
    }

    // DP State class to keep track of total weight and the chosen list of original indices
    private static class State {
        long weight;
        List<Integer> ids;

        State(long weight, List<Integer> ids) {
            this.weight = weight;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {
        int n = intervalsList.size();
        Interval[] intervals = new Interval[n];
        
        for (int i = 0; i < n; i++) {
            List<Integer> seq = intervalsList.get(i);
            intervals[i] = new Interval(seq.get(0), seq.get(1), seq.get(2), i);
        }

        // Sort primarily by end time. If end times are equal, sorting by start time helps consistency.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.end, b.end));

        // dp[i][k] represents the optimal State using a subset of the first i intervals with k choices
        State[][] dp = new State[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new State(0L, new ArrayList<>());
            }
        }

        // Precompute the latest non-overlapping interval for each interval using binary search
        int[] prevIdx = new int[n];
        for (int i = 0; i < n; i++) {
            prevIdx[i] = binarySearch(intervals, i, intervals[i].start);
        }

        // Fill out the DP table
        for (int i = 1; i <= n; i++) {
            Interval curr = intervals[i - 1];
            int p = prevIdx[i - 1]; // 0-indexed index of the last non-overlapping interval, or -1

            for (int k = 1; k <= 4; k++) {
                // Option 1: Exclude the current interval
                State bestState = dp[i - 1][k];
                long maxW = bestState.weight;
                List<Integer> bestIds = bestState.ids;

                // Option 2: Include the current interval
                State prevState = dp[p + 1][k - 1];
                long takeW = prevState.weight + curr.weight;
                
                // Form the new lexicographically sorted index list if we take this interval
                List<Integer> takeIds = new ArrayList<>(prevState.ids);
                takeIds.add(curr.id);
                Collections.sort(takeIds); 

                // Compare weights or break ties lexicographically
                if (takeW > maxW) {
                    maxW = takeW;
                    bestIds = takeIds;
                } else if (takeW == maxW && maxW > 0) {
                    if (isLexicographicallySmaller(takeIds, bestIds)) {
                        bestIds = takeIds;
                    }
                }

                dp[i][k] = new State(maxW, bestIds);
            }
        }

        // Extract the optimal index path from the final global state
        List<Integer> ansList = dp[n][4].ids;
        int[] ans = new int[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    // Binary search to find the last interval that ends strictly before the current target start time
    private int binarySearch(Interval[] intervals, int right, int targetStart) {
        int low = 0, high = right - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (intervals[mid].end < targetStart) {
                ans = mid;
                low = mid + 1; // Try to find a closer one
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // Tie-breaker method to check if list 'a' is lexicographically smaller than list 'b'
    private boolean isLexicographicallySmaller(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() < b.size();
    }
}
