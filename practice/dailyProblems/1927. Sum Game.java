class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        
        // store the sum of left and right side
        int leftSum = 0, rightSum = 0;
        
        // count how many ? are there on both sides
        int leftQuestion = 0, rightQuestion = 0;

        // checking the first half of the string
        for (int i = 0; i < n / 2; i++) {
            
            // if it is ?, count it
            if (num.charAt(i) == '?') {
                leftQuestion++;
            } else {
                // otherwise add the number to left sum
                leftSum += num.charAt(i) - '0';
            }
        }

        // checking the second half of the string
        for (int i = n / 2; i < n; i++) {
            
            // if it is ?, count it
            if (num.charAt(i) == '?') {
                rightQuestion++;
            } else {
                // otherwise add the number to right sum
                rightSum += num.charAt(i) - '0';
            }
        }

        // if total ? is odd, Alice will always win
        // otherwise check whether both sides can become equal
        return (leftQuestion + rightQuestion) % 2 != 0 || 
        leftSum - rightSum != 9 * (rightQuestion - leftQuestion) / 2;
    }
}
