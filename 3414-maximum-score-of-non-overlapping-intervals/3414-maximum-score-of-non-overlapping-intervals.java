import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, index;

        Interval(int l, int r, int w, int index) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.index = index;
        }
    }

    static class State {
        long score;
        int[] indices;

        State(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        // Sort by right endpoint
        Arrays.sort(arr, (a, b) -> {
            if (a.r != b.r) {
                return Integer.compare(a.r, b.r);
            }
            return Integer.compare(a.index, b.index);
        });

        // rightEnds[i] = right endpoint of interval i
        int[] rightEnds = new int[n];

        for (int i = 0; i < n; i++) {
            rightEnds[i] = arr[i].r;
        }

        // prev[i] = number of intervals ending strictly
        // before arr[i] starts
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            int left = arr[i].l;

            int lo = 0;
            int hi = i - 1;
            int pos = -1;

            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;

                if (rightEnds[mid] < left) {
                    pos = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            prev[i] = pos + 1;
        }

        /*
         * dp[k] represents the best answer using
         * at most k intervals among the processed intervals.
         */
        State[] dp = new State[5];

        for (int k = 0; k <= 4; k++) {
            dp[k] = new State(0, new int[0]);
        }

        for (int i = 0; i < n; i++) {

            // Update backwards so that the current interval
            // is used only once.
            for (int k = 4; k >= 1; k--) {

                State before = dp[k - 1];

                // We need the best solution among intervals
                // before 'prev[i]'.
                //
                // Since dp is maintained globally, we need a
                // separate DP table for exact positions.
            }
        }

        /*
         * Use full DP table.
         * dp[k][i] = best answer using first i intervals
         * and at most k intervals.
         */
        State[][] table = new State[5][n + 1];

        for (int k = 0; k <= 4; k++) {
            table[k][0] = new State(0, new int[0]);
        }

        for (int i = 1; i <= n; i++) {

            int idx = i - 1;

            for (int k = 0; k <= 4; k++) {

                // Don't take current interval
                table[k][i] = table[k][i - 1];

                if (k == 0) {
                    continue;
                }

                // Take current interval
                int p = prev[idx];

                State previousState = table[k - 1][p];

                int[] newIndices =
                    Arrays.copyOf(
                        previousState.indices,
                        previousState.indices.length + 1
                    );

                newIndices[newIndices.length - 1] = arr[idx].index;

                // Sort at most 4 elements
                Arrays.sort(newIndices);

                State take = new State(
                    previousState.score + arr[idx].w,
                    newIndices
                );

                if (better(take, table[k][i])) {
                    table[k][i] = take;
                }
            }
        }

        return table[4][n].indices;
    }

    private boolean better(State a, State b) {

        // Higher score is better
        if (a.score != b.score) {
            return a.score > b.score;
        }

        // Same score -> lexicographically smaller indices
        int len = Math.min(a.indices.length, b.indices.length);

        for (int i = 0; i < len; i++) {
            if (a.indices[i] != b.indices[i]) {
                return a.indices[i] < b.indices[i];
            }
        }

        return a.indices.length < b.indices.length;
    }
}