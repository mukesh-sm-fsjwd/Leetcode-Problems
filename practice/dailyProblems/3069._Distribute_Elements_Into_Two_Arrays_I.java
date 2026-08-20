class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        
        // Use lists to dynamically build the two distributed arrays
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        
        // Rule 1 & 2: First element goes to arr1, second goes to arr2
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        
        // Rule 3: Distribute remaining elements based on the last elements
        for (int i = 2; i < n; i++) {
            int lastArr1 = arr1.get(arr1.size() - 1);
            int lastArr2 = arr2.get(arr2.size() - 1);
            
            if (lastArr1 > lastArr2) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }
        
        // Combine both lists into the final result array
        int[] result = new int[n];
        int index = 0;
        
        for (int num : arr1) {
            result[index++] = num;
        }
        for (int num : arr2) {
            result[index++] = num;
        }
        
        return result;
    }
}

