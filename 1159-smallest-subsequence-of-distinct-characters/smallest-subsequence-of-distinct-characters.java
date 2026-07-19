class Solution {
    public String smallestSubsequence(String s) {
        int[] last = new int[26];
        boolean[] used = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        StringBuilder stack = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int idx = c - 'a';

            if (used[idx]) continue;

            while (stack.length() > 0) {
                char top = stack.charAt(stack.length() - 1);

                if (top > c && last[top - 'a'] > i) {
                    used[top - 'a'] = false;
                    stack.deleteCharAt(stack.length() - 1);
                } else {
                    break;
                }
            }

            stack.append(c);
            used[idx] = true;
        }

        return stack.toString();
    
    }
}