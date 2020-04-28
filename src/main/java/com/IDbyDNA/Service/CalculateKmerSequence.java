package com.IDbyDNA.Service;

public interface CalculateKmerSequence {

    /**
     * This method prints all uniqueKmers in the custom HashMap.
     * @return unique kmer value.
     */
    long printUniqueKmer();

    /**
     * This method prints total number of kmers in the custom HashMap.
     * @return total number of kmer value.
     */
    long printTotalKmer();

    /**
     * This method prints the k-mer with highest count.
     * @return string with max value.
     */
    String printKmerWithHighestCount();
}
