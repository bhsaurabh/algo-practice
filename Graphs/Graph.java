/**
 * An undirected graph
 */
public class Graph {
    private int V;  // number of vertices
    private int E;  // number of edges
    private Bag<Integer>[] adj;   // adjacency lists
    
    /**
     * Constructor initialises a blank graph
     * 
     * @param V: number of vertices in graph
     */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<Integer>[]) new Bag[V];
        // initialise every blank bag
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    
    /**
     * Connect 2 vertices in the graph
     * 
     * @param v, w: vertices to be connected
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }
    
    /**
     * @return V: the number of vertices in the graph
     */
    public int V() {
        return this.V;
    }
    
    /**
     * @return E: the number of edges in the graph
     */
    public int E() {
        return this.E;
    }
    
    /**
     * @param v: vertex whose adjacency list is needed
     * 
     * @return adj: an Iterable over all vertices adjacent to a given vertex
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}