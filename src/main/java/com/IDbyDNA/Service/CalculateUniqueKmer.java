package com.IDbyDNA.Service;

import com.IDbyDNA.Utils.IDbyDnaMap;
import com.IDbyDNA.dto.MaxKmerDto;

public class CalculateUniqueKmer {

    static long totalKmers = 0;
    static String maxKmer;
    static int maxKmerSize = 0;

    public MaxKmerDto calculateUniqueKMers(StringBuilder input, IDbyDnaMap<String, Integer> map, int size) {
        MaxKmerDto maxKmerDto = new MaxKmerDto();
        try {
            for (int i = 0; i < input.length() - size; i++) {
                String subString = input.substring(i, size + i);
                if (isStringMatchesIDbyDnaPattern(subString)) {
                    if (null == map || null == map.get(subString)) {
                        totalKmers++;
                        calculateMaxKmer(subString, 1);
                        map.put(subString, 1);
                    } else {
                        int currentKmerCount = map.get(subString) + 1;
                        totalKmers += currentKmerCount;
                        calculateMaxKmer(subString, currentKmerCount);
                        map.put(subString, currentKmerCount);
                    }
                }
            }
            maxKmerDto.setMaxKmer(maxKmer);
            maxKmerDto.setMaxKmerSize(maxKmerSize);
            maxKmerDto.setTotalKmers(totalKmers);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return maxKmerDto;
    }

    private void calculateMaxKmer(String subString, int currentKmerCount) {
        if(currentKmerCount > maxKmerSize) {
            maxKmerSize = currentKmerCount;
            maxKmer = subString;
        }
    }

    private boolean isStringMatchesIDbyDnaPattern(String word) {
        for(int i=0; i<word.length(); i++) {
            if(! (word.charAt(i) == 'A' || word.charAt(i) == 'T' || word.charAt(i) == 'C' || word.charAt(i) == 'G')) {
                return false;
            }
        }
        return true;
    }
}
