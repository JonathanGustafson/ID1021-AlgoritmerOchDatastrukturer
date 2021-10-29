//Assignment6

/*
 * @author Jonathan Gustafson
 * 
 * LAB2 Assignment 6
 * 
 * this class includes a method for
 * insertion sort and two quick sorts.
 * 
 * One quick sort uses the first element
 * in the list as pivot element, and the
 * second quicksort uses the median
 * between the first, middle and last 
 * element of the list as pivot element.
 * 
 * In the main method there is a test
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
 * Row 67 - Print the Quick sorted list (First element as pivot).
 * Row 75 - Print the Quick sorted list (Median element as pivot).
 * 
 */

public class Assignment6 {

	public static void main(String[] args) {
			//create list
			int[] list1 = new int[100000000];
				
			//fill the list with random integers between 1000
			for(int i = 0; i < list1.length; i++)
				list1[i] = (int) ((int) 1000 * Math.random());
				
			//print unsorted list
			//System.out.println("Unsorted list\n" + toString(list1) + "\n");

			//Create a copy of list1
			int[] list2 = list1;
				
			//create timer variables
			long start, stop, sortTime;
				
			//time quick sort with first element as pivot, and print the time
			start = System.nanoTime();
			quickSort1(list1);
			stop = System.nanoTime();
			sortTime = (stop - start)/1000000;  //divide by 1000000 to convert from nano to milliseconds.
			System.out.println("Quick-sort(first element = pivot), time: " + sortTime + " ms");
			//System.out.println("QuickSorted: " + toString(list1) + "\n");

			//time quick sort with first element as pivot, and print the time
			start = System.nanoTime();
			quickSort2(list2);
			stop = System.nanoTime();
			sortTime = (stop - start)/1000000;  //divide by 1000000 to convert from nano to milliseconds.
			System.out.println("Quick-sort(median element as pivot), time: " + sortTime + " ms");
			//System.out.println("QuickSorted: " + toString(list1) + "\n");
	}
	
	//calls itself but with the indexes for the first and the last element of the list
	private static void quickSort1(int[] list) {
		quickSort1(list, 0, list.length -1);
	}
		
	//quick sort with first element as pivot
	private static void quickSort1(int[] list, int leftPointer, int rightPointer) {
		//exit contidition
		if(leftPointer >= rightPointer)
			return;
			
		//Choose pivotelement as the middle index of the list
		int pivot = list[leftPointer];
			
		//use partition method to put all elements lower than the pivot element to the left
		//of the pivot element, and all the highter elements to the right of the pivot element
		int index = partition(list, leftPointer, rightPointer, pivot);
			
		//sort the left and right side of the previous pivot element
		quickSort1(list, leftPointer, index - 1);
		quickSort1(list, index, rightPointer);
	}
	
	//calls itself but with the indexes for the first and the last element of the list
	private static void quickSort2(int[] list) {
		quickSort2(list, 0, list.length -1);
	}
	
	//quick sort with median of three elements as pivot
	private static void quickSort2(int[] list, int leftPointer, int rightPointer) {
		//exit contidition
		if(leftPointer >= rightPointer)
			return;
			
		//Choose pivotelement as median of the first, middle and last element of the list
		int pivot = median(list[leftPointer], list[(leftPointer+rightPointer)/2],list[rightPointer]);
			
		//use partition method to put all elements lower than the pivot element to the left
		//of the pivot element, and all the highter elements to the right of the pivot element
		int index = partition(list, leftPointer, rightPointer, pivot);
			
		//sort the left and right side of the previous pivot element
		quickSort2(list, leftPointer, index - 1);
		quickSort2(list, index, rightPointer);
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
	
	//takes a list, uses insertSort to sort a list, and returns the median
	private static int median(int a, int b, int c) {
		int[] list = {a,b,c};
		insertSort(list);
		return list[list.length/2];
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
