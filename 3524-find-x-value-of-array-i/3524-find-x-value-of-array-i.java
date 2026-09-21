class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] next = new long[k];

            int x = num % k;

            // Start a new subarray
            next[x]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                int newR = (r * x) % k;
                next[newR] += dp[r];
            }

            // Add current subarrays to answer
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans;
    }
}