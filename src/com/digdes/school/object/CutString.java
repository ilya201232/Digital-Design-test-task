package com.digdes.school.object;

import java.util.Map;

public class CutString {
    private final String data;
    private final Map<Integer, Integer> cuts;

    private final int origLength;

    public CutString(String data, Map<Integer, Integer> cuts, int origLength) {
        this.data = data;
        this.cuts = cuts;
        this.origLength = origLength;
    }

    public int indexOfInOrigIndex(String pattern) {
        int index = data.indexOf(pattern);

        return indexToOrig(index);
    }

    public int indexOfInOrigIndex(String pattern, int startIndex) {
        int index = data.indexOf(pattern, indexToLocal(startIndex));

        return indexToOrig(index);
    }

    public int indexOfInOrigIndex(char pattern) {
        int index = data.indexOf(pattern);

        return indexToOrig(index);
    }

    public int lastIndexOfInOrigIndex(char pattern) {
        int index = data.lastIndexOf(pattern);

        return indexToOrig(index);
    }

    private int indexToOrig(int index) {
        if (index != -1) {
            int searchIndex = -1;
            for (int i = 0; i < origLength; i++) {
                if (!isInCuts(i)) {
                    searchIndex++;
                    if (searchIndex == index) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    private int indexToLocal(int index) {
        if (index != -1) {
            int searchIndex = -1;
            for (int i = 0; i < origLength; i++) {
                if (!isInCuts(i)) {
                    searchIndex++;
                    if (i == index) {
                        return searchIndex;
                    }
                }
            }
        }

        return -1;
    }

    private boolean isInCuts(int index) {
        for (Integer start : cuts.keySet()) {
            int end = cuts.get(start);

            if (index >= start && index <= end) {
                return true;
            }
        }

        return false;
    }

    public String getData() {
        return data;
    }
}
