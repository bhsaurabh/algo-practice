/**
 * Determine connected components in an undirected graph
 */
public class CC {
    private int[] id;
    private int count;
    
    /**
     * Constructor prepares for constant time connected queries
     * 
     * @param G: Graph to be traversed
     */
    public CC(Graph G) {
        this.count = 0;
        this.id = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            id[i] = -1;
        }
        
        // pre-preocess graph
        for (int v = 0; v < G.V(); v++) {
            if (!marked(v)) {
                // vertex v has not been visited, do a DFS on it
                dfs(G, v);
                count++;
            }
        }
    }
    
    /*
    Check if a vertex has been visited or not
    */
    private boolean marked(int v) {
        return this.id[v] != -1;  
    }
    
    /*
    Depth First Search from a vertex
    */
    private void dfs(Graph G, int v) {
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked(w)) {
                dfs(G, w);
            }
        }
    }
    
    /**
     * Check if 2 vertices are connected or not
     * 
     * @param v, w: vertices to be checked
     * 
     * @return true if connected, false otherwise
     */
    public boolean connected(int v, int w) {
        return id(v) == id(w); 
    }
    
    /**
     * Determine which connected component a vertex lies in
     * 
     * @param v: vertex to be checked
     * 
     * @return: connected component number
     */
    public int id(int v) {
        return id[v]; 
    }
    
    /**
     * @return num: the number of connected components
     */
    public int count() {
        return count; 
    }
}