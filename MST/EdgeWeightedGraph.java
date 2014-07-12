/**
 * An undirected graph of weighted edges
 */
public class EdgeWeightedGraph {
    private int V;  // number of vertices
    private int E;  // number of edges
    private Bag<Edge>[] adj;   // adjacency lists
    
    /**
     * Constructor initialises a blank graph
     * 
     * @param V: number of vertices in graph
     */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<Edge>[]) new Bag[V];
        // initialise every blank bag
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Edge>();
        }
    }
    
    /**
     * Connect 2 vertices in the graph
     * 
     * @param e: edge to be connected
     */
    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(w);
        adj[w].add(v);
        this.E++;
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
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }
}