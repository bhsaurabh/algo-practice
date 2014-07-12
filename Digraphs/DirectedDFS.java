public class DirectedDFS {
    private int s;
    private boolean[] marked;
    private int[] edgeTo;
    
    /**
     * Constructor performs the DFS on the digraph
     * 
     * @param G: Digraph to be traversed
     * @param s: source vertex to begin DFS from
     */
    public DirectedDFS(Digraph G, int s) {
        this.s = s;
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        
        dfs(G, s);
    }
    
    /*
    Recursive DFS routine
    */
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {   // perform DFS for all un-marked adjacent vertices 
                dfs(G, w);
                edgeTo[w] = v;
            }
        }
    }
    
    /**
     * Is there a directed path from source to a given vertex
     * 
     * @param v: vertex to check for path
     * 
     * @return true, if there exists a path; false otherwise
     */
    public boolean hasPathTo(int v) {
        return marked[v]; 
    }
    
    /**
     * Get the path from s to v
     * 
     * @param v: vertex to which path is needed
     * 
     * @return path: an iterable having all vertices which form the path
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        } 
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        
        return path;
    }
}