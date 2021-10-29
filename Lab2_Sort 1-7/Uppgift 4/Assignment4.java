//Assignment4

/*
 * @author Jonathan Gustafson
 * 
 * LAB2 Assignment 4
 * 
 * this class includes a method for insertion sort, 
 * merge sort and quick sort. 
 * 
 * in the main method there is a test which creates 
 * lists with numbers randomized between 0-1000 and
 * applies the sorting algorithms on it.
 *
 * ------------------------------
 * 
 * TEST:
 * 
 * In the main function, a randomized list is created
 * with the size of list1 (see row 40).
 * 
 * There are a few lines that prints the lists that
 * are commented out to make it easier to check the 
 * execution times of the algorithms.
 * 
 * If one wants to check the lists there are few lines
 * that can be included. See below
 * 
 * Row 51 - Print the list before it has been sorted.
 * Row 62 - Print the insertion sorted list.
 * Row 70 - Print the merge sorted list.
 * Row 78 - Print the quick sorted list.
 *
 */

public class Assignment4 {

	public static void main(String[] args) {
		//create list
		int[] list1 = new int[50000];
		
		//fill the list with random integers between 1000
		for(int i = 0; i < list1.length; i++)
			list1[i] = (int) ((int) 1000 * Math.random());
		
		//Create two copies of the randomized list
		int[] list2 = list1;
		int[] list3 = list1;
		
		//print unsorted list
		//System.out.println(toString(list1));

		//create timer variables
		long start, stop, sortTime;
		
		//time insert sort, and print the time
		start = System.nanoTime();
		insertSort(list1);
		stop = System.nanoTime();
		sortTime = (stop - start)/1000000;  //divide by 1000000 to convert from nano to milliseconds.
		System.out.println("Insertion-sort, time: " + sortTime + " ms");
		//System.out.println("InsertSorted:" + toString(list1));
		
		//time merge sort, and print the time
		start = System.nanoTime();
		list2 = mergeSort(list2);
		stop = System.nanoTime();
		sortTime = (stop - start)/1000000;  //divide by 1000000 to convert from nano to milliseconds.
		System.out.println("Merge-sort, time: " + sortTime + " ms");
	    //System.out.println("MergeSorted: " + toString(list2));
		
		//time quick sort, and print the time
		start = System.nanoTime();
		quickSort(list3);
		stop = System.nanoTime();
		sortTime = (stop - start)/1000000;  //divide by 1000000 to convert from nano to milliseconds.
		System.out.println("Quick-sort, time: " + sortTime + " ms");
		//System.out.println("QuickSorted: " + toString(list3));

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
		
		//exit condition
		if(list.length <= 1) 
			return list;
		
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
	
	//calls itself but with the indexes for the first and the last element of the list
	private static void quickSort(int[] list) {
		quickSort(list, 0, list.length -1);
	}
	
	private static void quickSort(int[] list, int leftPointer, int rightPointer) {
		//exit contidition
		if(leftPointer >= rightPointer)
			return;
		
		//Choose pivotelement as the middle index of the list
		int pivot = list[(leftPointer + rightPointer) /2];
		
		//use partition method to put all elements lower than the pivot element to the left
		//of the pivot element, and all the highter elements to the right of the pivot element
		int index = partition(list, leftPointer, rightPointer, pivot);
		
		//sort the left and right side of the previous pivot element
		quickSort(list, leftPointer, index - 1);
		quickSort(list, index, rightPointer);
	}
	
	private static int partition(int[] list, int leftPointer, int rightPointer, int pivot) {
		
		while(leftPointer <= rightPointer) {
			//check on the left side of the pivot for an element greater than the pivot element
			while(list[leftPointer] < pivot)
				leftPointer++;
			
			//check on the right side for an element smaller than the pivot element
			while(list[rightPointer] > pivot)
				rightPointer--;
			
			//if leftPointer is to the left or on the rightPointer, swap the found elements.
			if(leftPointer <= rightPointer) {
				swap(list, leftPointer, rightPointer);
				leftPointer++;
				rightPointer--;
			}
		}
		return leftPointer;
		
	}
	
	private static void swap(int[]list, int leftPointer, int rightPointer){
		int temp = list[leftPointer];
		list[leftPointer] = list[rightPointer];
		list[rightPointer] = temp;
		
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
