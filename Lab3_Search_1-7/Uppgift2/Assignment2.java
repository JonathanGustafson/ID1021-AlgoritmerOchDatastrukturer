//Assignment2.java

/*
 * THIS PROGRAM DEPENDS ON:
 * -BinarySearchST.java
 * -Queue.java
 * 
 * ----------------------------------
 * @author Jonathan Gustafson
 * 
 * Parts of the code is taken from: https://algs4.cs.princeton.edu/code/
 * 
 * This program reads a given amount of words from a text file,
 * then is saves all words in a symbol table, counts the words.
 * Then the program prints the most common word and its wordcount.
 * the amount of distinct words, how many words that was read
 * and the time it took.
 * 
 * */

import java.util.*;
import java.io.*;
import edu.*;

public class Assignment2 {
    
    public static void main(String[] args) throws FileNotFoundException {
    	
        int distinct = 0, words = 0;
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        
        //tools to read from file
        FileReader fr = new FileReader("C:\\Users\\jonth\\Desktop\\Workspace\\AlgoData_Lab3\\labText.txt");  
        Scanner sc = new Scanner(fr);
        
        //Check start time
        long startTime = System.nanoTime();
        
        // compute frequency counts
        while (words < 1000) {
            String key = sc.next();
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
        }
        
        //Check stop time and calculate elapsed time
        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime) / 1000000; //time in milliseconds
        
        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }
        
        //print results
        StdOut.println("time taken: " + elapsedTime + "ms");
        StdOut.println("most common word: " + max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words = " + words);
    }
}