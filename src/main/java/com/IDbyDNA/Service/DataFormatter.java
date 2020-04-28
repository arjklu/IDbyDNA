package com.IDbyDNA.Service;

public interface DataFormatter {

    /**
     * This method is responsible for reading the text/JSON file,
     * analyze the content and group it by valid and invalid
     * character sequence.
     */
    void runAnalysis();

    /**
     * This method iterates through the HashMap, and prints
     * the result in console.
     */
    void printAnalysis();
}


