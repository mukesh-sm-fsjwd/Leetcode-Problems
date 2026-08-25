class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();

        for(int n : nums){
            set.add(n);
        }

        int result = 0;

        // for(int i = k ; i <= 100;i += k){
        //     if(!set.contains(i)){
        //         result = i;
        //         break;
        //     }
        // }

        int i = k;
        while(true){
            if(!set.contains(i)){
                result = i;
                break;
            }
            i+=k;
        }

        return result;
    }
}
