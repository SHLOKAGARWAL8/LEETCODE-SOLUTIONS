
import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        // Available servers: {weight, index}
        PriorityQueue<int[]> free = new PriorityQueue<>(
            (a, b) -> a[0] != b[0]
                ? Integer.compare(a[0], b[0])
                : Integer.compare(a[1], b[1])
        );

        // Busy servers: {finishTime, weight, index}
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        int n = servers.length;
        int m = tasks.length;

        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        int[] ans = new int[m];
        long time = 0;

        for (int i = 0; i < m; i++) {

            time = Math.max(time, i);

            // Release all completed servers
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] s = busy.poll();
                free.offer(new int[]{(int) s[1], (int) s[2]});
            }

            // If no server is free, advance time
            if (free.isEmpty()) {
                time = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] == time) {
                    long[] s = busy.poll();
                    free.offer(new int[]{(int) s[1], (int) s[2]});
                }
            }

            // Select server with minimum weight, then index
            int[] server = free.poll();

            ans[i] = server[1];

            // Assign task and record completion time
            busy.offer(new long[]{
                time + tasks[i],
                server[0],
                server[1]
            });
        }

        return ans;
    }
}


