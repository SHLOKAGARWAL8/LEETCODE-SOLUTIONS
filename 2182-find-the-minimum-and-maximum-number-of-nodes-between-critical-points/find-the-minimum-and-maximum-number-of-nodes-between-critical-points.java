class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int[] result = {-1, -1};

        // Need at least 3 nodes for a critical point
        if (head == null || head.next == null || head.next.next == null) {
            return result;
        }

        ListNode prev = head;
        ListNode curr = head.next;

        int position = 1;

        int firstCritical = -1;
        int prevCritical = -1;

        int minDistance = Integer.MAX_VALUE;

        while (curr.next != null) {

            int prevVal = prev.val;
            int currVal = curr.val;
            int nextVal = curr.next.val;

            // Check if current node is a critical point
            boolean isCritical =
                    (currVal > prevVal && currVal > nextVal) ||
                    (currVal < prevVal && currVal < nextVal);

            if (isCritical) {

                // First critical point
                if (firstCritical == -1) {
                    firstCritical = position;
                }

                // Calculate distance from previous critical point
                if (prevCritical != -1) {
                    minDistance = Math.min(
                        minDistance,
                        position - prevCritical
                    );
                }

                prevCritical = position;
            }

            prev = curr;
            curr = curr.next;
            position++;
        }

        // Fewer than two critical points
        if (firstCritical == -1 || prevCritical == firstCritical) {
            return result;
        }

        int maxDistance = prevCritical - firstCritical;

        result[0] = minDistance;
        result[1] = maxDistance;

        return result;
    }
}