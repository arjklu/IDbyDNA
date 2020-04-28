package com.IDbyDNA.Service.Impl;

import com.IDbyDNA.Service.DataFormatter;
import com.IDbyDNA.dto.JsonDataFormatDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonFormatter implements DataFormatter {

    public String filePath;
    public Map<String, Integer> characterListMap;
    public List<String> errors;

    public JsonFormatter(String filePath) {
        this.filePath = filePath;
        this.characterListMap = new TreeMap<>();
        this.errors = new ArrayList<>();
    }

    @Override
    public void runAnalysis() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonDataFormatDto jsonDataFormat = mapper.readValue(new File(filePath),
                    JsonDataFormatDto.class);
            List<String> parts = jsonDataFormat.getParts();
            List<Integer> numbers = jsonDataFormat.getNumbers();
            int numberSize = numbers.size();
            for(int i=0; i<parts.size(); i++) {
                if(null == this.characterListMap.get(parts.get(i))) {
                    if(i >= numberSize) {
                        break;
                    }
                    this.characterListMap.put(parts.get(i), numbers.get(i));
                } else {
                    this.errors.add(", error found an " + parts.get(i));
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
