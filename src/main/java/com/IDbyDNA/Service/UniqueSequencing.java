package com.IDbyDNA.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueSequencing {

    String filePath;

    public UniqueSequencing(String filePath) {
        this.filePath = filePath;
    }

    public static void main(String[] args) {
        File file = new File("src/main/resources/SRR1748776.fa");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String fileLine;
            StringBuilder consolidatedStrings = new StringBuilder();
            List<StringBuilder> consolidatedStringList = new ArrayList<>();
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

            //IDbyDnaMap<String, Integer> map = new IDbyDnaMap<>();
            //CalculateUniqueKmer calculateUniqueKmer = new CalculateUniqueKmer();
            Map<String, Integer> map = new HashMap<>();
            for(int i=0; i<consolidatedStringList.size(); i++) {
                //System.out.println(i);
                new CalculateUniqueKmer().calculateUniqueKMers(consolidatedStringList.get(i), map, 25);
            }

            int distinctKmers = map.size();
            int totalKmer = map.values().stream().reduce(0, Integer::sum);

            System.out.println("Total distinct k-mers: " + distinctKmers);
            System.out.println("Total number of k-mers: " + totalKmer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
