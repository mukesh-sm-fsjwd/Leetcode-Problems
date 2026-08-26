class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int minLength = n + 1;
        int bestLeft = -1;
        int onesCount = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                onesCount++;
            }

            // Shrink the window when we have exactly or more than k ones
            while (onesCount == k) {
                int currentLength = right - left + 1;

                // Condition 1: Found a strictly shorter beautiful substring
                if (currentLength < minLength) {
                    minLength = currentLength;
                    bestLeft = left;
                } 
                // Condition 2: Found same length, check if lexicographically smaller
                else if (currentLength == minLength) {
                    String currentSub = s.substring(left, left + minLength);
                    String bestSub = s.substring(bestLeft, bestLeft + minLength);
                    if (currentSub.compareTo(bestSub) < 0) {
                        bestLeft = left;
                    }
                }

                // Move left pointer to find other candidates
                if (s.charAt(left) == '1') {
                    onesCount--;
                }
                left++;
            }
        }

        return bestLeft == -1 ? "" : s.substring(bestLeft, bestLeft + minLength);
    }
}
