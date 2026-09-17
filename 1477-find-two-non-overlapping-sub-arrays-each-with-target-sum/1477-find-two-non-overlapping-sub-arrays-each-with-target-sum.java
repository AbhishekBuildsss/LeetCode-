class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length, ans = Integer.MAX_VALUE;
        int[] dp = new int[n];
        int left = 0, sum = 0, best = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target)
                sum -= arr[left++];

            if (sum == target) {
                int len = right - left + 1;

                if (left > 0 && dp[left - 1] != 0)
                    ans = Math.min(ans, len + dp[left - 1]);

                best = Math.min(best, len);
            }

            dp[right] = best == Integer.MAX_VALUE ? 0 : best;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}