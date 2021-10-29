//Assignment5.java
/*
 * THIS PROGRAM DEPENDS ON:
 * 
 * -SeparateChaininghash.java
 * -SequentialSearchST.java
 * -StdIn.java
 * -StdOut.java
 * ----------------------------------
 * @author Jonathan Gustafson
 * 
 * Parts of the code is taken from: https://algs4.cs.princeton.edu/code/
 * 
 * This program reads a given amount of words from a text file,
 * then is saves all words in a separate chaingin hash table, counts 
 * the words.
 * Then the program prints the distribution of hascodes for the hash
 * table.
 * 
 * */


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import edu.*;

public class Assignment5 {
	
	public static void main(String[] args) throws FileNotFoundException {
        int distinct = 0, words = 0;
        SeparateChainingHashST<String, Integer> hash = new SeparateChainingHashST<String, Integer>();
        
        FileReader fr = new FileReader("C:\\Users\\jonth\\Desktop\\Workspace\\AlgoData_Lab3\\labText.txt");  
        Scanner sc = new Scanner(fr);
        
        // compute frequency counts
        while (words < 100) {
            String key = sc.next();
            words++;
            if (hash.contains(key)) {
                hash.put(key, hash.get(key) + 1);
            }
            else {
                hash.put(key, 1);
                distinct++;
            }
        }
        
        hash.printHashSizes();
        StdOut.println("\n" + distinct + " distinct words");
    }
}
