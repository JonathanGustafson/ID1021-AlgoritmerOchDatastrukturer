//Assignment2.java

/*
 * @author Jonathan Gustafson
 * 
 * This program includes an insertion sort algorithm,
 * aswell as a method that counts the amount of inversions
 * a list has.
 * 
 * In the main method, there is a test. The test
 * works in the way that the user types the size
 * and content of the array that is supposed to be
 * checked for inverstions. 	
 * 					
 *           size of array	
 *                |
 * Test example: "6 1 2 5 3 4 0"
 * --------------------------------------
 * Time complexity (invCount):
 * 
 * if the size of the array is n the amount
 * of operations will be:
 * 
 * 1 + 2 + ... + (n-1) = n(n-1)/2
 * 
 * This makes the time complexity O(n^2)
 * 
 * */

import java.util.Scanner;

public class Assignment2 {
	
	public static void main(String[] args) {
		
		//scanner for user input
		Scanner scanner = new Scanner(System.in);
		
		//enter lenght of array
		System.out.println("Enter length of array:");
		int length = scanner.nextInt();
		
		//create array with given size
		int[] list = new int[length];
		
		//Take user input and put it in an array
		System.out.println("Enter content of array\n");
		for(int i = 0; i < length; i++) {
			int element = scanner.nextInt();
			list[i] = element;
		}
		
		//count the inversions and print it
		int inversions = invCount(list);
		System.out.println("Inversions: " + inversions);
		
		//Sort the list
		//Sort(list);
		
		scanner.close();
		
	}
	
	public static void Sort(int[] list) {
		
		int swapCount = 0;
		
		//i starts on the 2nd element
		for(int i = 1; i < list.length; i++) {
			
			int current = list[i];
			//System.out.println("Current: " + current);
			
			//j starts on the element to the left i
			int j = i-1;
			
			//while the current item is less than j, move j one step to the right.
			while(j >= 0 && list[j] > current) {
				list[j + 1] = list[j]; //move the element on j one step to the right
				j--;
				swapCount++;
				//print the list 
				System.out.println(toString(list) + " Current: " + current);
			}
			
			list[j+1] = current; //place current in the right slot
		}
		//print number of swaps
		System.out.println("Sorted List: " + toString(list));
		System.out.println("Swaps: " + swapCount);
	}
	
	static int invCount(int[] list) {
		int invCount = 0;
		for(int i = 0; i < list.length ; i++) 
			for(int j = i+1; j < list.length; j++)
				if(list[i] > list[j]) {
					invCount++;
					System.out.print("[" + i + ", " + list[i] + "],[" + j + ", " + list[j] + "]\n");;
				}
		System.out.println("");
		
		return invCount;
	}
	
	static String toString(int[] list) {
		StringBuilder s = new StringBuilder();
		s.append('[');
		for(int i = 0; i < list.length - 1; i++) {
			s.append(list[i]);
			s.append(", ");
		}
		s.append(list[list.length-1] + "]");
		return s.toString();
	}

}
