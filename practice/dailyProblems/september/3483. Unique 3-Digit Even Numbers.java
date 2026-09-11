class Solution {
    public int totalNumbers(int[] digits) {
        // Step 1: Count the frequency of each digit in the input array
        int[] availableCounts = new int[10];
        for (int digit : digits) {
            availableCounts[digit]++;
        }
        
        int matchCount = 0;
        
        // Step 2: Enumerate all possible 3-digit even numbers from 100 to 998
        for (int num = 100; num <= 998; num += 2) {
            int hundreds = num / 100;
            int tens = (num / 10) % 10;
            int units = num % 10;
            
            // Step 3: Verify if 'num' can be formed with the available digits
            availableCounts[hundreds]--;
            availableCounts[tens]--;
            availableCounts[units]--;
            
            if (availableCounts[hundreds] >= 0 && availableCounts[tens] >= 0 && availableCounts[units] >= 0) {
                matchCount++;
            }
            
            // Step 4: Backtrack / Restore counts for the next iteration
            availableCounts[hundreds]++;
            availableCounts[tens]++;
            availableCounts[units]++;
        }
        
        return matchCount;
    }
}
