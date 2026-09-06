class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        // dp[j] = number of ways to form t[0...j-1]
        // using characters processed from s
        long[] dp = new long[m + 1];

        // Empty string can always be formed in 1 way
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {

            // Go backwards so that dp[j-1]
            // still represents the previous row.
            for (int j = m; j >= 1; j--) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }

        return (int) dp[m];
    }
}