
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        boolean sorted = true;
        char min = 'z';
        char max = 'a';
        int minCount = 0;
        int maxCount = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (i > 0 && c < s.charAt(i - 1)) {
                sorted = false;
            }

            if (c < min) {
                min = c;
                minCount = 1;
            } else if (c == min) {
                minCount++;
            }

            if (c > max) {
                max = c;
                maxCount = 1;
            } else if (c == max) {
                maxCount++;
            }
        }

        if (sorted) return 0;

        if (n == 2) return -1;

        if (s.charAt(0) == min ||
            s.charAt(n - 1) == max) {
            return 1;
        }

        if (s.charAt(0) == max &&
            s.charAt(n - 1) == min &&
            minCount == 1 &&
            maxCount == 1) {
            return 3;
        }

        return 2;
    }
}
