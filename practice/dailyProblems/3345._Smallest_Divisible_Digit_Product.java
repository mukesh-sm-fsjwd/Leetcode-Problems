class Solution {
    public int smallestNumber(int n, int t) {
        // //if the number n is not divisible by t , incrementing n.
        // if(productOfDigits(n) % t != 0){
        //     n++;
        // }
        // return n;
        while(true){
            if(productOfDigits(n) % t == 0){
                return n;
            }
            n++;
        }
    }

    private int productOfDigits(int n){
        int product = 1;

        while(n > 0){
            product *= n % 10;
            n /= 10;
        }

        return product;
    }
}
