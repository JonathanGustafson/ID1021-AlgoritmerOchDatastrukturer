//Graph.java

/*
 * @author Jonathan Gustafson
 * 
 * Dependancies: Bag.java
 * 
 * This class creates an undirected graph and allows for adding of edges between vertices
 * aswell as getting information about the vertices.
 * 
 */

package tools;

public class Graph {
	
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	
	//initialize graph with size V
	public Graph(int V){
		
		this.V = V;
		adj = (Bag<Integer>[]) new Bag[V];
		
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}
	
	//returns number of vertices
	public int V() {
		return V;
	}
	
	//returns number of edges
	public int E() {
		return E;
	}
	
	//adds edge between a and b
	public void addEdge(int a, int b) {
		adj[a].add(b);
		adj[b].add(a);
		E++;
	}
	
	//get the degree of a vertice
	public int getDegree(int v) {
		return adj[v].size();
	}
	
	//returns a bag of ajdacent vertices of v
	public Iterable<Integer> adj(int v){
		return adj[v];
	}

}
