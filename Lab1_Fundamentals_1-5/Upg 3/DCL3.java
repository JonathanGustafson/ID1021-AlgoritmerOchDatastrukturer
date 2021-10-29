import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Scanner;

/****************************************
 * @author Jonathan Gustafson
 * 
 *  a generic doubly circular linked list 
 *  based on a FIFO queue. The program 
 *  includes the functions enqueue, dequeue. 
 *  The main function in the end of the
 *  code there is a test implemented that
 *  allows the user to test the functions
 *  through the console 
 * 
 * An example test is commenten under 
 * the main function
 * 
 ***************************************/

public class DCL3<Item> implements Iterable<Item> {
	
	private Node<Item> head;
	private int length;
	
	private class Node<Item>{
		
		private Item item;
		private Node<Item> next;
		private Node<Item> prev;
		
		public Node(Item item) {
			this.item = item;
		}
	}
	
	//initialize an empty list
	public DCL3() {
		this.head = null;
		this.length = 0;
	}
	
	//returns true if the list is empty
	public boolean isEmpty() {
		return length == 0;
	}
	
	//returns the length of the list
	public int length() {
		return length;
	}
	
	//add an item to the end of the queue
	public void enqueue(Item item) {
		Node<Item> newNode = new Node<Item>(item);
		if(isEmpty()) {
			head = newNode;
			head.next = head;
			head.prev = head;
		} else {
			Node<Item> prevLast = head.prev;
			head.prev = newNode;
			newNode.next = head;
			prevLast.next = newNode;
		}

		length++;	
	}
	
	//remove the first item of the queue
	public Item dequeue() {
		if(isEmpty()) throw new NoSuchElementException("List empty");
        Item item = head.item;
        Node<Item> last = head.prev;
        head = head.next;
        head.prev = last;
        last.next = head;
        length--;
        
        if(isEmpty())
        	head = null;
        
        return item;
    }

	//converts the elements of the list to a string
	public String toString() {
		if(isEmpty()) throw new NoSuchElementException("List empty");
		StringBuilder s = new StringBuilder();
		for(Item item : this) {
			s.append('[');
			s.append(item);
			s.append("], ");
		}
		return s.toString();
	}
	
	public Iterator<Item> iterator() {
		return new LinkedIterator(head);
	}
	
	// iterator class for doubly circular linked list 
	private class LinkedIterator implements Iterator<Item>{
		
		private Node<Item> current;
		
		private boolean isFirst = true; //marks that it is the first "iteration lap"
		
		//constructor, starts on the head node
		public LinkedIterator(Node<Item> head) {
			current = head;
		}
		
		public boolean hasNext() {
			return !(current == head && !isFirst);
		}

		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			isFirst = false; //makes sure that the iteration stop when it reaches the head again
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
	
public static void main(String[] args) {
		
		DCL3<String> que = new DCL3<>(); //Creat list
		Scanner scanner = new Scanner(System.in); //Create scanner for user input
		System.out.println("1: Queue\n2: Dequeue\n3: Quit");
		
		int index = 0;
		String value = "";
		while (index != 3) {
			index = scanner.nextInt(); //Read operation
			switch(index) {
			case 1: //1 = queue item
				value = scanner.next(); //Read value of item
				que.enqueue(value);
				System.out.println(que);
				break;
			case 2: //2 = dequeue (remove head)
				que.dequeue();
				System.out.println(que);
				break;
			case 3: //3 = exit program
				System.out.println("Quit");
			}
		}
	}
}

/* Test: 1 a 1 b 1 c 1 d 2 1 d 2 2 2 */
