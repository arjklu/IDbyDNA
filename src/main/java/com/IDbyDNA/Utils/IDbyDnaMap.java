package com.IDbyDNA.Utils;

import java.util.*;

/*
Customized HashTable to store all IDbyDNA data.
 */
public class IDbyDnaMap<K, V>
{
    private ArrayList<HashNode<K, V>> bucketArray;          // This bucketArray is used to store array of chains
    private int numBuckets;                                 // capacity of array list
    private int size;                                       // size of array list

    public IDbyDnaMap()                                     // Constructor (initializes capacity, size and empty chains)
    {
        bucketArray = new ArrayList<HashNode<K, V>>();
        numBuckets = 30;
        size = 0;
        for (int i = 0; i < numBuckets; i++)
            bucketArray.add(null);
    }

    public int size() { return size; }
    public boolean isEmpty() { return size() == 0; }

    private int getBucketIndex(K key)                       // generates hash-function to find index for a key
    {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return Math.abs(index);
    }

    /**
     * This method removes the data by a given key
     * @param key
     * @return an Object
     */
    public V remove(K key)
    {
        int bucketIndex = getBucketIndex(key);              // gets the hash function to find index for given key
        HashNode<K, V> head = bucketArray.get(bucketIndex);
        HashNode<K, V> prev = null;
        while (head != null)
        {
            if (head.key.equals(key))
                break;
            prev = head;
            head = head.next;
        }

        if (head == null)
            return null;

        size--;

        if (prev != null)
            prev.next = head.next;
        else
            bucketArray.set(bucketIndex, head.next);

        return head.value;
    }

    /**
     * This method is used to retrieve the value by passing in the key.
     * @param key
     * @return an Object
     */
    public V get(K key)
    {
        int bucketIndex = getBucketIndex(key);                  // generates hash-function to find index for a key
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        while (head != null)                                    // search for the key in chain
        {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }

        return null;                                            // if key not found
    }

    /**
     * This method is used for storing the key and its corresponding vales into our hashtable.
     * @param key
     * @param value
     */
    public void put(K key, V value)
    {
        int bucketIndex = getBucketIndex(key);                  // generates hash-function to find index for a key
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        while (head != null)
        {
            if (head.key.equals(key))
            {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);

        if ((1.0*size)/numBuckets >= 0.7)                           // If load factor goes beyond threshold, then
        {                                                           // double hash table size
            ArrayList<HashNode<K, V>> temp = bucketArray;
            bucketArray = new ArrayList<HashNode<K, V>>();
            numBuckets = 2 * numBuckets;
            size = 0;
            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);

            for (HashNode<K, V> headNode : temp)
            {
                while (headNode != null)
                {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
}
