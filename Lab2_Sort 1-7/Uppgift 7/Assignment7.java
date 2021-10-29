//Assignment7.java

/*
 * @author Jonathan gustafson
 * 
 * This program takes input from the user to create a list.
 * After that the program multiplies the input by -1 and 
 * uses insertsort, and the multiplies the inpub by -1 
 * again to sort the list in a descending order.
 * 
 * ----------------------------------------
 * 
 * TESTING:
 * 
 * 	 Size of list
 *		  | 
 * Test: "6 1 2 5 3 4 0"
 * 
 * */


import java.util.Scanner;

public class Assignment7 {

	public static void main(String[] args) {
		
		//scanner for user input
		Scanner scanner = new Scanner(System.in);
				
		//enter lenght of array
		System.out.println("Enter length of array:");
		int length = scanner.nextInt();
				
		//create array with given size
		int[] list = new int[length];
				
		//Take user input and put it in an array
		System.out.println("Enter content of array");
		for(int i = 0; i < length; i++) {
			int element = scanner.nextInt();
			list[i] = -1*element;
		}
				
		insertSort(list);
				
		for(int i = 0; i < length; i++)
			list[i] *= -1;
				
		System.out.println(toString(list));
				
		scanner.close();

	}
	
	public static void insertSort(int[] list) {
		
		//i starts on the 2nd element
		for(int i = 1; i < list.length; i++) {
			
			int current = list[i];
			
			//j starts on the element to the left i
			int j = i-1;
			
			//while the current item is less than j, move j one step to the right.
			while(j >= 0 && list[j] > current) {
				list[j + 1] = list[j]; //move the element on j one step to the right
				j--;
				//print the list 
			}
			
			list[j+1] = current; //place current in the right slot
		}
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
