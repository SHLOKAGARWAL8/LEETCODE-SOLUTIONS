class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> answer = new ArrayList<>();
        String digits = "123456789";

        for (int length = 2; length <= 9; length++) {
            for (int start = 0; start + length <= 9; start++) {
                int number = Integer.parseInt(digits.substring(start, start + length));

                if (number >= low && number <= high) {
                    answer.add(number);
                }
            }
        }

        Collections.sort(answer);
        return answer;
    }
}