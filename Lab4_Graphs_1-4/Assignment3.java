//Assignment3.java
/*
 * @author Jonathan Gustafson
 * 
 * Dependencies:
 * -Graph.java
 * -BFS.java
 * -Stack.java
 * -Bag.java
 * -Queue.java
 * 
 * This class includes a main function, which takes data from a contiguous-usa-dat
 * and creates an undirected graph with the connections.
 * Then the program allows the user to enter a state A and a state B to get
 * a path between A and C (through B) using breadth first search.
 * 
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import tools.*;

public class Assignment3 {

	public static void main(String[] args) throws IOException {
		
		//tools to read from file
		File file = new File("C:\\Users\\Anvandare\\Desktop\\Workspace\\AlgoDataLAB4\\contiguous-usa.dat");
		FileReader fr1 = new FileReader(file);
		Scanner scan1 = new Scanner(fr1);
		        
		//Create symbol table and add content of input data
		SymbolTable st = new SymbolTable(50);
		String str1, str2; 	//strings used for reading data
		while(scan1.hasNext()) {
			str1 = scan1.next();
			if(!st.contains(str1))
				st.add(str1);			
		}
				
		//create new scanner to read file again.
		scan1.close();
		fr1.close();
		FileReader fr2 = new FileReader(file);
		Scanner scan2 = new Scanner(fr2);
				
		//create graph and add the edges from the database
		Graph g = new Graph(st.size());
		while(scan2.hasNext()) {
			str1 = scan2.next();
			str2 = scan2.next();
			g.addEdge(st.getIndex(str1), st.getIndex(str2));
		}
		        
		//create new scanner to read user input
		scan2.close();
		fr2.close();
		Scanner scan3 = new Scanner(System.in);
		
		//take input from user and print path from A to C through B if path to B exists
		//if path to B doesn't exist, print path from A to C
		while(true) {
			System.out.println("Choose point A, B and C: ");
			String a = scan3.next();
			String b = scan3.next();
			String c = scan3.next();
			if(a.equals("0") || b.equals("0") || c.equals("0")) {
				System.out.println("Program stopped");
				break;
			}
			
			BFS bfs = new BFS(g, st.getIndex(a));
        
			System.out.println("Path from " + a + " to " + c + " through " + b + "\n");
			
			if(bfs.hasPathTo(st.getIndex(b))) {
				BFS bfsC = new BFS(g,st.getIndex(b));
				
				//print path from A to B
				for(int i : bfs.pathTo(st.getIndex(b))) 
					System.out.print(st.get(i) + " ");
				

				System.out.print(" (now at point B) ");
				
				//print path from B to C
				for(int i : bfsC.pathTo(st.getIndex(c))) 
					System.out.print(st.get(i) + " ");
				
				System.out.println(" (now at point C) ");
				System.out.println("\n");
			}
			else {	
				for(int i : bfs.pathTo(st.getIndex(c)))
					System.out.print(st.get(i) + " ");
					System.out.println("\n");
			}
		}
		
		scan3.close();
	}

}
