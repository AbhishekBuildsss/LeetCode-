import java.util.*;

class Solution {

    String s;
    int pos;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        pos = 0;

        Set<String> result = parseExpression();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    // expression = term { "," term }
    Set<String> parseExpression() {

        Set<String> result = parseTerm();

        while (pos < s.length() && s.charAt(pos) == ',') {
            pos++;

            result.addAll(parseTerm());
        }

        return result;
    }

    // term = factor factor factor...
    Set<String> parseTerm() {

        Set<String> result = new HashSet<>();
        result.add("");

        while (pos < s.length()
                && s.charAt(pos) != ','
                && s.charAt(pos) != '}') {

            Set<String> part;

            if (s.charAt(pos) == '{') {
                pos++;
                part = parseExpression();
                pos++; // skip '}'
            } else {
                part = new HashSet<>();
                part.add(String.valueOf(s.charAt(pos)));
                pos++;
            }

            result = combine(result, part);
        }

        return result;
    }

    Set<String> combine(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}