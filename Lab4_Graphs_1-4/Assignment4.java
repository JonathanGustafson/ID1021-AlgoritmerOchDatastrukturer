//Assignment4.java
/*
 * @author Jonathan Gustafson
 * 
 * Dependencies:
 * -DirectedGraph.java
 * -DirectedDFS.java
 * -Stack.java
 * -Bag.java
 * 
 * This class includes a main function, which takes data from a contiguous-usa-dat
 * and creates a directed graph with the connections.
 * Then the program allows the user to enter a state A and a state B to get
 * a path between A and B using depth first search.
 * 
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import tools.*;

public class Assignment4 {

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
		DirectedGraph g = new DirectedGraph(st.size());
		while(scan2.hasNext()) {
			str1 = scan2.next();
			str2 = scan2.next();
			g.addEdge(st.getIndex(str1), st.getIndex(str2));
		}
        
		//create new scanner to read user input
		scan2.close();
		fr2.close();
		Scanner scan3 = new Scanner(System.in);
		
		while(true) {
			System.out.println("Choose point A and B: ");
			String a = scan3.next();
			String b = scan3.next();
			
			if(a.equals("0") || b.equals("0")) {
				System.out.println("Program stopped");
				break;
			}
			
			DirectedDFS dfs = new DirectedDFS(g, st.getIndex(a));
        
			if(dfs.hasPathTo(st.getIndex(b))){
				System.out.print("Path from " + a + " to " + b + ": ");
				for(int i : dfs.pathTo(st.getIndex(b)))
					System.out.print(st.get(i) + " ");
				System.out.println("\n");
			}
			else {
				System.out.println("No path available");
			}
		}
		
		scan3.close();
	}
}