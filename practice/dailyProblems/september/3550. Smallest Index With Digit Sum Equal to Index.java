class Solution {
    public int smallestIndex(int[] nums) {
        
        int n = nums.length;

        for(int i = 0;i < n;i++){
            int duplicate = nums[i];
            int currentSum = 0;
            while(duplicate > 0){
                int lastDigit = duplicate % 10;
                currentSum += lastDigit;
                duplicate /= 10;
            }
            if(currentSum == i){
                return i;
            }
        }
        
        return -1;
    }
}
