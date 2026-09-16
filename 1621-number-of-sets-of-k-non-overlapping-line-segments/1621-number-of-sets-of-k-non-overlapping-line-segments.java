class Solution {
    public int numberOfSets(int n, int k) {
        final long MOD = 1000000007L;

        // dp[j] = number of ways to choose j segments
        // while processing the points seen so far.
        long[] dp = new long[k + 1];

        dp[0] = 1;

        /*
         * We use the combinatorial identity:
         *
         * answer = C(n + k - 1, 2 * k)
         *
         * So calculate the binomial coefficient directly.
         */

        int total = n + k - 1;
        int choose = 2 * k;

        if (choose > total) {
            return 0;
        }

        long[] fact = new long[total + 1];
        long[] invFact = new long[total + 1];

        fact[0] = 1;

        for (int i = 1; i <= total; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[total] = power(fact[total], MOD - 2, MOD);

        for (int i = total - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }

        long result = fact[total];

        result = result * invFact[choose] % MOD;
        result = result * invFact[total - choose] % MOD;

        return (int) result;
    }

    private long power(long a, long b, long mod) {
        long result = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = result * a % mod;
            }

            a = a * a % mod;
            b >>= 1;
        }

        return result;
    }
}