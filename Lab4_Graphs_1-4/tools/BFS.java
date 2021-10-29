//BFS.java

/*
 * @author Jonathan gustafson
 * 
 * Dependancies:
 * Graph.java
 * 
 * This class creates search paths in a graph from a specific vertice.
 * (breadth first search)
 * 
 */

package tools;

public class BFS {

	private boolean[] visited;
	private int[] edgeTo;
	private int[] distTo;
	private final int s; 		//starting vertice
	
	//intitialize graph and start the recursive depth first search
	public BFS(Graph g, int s){
		this.s = s;
		visited = new boolean[g.V()];
		edgeTo = new int[g.V()];
		distTo = new int[g.V()];
		
		bfs(g, s);
	}
	
	//recursively traverse the graph, marking the vertices as visited and marking from what vertices we came from
	//(breadth first)
	public void bfs(Graph g, int s) {
		Queue<Integer> q = new Queue<Integer>();
		visited[s] = true;
		q.enqueue(s);
		while(!q.isEmpty()) {
			int v  = q.dequeue();
			for(int w : g.adj(v)) {
				if(!visited[w]) {
					edgeTo[w] = v;
					visited[w] = true;
					q.enqueue(w);
				}
			}
		}
		
	}
	
	public void dfs(DirectedGraph g, int v) {
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
	
	//returns path to v
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