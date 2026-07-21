class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int active = 0;
        int previousZeroBlock = 0;
        int currentZeroBlock = 0;
        int maxGain = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                active++;

                if (currentZeroBlock > 0) {
                    previousZeroBlock = currentZeroBlock;
                    currentZeroBlock = 0;
                }
            } else {
                currentZeroBlock++;

                if (previousZeroBlock > 0) {
                    maxGain = Math.max(maxGain, previousZeroBlock + currentZeroBlock);
                }
            }
        }

        return active + maxGain;
    }
}