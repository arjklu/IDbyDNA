package com.IDbyDNA.Service.Impl;

import com.IDbyDNA.Service.CalculateKmerSequence;
import com.IDbyDNA.Service.CalculateUniqueKmer;
import com.IDbyDNA.Utils.IDbyDnaMap;
import com.IDbyDNA.dto.MaxKmerDto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalculateKmerSequenceImpl implements CalculateKmerSequence {

    public String filePath;
    public MaxKmerDto maxKmerDto;
    public IDbyDnaMap<String, Integer> map;

    public CalculateKmerSequenceImpl(String filePath) {
        this.filePath = filePath;
        this.map = new IDbyDnaMap<>();
        this.maxKmerDto = new MaxKmerDto();
    }

    @Override
    public long printUniqueKmer() {
        calculateKmers();
        System.out.println("Total distinct k-mers: " + map.size());
        return map.size();
    }

    @Override
    public long printTotalKmer() {
        System.out.println("Total number of k-mers: " + this.maxKmerDto.getTotalKmers());
        return this.maxKmerDto.getTotalKmers();
    }

    @Override
    public String printKmerWithHighestCount() {
        System.out.println("k-mer with hight count: " + this.maxKmerDto.getMaxKmer()
                + ", with count of: " + this.maxKmerDto.getMaxKmerSize());
        return this.maxKmerDto.getMaxKmer();
    }

    private void calculateKmers() {
        File file = new File(filePath);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String fileLine;
            int totalKmer = 0;
            StringBuilder consolidatedStrings = new StringBuilder();
            List<StringBuilder> consolidatedStringList = new ArrayList<StringBuilder>();
            while ((fileLine = br.readLine()) != null) {
                if(fileLine.contains(">")) {
                    consolidatedStrings = new StringBuilder();
                } else if(fileLine.matches("^[a-zA-Z]*$")) {
                    consolidatedStrings.append(fileLine);
                }
                if(consolidatedStrings.length() == 251){
                    consolidatedStringList.add(consolidatedStrings);
                }
            }

            for(int i=0; i<consolidatedStringList.size(); i++) {
                this.maxKmerDto = new CalculateUniqueKmer().calculateUniqueKMers(consolidatedStringList.get(i), this.map, 25);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
