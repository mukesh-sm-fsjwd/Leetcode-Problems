class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Let's add everything up from left to right because 
        // when we take stones, we always get the sum of all elements so far.
        // Doing it in-place to save memory!
        for (int i = 1; i < n; i++) {
            stones[i] = stones[i] + stones[i - 1];
        }
        
        // If the game ends and we have to take all stones, 
        // this is our starting best score choice (the total sum).
        int res = stones[n - 1];
        
        // Now we loop backwards from the second to last item.
        // We stop at 1 because the rules say Alice has to take at least 2 stones on turn 1.
        for (int i = n - 2; i >= 1; i--) {
            // At this index, do we keep our old best score, 
            // or do we pick this new spot and subtract whatever the opponent can get?
            res = Math.max(res, stones[i] - res);
        }
        
        // This holds the final answer for Alice's best strategy
        return res;
    }
}
