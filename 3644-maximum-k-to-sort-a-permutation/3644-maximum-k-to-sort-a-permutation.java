class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int k = -1;
        boolean sorted = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                sorted = false;
                k &= nums[i];
                k &= i;
            }
        }

        return sorted ? 0 : k;
    }
}
