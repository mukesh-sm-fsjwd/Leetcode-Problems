class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        
        // standard array instead of arraylist to keep runtime fast and avoid GC pressure
        int[] ones1 = new int[n * n];
        int[] ones2 = new int[n * n];
        int idx1 = 0, idx2 = 0;
        
        // grab all 1s from both matrices
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    // pack row and col into a single int using bit shifts
                    ones1[idx1++] = (i << 6) | j; 
                }
                if (img2[i][j] == 1) {
                    ones2[idx2++] = (i << 6) | j;
                }
            }
        }
        
        // edge case: if either has no 1s, overlap is impossible
        if (idx1 == 0 || idx2 == 0) return 0;
        
        // shift can go from -(n-1) to (n-1), so size needs to be 2n + 1
        int[][] count = new int[2 * n + 1][2 * n + 1];
        int maxOverlap = 0;
        
        // check every pairing to find the most common shift vector
        for (int i = 0; i < idx1; i++) {
            int p1 = ones1[i];
            int r1 = p1 >> 6;
            int c1 = p1 & 0x3F; // 0x3F is 63 in decimal, clears the row bits
            
            for (int j = 0; j < idx2; j++) {
                int p2 = ones2[j];
                int r2 = p2 >> 6;
                int c2 = p2 & 0x3F;
                
                // add n to avoid negative array index crashes
                int rowShift = r1 - r2 + n;
                int colShift = c1 - c2 + n;
                
                count[rowShift][colShift]++;
                if (count[rowShift][colShift] > maxOverlap) {
                    maxOverlap = count[rowShift][colShift];
                }
            }
        }
        
        return maxOverlap;
    }
}
