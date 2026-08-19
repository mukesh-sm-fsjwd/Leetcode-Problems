class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Masks for seat blocks
        int LEFT_MASK = 0b0000011110;    // seats 2,3,4,5
        int MIDDLE_MASK = 0b0001111000;  // seats 4,5,6,7
        int RIGHT_MASK = 0b0111100000;   // seats 6,7,8,9
        Map<Integer, Integer> reserved = new HashMap<>();
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int seatNum = seat[1];
            reserved.put( row,reserved.getOrDefault(row, 0) | (1 << (seatNum - 1))  );
        }
        int result = (n - reserved.size()) * 2;
        for (int mask : reserved.values()) {
            boolean canLeft = (mask & LEFT_MASK) == 0;
            boolean canMiddle = (mask & MIDDLE_MASK) == 0;
            boolean canRight = (mask & RIGHT_MASK) == 0;
            if (canLeft && canRight) {
                result += 2;
            } else if (canLeft || canMiddle || canRight) {
                result += 1;
            }
        }
        return result;
    }
}

