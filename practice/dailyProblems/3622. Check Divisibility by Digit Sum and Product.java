class Solution {
    public boolean checkDivisibility(int n) {
        int sumDigits = 0;
        int productDigits = 1;
        int original = n;

        while(n > 0){
            sumDigits += n % 10;
            productDigits *= n % 10;
            n /= 10;
        }

        return original % (sumDigits + productDigits) == 0;
    }
}
