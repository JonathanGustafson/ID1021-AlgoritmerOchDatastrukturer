//Assignment6.java

/*
 * THIS PROGRAM DEPENDS ON:
 * 
 * -SeparateChaininghash.java
 * -SequentialSearchST.java
 * -StdIn.java
 * -StdOut.java
 * 
 * ----------------------------------
 * @author Jonathan Gustafson
 * 
 * Parts of the code is taken from: https://algs4.cs.princeton.edu/code/
 * 
 * This program reads a given amount of words from a text file,
 * then is saves all words in a separate chaining hash table,
 * and counts the words.
 * Then the program asks the user to write a word, and then
 * the program prints the amount of that specific word that
 * exists in the given words.
 * 
 * */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import edu.*;

public class Assignment6 {

		public static void main(String[] args) throws FileNotFoundException {
			int distinct = 0, words = 0;
			SeparateChainingHashST<String, Integer> hash = new SeparateChainingHashST<String, Integer>();
	        
			FileReader fr = new FileReader("C:\\Users\\jonth\\Desktop\\Workspace\\AlgoData_Lab3\\labText.txt");  
			Scanner sc = new Scanner(fr);
	        
	        // compute frequency counts
	        while (sc.hasNext()) {
	            String key = sc.next();
	            //words++;
	            if (hash.contains(key)) {
	                hash.put(key, hash.get(key) + 1);
	            }
	            else {
	                hash.put(key, 1);
	                distinct++;
	            }
	        }
	        
	        sc.close();
	        
	        Scanner scan = new Scanner(System.in);
	        String input;
	        
	       StdOut.println("Type a word to check (0 to exit):");
	        
	        while (true) {
	        	input = scan.next();
	        	if(input.equalsIgnoreCase("0"))
	        		break;
	        	
	        	if(hash.contains(input)) {
	        		int wordCount = hash.get(input);
	        		StdOut.println(input + ": " + wordCount);
	        	}
	        	else
	        		StdOut.println("Word not found");
	        		
	        }
	        
	        StdOut.println("Program stopped");
	    }
}
