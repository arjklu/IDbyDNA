package com.IDbyDNA.Service.Impl;

import com.IDbyDNA.Service.DataFormatter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TextFormatter implements DataFormatter {

    public String filePath;
    public Map<String, Integer> characterListMap;
    public List<String> errors;

    public TextFormatter(String filePath) {
        this.filePath = filePath;
        this.characterListMap = new TreeMap<>();
        this.errors = new ArrayList<>();
    }

    @Override
    public void runAnalysis() {
        try {
            String data = new String(Files.readAllBytes(Paths.get(filePath)));

            String sbKey = "", sbValue = "";
            Map<String, Integer> characterMap = new HashMap<>();

            for(int i=0; i<data.length(); i++) {
                if(Character.isDigit(data.charAt(i))) {
                    sbValue = sbValue + (data.charAt(i) - '0');
                } else {
                    if(sbKey.length()>0 && sbValue.length()>0) {
                        if (null == characterMap.get(sbKey)) {
                            this.characterListMap.put(sbKey, Integer.parseInt(sbValue));
                            characterMap.put(sbKey, Integer.parseInt(sbValue));
                            sbKey = "";
                        } else {
                            this.errors.add(", error found an " + sbKey);
                        }
                    }
                    sbKey += data.charAt(i);
                    sbValue = "";
                }

            }
            if(sbKey.length()>0 && sbValue.length()>0) {
                if (null == characterMap.get(sbKey)) {
                    this.characterListMap.put(sbKey, Integer.parseInt(sbValue));
                } else {
                    this.errors.add(", error found an " + sbKey);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printAnalysis() {
        this.characterListMap.forEach((k, v) -> System.out.print(k + ":" + v + " "));
        errors.forEach(item -> {
            System.out.print(item);
        });
    }
}
