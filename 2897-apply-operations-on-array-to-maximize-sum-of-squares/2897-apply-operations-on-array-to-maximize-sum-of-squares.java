
class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int mod = 1_000_000_007;
        int[] bits = new int[30];

        for (int num : nums) {
            for (int b = 0; b < 30; b++) {
                if ((num & (1 << b)) != 0) {
                    bits[b]++;
                }
            }
        }

        long ans = 0;

        for (int i = 0; i < k; i++) {
            long num = 0;

            for (int b = 0; b < 30; b++) {
                if (bits[b] > 0) {
                    num |= (1L << b);
                    bits[b]--;
                }
            }

            ans = (ans + num * num) % mod;
        }

        return (int) ans;
    }
}
