class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        int max = 0;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
            sum += diff[i];
        }

        int k = k1 + k2;
        if (sum <= k) return 0;

        int left = 0, right = max;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long operations = 0;

            for (int d : diff) {
                operations += Math.max(0, d - mid);
            }

            if (operations <= k)
                right = mid;
            else
                left = mid + 1;
        }

        int level = left;
        long operations = 0;

        for (int d : diff) {
            operations += Math.max(0, d - level);
        }

        long remaining = k - operations;
        long result = 0;

        for (int d : diff) {
            d = Math.min(d, level);

            if (d == level && remaining > 0) {
                d--;
                remaining--;
            }

            result += (long) d * d;
        }

        return result;
    }
}