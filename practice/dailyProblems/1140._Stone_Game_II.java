class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        int[][] dp = new int[n][n+1];
        for(int i = 0;i < n;i++) Arrays.fill(dp[i],-1);

        // Passing piles array , index (idx) , M (initially 1) and 2d dp array for memoization.
        return solve(piles,0,1,dp);
    }

    private int solve(int[] piles,int idx,int M,int[][] dp){
        if(idx >= piles.length) return 0;

        if(dp[idx][M] != -1) return dp[idx][M];

        //Remaining Stones
        int total = 0;
        for(int i = idx;i < piles.length;i++){
            total += piles[i];
        }

        int answer = 0;

        //Choices
        for(int X = 1;X <= 2*M;X++){
            if(idx + X > piles.length) break;

            int newM = Math.max(M,X);

            int opponent = solve(piles,idx+X,newM,dp);

            answer = Math.max(answer,total - opponent);
        }

        return dp[idx][M] = answer;
    }
}

