class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        Arrays.fill(left, n);
        Arrays.fill(right, -1);
        
        // Step 1: Record the leftmost and rightmost index for each character
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            left[idx] = Math.min(left[idx], i);
            right[idx] = i;
        }
        
        List<String> result = new ArrayList<>();
        int prevRight = -1; // Track the right boundary of the last added substring
        
        // Step 2 & 3: Iterate through the string and eagerly construct substrings
        for (int i = 0; i < n; i++) {
            // We only care about starting an interval at a character's first appearance
            if (i != left[s.charAt(i) - 'a']) {
                continue;
            }
            
            int newRight = checkValidInterval(s, i, left, right);
            
            if (newRight != -1) {
                // If the new interval is within the previous one, it's smaller and non-overlapping.
                // Replace the last one to satisfy the "minimum total length" constraint.
                if (i <= prevRight && !result.isEmpty()) {
                    result.set(result.size() - 1, s.substring(i, newRight + 1));
                } else {
                    result.add(s.substring(i, newRight + 1));
                }
                prevRight = newRight;
            }
        }
        
        return result;
    }
    
    // Helper function to find the valid right bound or return -1 if invalid
    private int checkValidInterval(String s, int start, int[] left, int[] right) {
        int rBound = right[s.charAt(start) - 'a'];
        
        for (int j = start; j <= rBound; j++) {
            int currCharIdx = s.charAt(j) - 'a';
            
            // If any nested character started before our 'start', this interval is invalid
            if (left[currCharIdx] < start) {
                return -1;
            }
            // Expand the right boundary to include all occurrences of the nested character
            rBound = Math.max(rBound, right[currCharIdx]);
        }
        
        return rBound;
    }
}
