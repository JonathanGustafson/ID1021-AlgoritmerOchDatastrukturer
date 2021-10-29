//DFS.java

/*
 * @author Jonathan gustafson
 * 
 * Dependancies:
 * -Graph.java
 * -Stack.java
 * 
 * This class creates search paths in a graph from a specific vertice.
 * (depth first search)
 * 
 */

package tools;

public class DFS {

	private boolean[] visited;
	private int[] edgeTo;
	private final int s; //starting vertice
	
	//intitialize graph and start the recursive depth first search
	public DFS(Graph g, int s){
		this.s = s;
		visited = new boolean[g.V()];
		edgeTo = new int[g.V()];
		
		dfs(g, s);
	}
	
	//recursively traverse the graph, marking the vertices as visited and marking from what vertices we came from
	public void dfs(Graph g, int v) {
		visited[v] = true;
		
		for(int w : g.adj(v)) {
			if(!visited[w]) {
				edgeTo[w] = v;
				dfs(g, w);
			}
		}
	}

	//returns if there is a path from s to v
	public boolean hasPathTo(int v) {
		return visited[v];
	}
	
	//Returns path to v
	public Iterable<Integer> pathTo(int v){
		
		if(!hasPathTo(v))
			return null;
		
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int w = v; w != s; w=edgeTo[w]) {
			stack.push(w);
		}
		stack.push(s);
		
		return stack;
	}

}
