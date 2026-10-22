class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) zero = i;
        }

        boolean inc = true, dec = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (nums[0] + i) % n)
                inc = false;

            if (nums[i] != (nums[0] - i + n) % n)
                dec = false;
        }

        if (!inc && !dec) return -1;

        if (inc) return Math.min(zero, n - zero + 2);

        int k = (n - 1 - zero + n) % n;
        int other = (n - k) % n;

        return 1 + Math.min(
            Math.min(k, n - k + 2),
            Math.min(other, n - other + 2)
        );
    }
}