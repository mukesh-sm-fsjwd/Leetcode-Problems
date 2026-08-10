class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n+1];
        // dp[0] = false
        // If there are no stones left then the current player cannot move then he/she loses.

        for(int i = 1;i <= n;i++){
            //Try removing every possible square number
            for(int j = 1;j * j <= i;j++){
                //If opponent player reaches losing state then current player wins.
                if(!dp[i - j*j]){
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}

