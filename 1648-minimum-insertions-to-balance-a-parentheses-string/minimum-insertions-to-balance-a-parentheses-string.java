class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                // If the next character is ')', consume it as a pair.
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    // Insert one ')' to complete the required pair.
                    insertions++;
                }

                // The closing pair must match an opening '('.
                if (open > 0) {
                    open--;
                } else {
                    // Insert '(' because this closing pair has no opener.
                    insertions++;
                }
            }
        }

        return insertions + 2 * open;
    }
}
