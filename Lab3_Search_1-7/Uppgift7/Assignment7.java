//Assignment7.java
/* 
 * THIS PROGRAM DEPENDS ON:
 * -BST.java
 * -Queue.java
 * -StdOut.java (assertions)
 * -StdIn.java (assertions)
 * 
 * ----------------------------------
 * @author Jonathan Gustafson
 * 
 * Parts of the code is taken from: https://algs4.cs.princeton.edu/code/
 * 
 * This program reads a given amount of words from a text file,
 * then is saves all words in a search tree and counts the words.
 * Then the program gives the user the option to exit the program,
 * print the read words in alphabetic or reversed alphabetic order.
 * 
 * */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import edu.*;

public class Assignment7 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		int distinct = 0, words = 0;
		BST<String, Integer> bst = new BST<String, Integer>();
        
		FileReader fr = new FileReader("C:\\Users\\jonth\\Desktop\\Workspace\\AlgoData_Lab3\\labText.txt");  
		Scanner sc = new Scanner(fr);
        
        // compute frequency counts
        while (words < 200) {
            String key = sc.next();
            words++;
            if (bst.contains(key)) {
                bst.put(key, bst.get(key) + 1);
            }
            else {
                bst.put(key, 1);
                distinct++;
            }
        }
        
        sc.close();
        
        Scanner scan = new Scanner(System.in);
        StdOut.println("1: Alphabetic order\n2: Reversed Alphabetic order\n0: Quit");
        int input;
        Boolean running = true;
        while(running == true) {
        	input = scan.nextInt();
        	switch(input) {
        		case 1:
        			bst.printAlpha();
        			break;
        		case 2:
        			bst.printReversedAlpha();
        			break;
        		case 0:
        			running = false;
        			StdOut.println("Program stopped");
        			break;
        	}
        }
        
        scan.close();
    }
}
