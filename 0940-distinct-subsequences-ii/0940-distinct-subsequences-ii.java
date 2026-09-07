class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;

        // dp[i] = number of distinct subsequences
        // after processing characters up to i
        long[] dp = new long[s.length() + 1];

        // Empty subsequence
        dp[0] = 1;

        // Last contribution of each character
        long[] last = new long[26];

        for (int i = 1; i <= s.length(); i++) {
            int c = s.charAt(i - 1) - 'a';

            // Every existing subsequence can either:
            // 1. Not take current character
            // 2. Take current character
            dp[i] = (2 * dp[i - 1]) % MOD;

            // If this character appeared before,
            // its old subsequences would be duplicated.
            dp[i] = (dp[i] - last[c] + MOD) % MOD;

            // Current dp becomes the latest contribution
            last[c] = dp[i - 1];
        }

        // Remove empty subsequence
        return (int)((dp[s.length()] - 1 + MOD) % MOD);
    }
}