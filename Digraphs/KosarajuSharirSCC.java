public class KosarajuSharirSCC {
    private boolean[] marked;
    private int[] scc;  // keep track of CC IDs
    private int count;
    
    /**
     * Prepares for constant time queries
     * 
     * @param G: Digraph whose SCCs are needed
     */
    public KosarajuSharirSCC(Digraph G) {
        // initialise all member variables
        marked = new boolean[G.V()];
        scc = new int[G.V()];
        count = 0;
        // perform topological sort on reverse graph
        DepthFirstOrder dfo = new DepthFirstOrder(G.reverse());
        // use reverse post order on reverse graph as vertex order
        for (int v : dfo.reversePost()) {
            if (!marked[v]) {
                // traverse this vertex and add to SCC
                dfs(G, v);
                count++;    // increment count here only; not outside
            }
        }
    }
    
    /*
    Recursive DFS
    */
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        scc[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }
    
    /**
     * Check if 2 vertices are connected strongly
     * 
     * @param v, w: vertices to be checked
     * 
     * @return true if strongly connected; false otherwise
     */
    public boolean stronglyConnected(int v, int w) {
        return scc[v] == scc[w]; 
    }
    
    /**
     * @return count: the number of strongly connected components
     */
    public int count() {
        return count; 
    }
}