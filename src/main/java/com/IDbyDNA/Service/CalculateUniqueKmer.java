package com.IDbyDNA.Service;

import java.util.Map;

public class CalculateUniqueKmer {

    public void calculateUniqueKMers(StringBuilder input, Map<String, Integer> map, int size) {
        try {
            for (int i = 0; i < input.length() - size; i++) {
                String subString = input.substring(i, size + i);
                if (isStringMatchesIDbyDnaPattern(subString)) {
                    if (null == map || null == map.get(subString)) {
                        map.put(subString, 1);
                    } else {
                        map.put(subString, map.get(subString) + 1);
                    }
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println(input);
        }
    }

    public boolean isStringMatchesIDbyDnaPattern(String word) {
        for(int i=0; i<word.length(); i++) {
            if(! (word.charAt(i) == 'A' || word.charAt(i) == 'T' || word.charAt(i) == 'C' || word.charAt(i) == 'G')) {
                return false;
            }
        }
        return true;
    }
}
