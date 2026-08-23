class Solution {
    public boolean sumGame(String num) {
         int n = num.length();
        int half = n / 2;

        int left = 0;
        int right = 0;
        int qLeft = 0;
        int qRight = 0;

        for (int i = 0; i < half; i++) {
            if (num.charAt(i) == '?') {
                qLeft++;
            } else {
                left += num.charAt(i) - '0';
            }
        }

        for (int i = half; i < n; i++) {
            if (num.charAt(i) == '?') {
                qRight++;
            } else {
                right += num.charAt(i) - '0';
            }
        }

        int diff = left - right;

        if (qLeft == qRight) {
            return diff != 0;
        }

        if ((qLeft - qRight) % 2 != 0) {
            return true;
        }
        int q = Math.abs(qLeft - qRight) / 2;

        if (qLeft > qRight) {
            return diff + 9 * q != 0;
        } else {
            return diff - 9 * q != 0;
        }
    }
}