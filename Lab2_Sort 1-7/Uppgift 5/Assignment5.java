//Assignment5

/*
 * @author Jonathan Gustafson
 * 
 * LAB2 Assignment 5
 * 
 * this class includes a method for
 * insertion sort and merge sort.
 * 
 * the merge sort uses a cutoff variable
 * to declare a listsize where the sorting
 * algorthim starts using insertion sort
 * instead of merge sort.
 * 
 * in the main method there is a test
 * which creates lists with numbers 
 * randomized between 0-1000 and
 * applies the sorting algorithms
 * on it.
 * ------------------------------
 * 
 * TEST:
 * 
 * In the main function, a randomized list is created
 * with the size of list1 (see row 46).
 * 
 * There are a few lines that prints the lists that
 * are commented out to make it easier to check the 
 * execution times of the algorithms.
 * 
 * If one wants to check the lists there are few lines
 * that can be included. See below
 * 
 * Row 53 - Print the list before it has been sorted.
 * Row 64 - Print the insertion sorted list.
 * 
 */

public class Assignment5 {
	
	private static final int CUTOFF = 30;

	public static void main(String[] args) {
		//create list
		int[] list = new int[10000000];
				
			//fill the list with random integers between 1000
		for(int i = 0; i < list.length; i++)
			list[i] = (int) ((int) 1000 * Math.random());
				
		//print unsorted list
		//System.out.println(toString(list1) + "\n");

		//create timer variables
		long start, stop, sortTime;
		
		//time merge sort, and print the time
		start = System.nanoTime();
		list = mergeSort(list);
		stop = System.nanoTime();
		sortTime = (stop - start)/1000000;  //divide by 1000000 to convert from nano to milliseconds.
		System.out.println("Merge-sort, time: " + sortTime + " ms");
		//System.out.println("MergeSorted: " + toString(list) + "\n");
	}
	
	private static void insertSort(int[] list) {
		
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
	
	private static int[] mergeSort(int[] list) {
		
		//cutoff to insertion sort
		if(list.length < CUTOFF) {
			insertSort(list);
			return list;
		}
		
		int mid = list.length / 2;	//a point to  mark the middle of the list
		int[] left = new int[mid];	//create the left part of the split list
		int[] right;				//declare the right part of the split list
		
		//if statment to see if the list length is even or not
		//this is needed to make the correct size for the right list
		if(list.length % 2 == 0)
			right = new int[mid];
		else
			right = new int[mid+1];
		
		//fill the left list with the first half of the main array
		for(int i = 0; i < mid; i++)
			left[i] = list[i];
		
		//fill the right list with the 2nd half of the main array
		for(int i = 0; i < right.length; i++)
			right[i] = list[mid + i];
		
		int[] combined = new int [list.length];
		
		left = mergeSort(left);
		right = mergeSort(right);
		
		combined = merge(left,right);
		
		return combined;
	}
	
	private static int[] merge(int[] left, int[] right) {
		
		//create an array where you can mergo two arrays into one;
		int[] combined = new int[left.length + right.length]; 
		
		//create pointer variables for the left/right/combined list
		int leftPointer = 0;
		int rightPointer = 0;
		int combinedPointer = 0;
		
		//while there are still elements in the left or right list
		while(leftPointer < left.length || rightPointer < right.length) {
			
			//if there is elements left in both lists
			if(leftPointer < left.length && rightPointer < right.length) {
				
				//compare the nr at leftPointer with rightPointer and insert the smallest nr in combined
				//increment the combined pointer aswell as the left/right that was smallest
				if(left[leftPointer] < right[rightPointer]) 
					combined[combinedPointer++] = left[leftPointer++];
				else 
					combined[combinedPointer++] = right[rightPointer++];
			}
			//check what list has elements left and insert them into the combined list
			else if(leftPointer < left.length)
				combined[combinedPointer++] = left[leftPointer++];
			else if(rightPointer < right.length)
				combined[combinedPointer++] = right[rightPointer++];
		}
		
		return combined;
	}
	
	static String toString(int[] list) {
		StringBuilder s = new StringBuilder();
		s.append("\n[");
		for(int i = 1; i < list.length; i++) {
			s.append(list[i]);
			s.append(", ");
			if(i % 10 == 0)
				s.append("\n");
		}
		s.append(list[list.length-1] + "]");
		return s.toString();
	}

}
