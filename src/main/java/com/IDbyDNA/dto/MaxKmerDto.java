package com.IDbyDNA.dto;

public class MaxKmerDto {
    public long totalKmers;
    public String maxKmer;
    public int maxKmerSize;

    public long getTotalKmers() {
        return totalKmers;
    }

    public void setTotalKmers(long totalKmers) {
        this.totalKmers = totalKmers;
    }

    public String getMaxKmer() {
        return maxKmer;
    }

    public void setMaxKmer(String maxKmer) {
        this.maxKmer = maxKmer;
    }

    public int getMaxKmerSize() {
        return maxKmerSize;
    }

    public void setMaxKmerSize(int maxKmerSize) {
        this.maxKmerSize = maxKmerSize;
    }
}
