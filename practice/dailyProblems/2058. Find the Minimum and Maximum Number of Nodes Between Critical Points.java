class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int first = -1;
        int previous = -1;
        int min = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        while (curr.next != null) {

            ListNode next = curr.next;

            // Is curr a critical point?
            if ((curr.val > prev.val && curr.val > next.val) ||
                (curr.val < prev.val && curr.val < next.val)) {

                // We already found a critical point
                if (previous != -1) {
                    min = Math.min(min, index - previous);
                }

                // Save the first critical point
                if (first == -1) {
                    first = index;
                }

                // Current becomes previous critical point
                previous = index;
            }

            prev = curr;
            curr = next;
            index++;
        }

        // Fewer than 2 critical points
        if (first == previous) {
            return new int[]{-1, -1};
        }

        return new int[]{min, previous - first};
    }
}
