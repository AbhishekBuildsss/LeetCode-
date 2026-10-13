
import java.util.*;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] arr = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            arr[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(arr);

        long ans = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i < (n + 1) / 2)
                ans += arr[n - 1 - i];
            else
                ans -= arr[n - 1 - i];
        }

        return ans;
    }
}
