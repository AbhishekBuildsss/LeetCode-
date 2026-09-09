class Solution {
    public long countCommas(long n) {
        long ans = 0;

        // Numbers with at least 4 digits
        // have 1 comma for every complete group of 3 digits
        long power = 1000;

        while (power <= n) {
            ans += n - power + 1;
            power *= 1000;
        }

        return ans;
    }
}