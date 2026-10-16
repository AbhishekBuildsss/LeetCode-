
class Solution {
    static final long MOD = 1_000_000_007L;
    long[] sum, sq, lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int l = last[nums[i]];
            update(1, 0, n - 1, l, i, 1);
            last[nums[i]] = i + 1;
            ans = (ans + sq[1]) % MOD;
        }

        return (int) ans;
    }

    void apply(int p, int l, int r, long v) {
        long len = r - l + 1L;
        sq[p] = (sq[p] + 2 * v % MOD * sum[p] % MOD
                + v * v % MOD * len % MOD) % MOD;
        sum[p] = (sum[p] + v * len) % MOD;
        lazy[p] = (lazy[p] + v) % MOD;
    }

    void push(int p, int l, int r) {
        if (lazy[p] == 0 || l == r) return;
        int m = (l + r) / 2;
        apply(p * 2, l, m, lazy[p]);
        apply(p * 2 + 1, m + 1, r, lazy[p]);
        lazy[p] = 0;
    }

    void update(int p, int l, int r, int ql, int qr, long v) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            apply(p, l, r, v);
            return;
        }

        push(p, l, r);
        int m = (l + r) / 2;
        update(p * 2, l, m, ql, qr, v);
        update(p * 2 + 1, m + 1, r, ql, qr, v);

        sum[p] = (sum[p * 2] + sum[p * 2 + 1]) % MOD;
        sq[p] = (sq[p * 2] + sq[p * 2 + 1]) % MOD;
    }
}
