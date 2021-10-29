import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Scanner;

/****************************************
 * @author Jonathan Gustafson
 * 
 *  a generic doubly circular linked list 
 *  based on a FIFO queue. The program 
 *  includes the functions insertFirst, 
 *  insertEnd, removeFirst and insertEnd. 
 *  The main function in the end of the
 *  code there is a test implemented that
 *  allows the user to test the functions
 *  through the console 
 * 
 *  An example test is commenten under 
 *  the main function
 * 
 ***************************************/

public class DCL4<Item> implements Iterable<Item> {
	
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
	public DCL4() {
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
	
	//returns the value of the head
	public Item peekHead() {
		return head.item;
	}
	
	//add an item to the front of the list
	public void insertFront(Item item) {
		Node<Item> newNode = new Node<Item>(item);
		if(isEmpty()) {
			head = newNode;
			head.next = head;
			head.prev = head;
		} else {
			Node<Item> prevHead = head;
			newNode.prev = prevHead.prev;
			prevHead.prev.next = newNode;
			newNode.next = prevHead;
			head = newNode;
			prevHead.prev = head;
		}

		length++;	
	}
	
	//add an item to the end of the queue
	public void insertEnd(Item item) {
		Node<Item> newNode = new Node<Item>(item);
		if(isEmpty()) {
			head = newNode;
			head.next = head;
			head.prev = head;
		} else {
			Node<Item> prevLast = head.prev;
			head.prev = newNode;
			newNode.next = head;
			newNode.prev = prevLast;
			prevLast.next = newNode;	
		}
		
		length++;	
	}
	
	//remove the first item of the list
	public Item removeFront() {
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
	
	//remove the last item of the list
		public Item removeEnd() {
			if(isEmpty()) throw new NoSuchElementException("List empty");
	        Item item = head.prev.item;
	        
	        Node<Item> newLast = head.prev.prev;
	        newLast.next = head;
	        head.prev = newLast;
	        
	        length--;
	        
	        if(isEmpty())
	        	head = null; //if the last element is removed, reset the list
	        
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
		
		DCL4<String> que = new DCL4<>();
		Scanner scanner = new Scanner(System.in);
		System.out.println("1: insertFront\n"
						 + "2: insertEnd\n"
						 + "3: removeFront\n"
						 + "4: removeLast\n"
						 + "5: Quit");
		
		int index = 0;
		String value = "";
		while (index != 5) {
			index = scanner.nextInt();
			switch(index) {
			case 1:
				value = scanner.next();
				que.insertFront(value);
				System.out.println(que);
				break;
			case 2:
				value = scanner.next();
				que.insertEnd(value);
				System.out.println(que);
				break;
			case 3: 
				que.removeFront();
				System.out.println(que);
				break;
			case 4: 
				que.removeEnd();
				System.out.println(que);
				break;
			case 5: 
				System.out.println("Program stopped");
				break;
			}
		}
		
	}
}

/*Test: 2 jag 2 heter 1 Hej 2 X 3 4 */