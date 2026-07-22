class Solution {
private static class Group {
        int start;
        int length;

        Group(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }

    private int[] tree;
    private int size;     
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
   

        int n = s.length();
        int totalOnes = 0;

        List<Group> zeroGroups = new ArrayList<>();
        int[] zeroGroupIndex = new int[n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            } else {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    zeroGroups.get(zeroGroups.size() - 1).length++;
                } else {
                    zeroGroups.add(new Group(i, 1));
                }
            }

            zeroGroupIndex[i] = zeroGroups.size() - 1;
        }

        List<Integer> answer = new ArrayList<>();

        if (zeroGroups.isEmpty()) {
            for (int i = 0; i < queries.length; i++) {
                answer.add(totalOnes);
            }
            return answer;
        }

        int groupCount = zeroGroups.size();
        int[] mergedLengths = new int[Math.max(0, groupCount - 1)];

        for (int i = 0; i + 1 < groupCount; i++) {
            mergedLengths[i] =
                    zeroGroups.get(i).length + zeroGroups.get(i + 1).length;
        }

        buildTree(mergedLengths);

        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];

            int leftGroup = zeroGroupIndex[l];
            int rightGroup = zeroGroupIndex[r];

            int leftLength = -1;
            int rightLength = -1;

            if (s.charAt(l) == '0') {
                Group group = zeroGroups.get(leftGroup);
                leftLength = group.length - (l - group.start);
            }

            if (s.charAt(r) == '0') {
                Group group = zeroGroups.get(rightGroup);
                rightLength = r - group.start + 1;
            }

            int best = totalOnes;

            if (s.charAt(l) == '0'
                    && s.charAt(r) == '0'
                    && leftGroup + 1 == rightGroup) {
                best = Math.max(best, totalOnes + leftLength + rightLength);
            }

            int firstCompleteGroup = leftGroup + 1;
            int lastCompleteGroup =
                    s.charAt(r) == '1' ? rightGroup : rightGroup - 1;

            int mergeLeft = firstCompleteGroup;
            int mergeRight = lastCompleteGroup - 1;

            if (mergeLeft <= mergeRight) {
                best = Math.max(
                        best,
                        totalOnes + rangeMax(mergeLeft, mergeRight)
                );
            }

            if (s.charAt(l) == '0'
                    && leftGroup + 1 <= lastCompleteGroup) {
                best = Math.max(
                        best,
                        totalOnes
                                + leftLength
                                + zeroGroups.get(leftGroup + 1).length
                );
            }

            if (s.charAt(r) == '0'
                    && leftGroup < rightGroup - 1) {
                best = Math.max(
                        best,
                        totalOnes
                                + rightLength
                                + zeroGroups.get(rightGroup - 1).length
                );
            }

            answer.add(best);
        }

        return answer;
    }

    private void buildTree(int[] values) {
        size = 1;

        while (size < values.length) {
            size <<= 1;
        }

        tree = new int[size * 2];

        for (int i = 0; i < values.length; i++) {
            tree[size + i] = values[i];
        }

        for (int i = size - 1; i >= 1; i--) {
            tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
        }
    }

    private int rangeMax(int left, int right) {
        if (left > right) {
            return 0;
        }

        left += size;
        right += size;

        int result = 0;

        while (left <= right) {
            if ((left & 1) == 1) {
                result = Math.max(result, tree[left++]);
            }

            if ((right & 1) == 0) {
                result = Math.max(result, tree[right--]);
            }

            left >>= 1;
            right >>= 1;
        }

        return result;
    }
}