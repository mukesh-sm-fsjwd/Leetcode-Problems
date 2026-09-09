class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long currentTier = 1000; // The first number that gets a comma

        // Loop as long as our tier is smaller than or equal to n
        while (currentTier <= n) {
            // Every number from 'currentTier' up to 'n' gets exactly 1 comma from this tier
            long numbersInThisTier = n - currentTier + 1;
            
            // Add them to our running total
            totalCommas += numbersInThisTier;
            
            // Move to the next comma tier (e.g., 1,000 -> 1,000,000)
            currentTier *= 1000;
        }

        return totalCommas;
    }
}
