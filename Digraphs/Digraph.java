public class Digraph {
    private int V;
    private int E;
    private Bag<Integer>[] adj;
    
    /**
     * Create a blank graph
     * 
     * @param V: number of vertices in the dograph
     */
    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = (Bag<Integer>[]) new Bag[V];
        
        // initialise every blank bag
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    
    /**
     * Add a directed edge from v to w
     * 
     * @param v, w: source and destination vertices of edge
     */
    public void addEdge(int v, int w) {
        adj[v].add(w); 
        E++;
    }
    
    /**
     * Get the number of vertices in the digraph
     * 
     * @return V: the number of vertices
     */
    public int V() {
        return this.V; 
    }
    
    /**
     * Get the number of edges in the digraph
     * 
     * @return E: the number of edges
     */
    public int E() {
        return this.E; 
    }
    
    /**
     * Get all vertices accessible directly from a vertex
     * 
     * @param v: vertex whose neighbors are needed
     *
     * @return adj: an Iterable over all vertices accessible from v
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    
    /**
     * Get the reverse of the current graph
     * 
     * @return G: the reverse of the current graph
     */
    public Graph reverse() {
        // build the new reverse graph
        Digraph G = new Digraph(this.V);
        
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                G.addEdge(w, v);
            }
        }
        
        return G;
    }
}