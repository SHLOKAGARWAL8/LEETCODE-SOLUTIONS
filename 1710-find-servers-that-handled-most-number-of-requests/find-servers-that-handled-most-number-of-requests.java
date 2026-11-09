
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        // {finishTime, serverIndex}
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        int[] count = new int[k];
        int max = 0;

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        for (int i = 0; i < arrival.length; i++) {

            // Release completed servers
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= arrival[i]) {

                long[] curr = busy.poll();
                available.add((int) curr[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            int preferred = i % k;

            // Find next available server with wraparound
            Integer server = available.ceiling(preferred);

            if (server == null) {
                server = available.first();
            }

            available.remove(server);

            count[server]++;
            max = Math.max(max, count[server]);

            busy.offer(new long[]{
                (long) arrival[i] + load[i],
                server
            });
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}
