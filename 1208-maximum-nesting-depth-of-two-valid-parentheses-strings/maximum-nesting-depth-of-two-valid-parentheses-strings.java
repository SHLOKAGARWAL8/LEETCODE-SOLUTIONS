class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] answer = new int[n];

        int depth = 0;

        for (int i = 0; i < n; i++) {

            if (seq.charAt(i) == '(') {
                depth++;

                // Put alternate depths into different groups
                answer[i] = depth % 2;
            } else {
                // Use the current depth before decreasing it
                answer[i] = depth % 2;

                depth--;
            }
        }

        return answer;
    }
}