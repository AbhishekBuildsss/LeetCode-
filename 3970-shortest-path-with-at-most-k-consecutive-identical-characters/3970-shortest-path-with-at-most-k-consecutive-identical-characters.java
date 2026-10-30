import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            Comparator.comparingLong(a -> a[0])
        );

        HashMap<Long, Integer> dist = new HashMap<>();

        int first = labels.charAt(0) - 'a';
        long start = ((long) 0 * 26 + first) * k;

        dist.put(start, 0);
        pq.offer(new long[]{0, 0, first, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            int cost = (int) cur[0];
            int u = (int) cur[1];
            int last = (int) cur[2];
            int count = (int) cur[3];

            long key = ((long) u * 26 + last) * k + count - 1;

            if (dist.getOrDefault(key, Integer.MAX_VALUE) != cost) {
                continue;
            }

            if (u == n - 1) {
                return cost;
            }

            for (int[] e : graph[u]) {
                int v = e[0];
                int w = e[1];

                int ch = labels.charAt(v) - 'a';
                int newCount = (ch == last) ? count + 1 : 1;

                if (newCount > k) {
                    continue;
                }

                long newKey = ((long) v * 26 + ch) * k + newCount - 1;
                int newCost = cost + w;

                if (newCost < dist.getOrDefault(newKey, Integer.MAX_VALUE)) {
                    dist.put(newKey, newCost);
                    pq.offer(new long[]{newCost, v, ch, newCount});
                }
            }
        }

        return -1;
    }
}