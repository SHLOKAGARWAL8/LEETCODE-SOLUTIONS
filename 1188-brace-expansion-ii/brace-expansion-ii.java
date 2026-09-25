import java.util.*;

class Solution {
    int index = 0;
    String s;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        index = 0;

        Set<String> result = parseExpression();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parseExpression() {
        Set<String> result = new HashSet<>();

        while (index < s.length() && s.charAt(index) != '}') {
            Set<String> term = parseTerm();
            result.addAll(term);

            if (index < s.length() && s.charAt(index) == ',') {
                index++;
            }
        }

        return result;
    }

    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != ','
                && s.charAt(index) != '}') {

            Set<String> current;

            if (s.charAt(index) == '{') {
                index++;
                current = parseExpression();
                index++; // Skip closing brace
            } else {
                current = new HashSet<>();
                current.add(String.valueOf(s.charAt(index)));
                index++;
            }

            Set<String> combined = new HashSet<>();

            for (String a : result) {
                for (String b : current) {
                    combined.add(a + b);
                }
            }

            result = combined;
        }

        return result;
    }
}