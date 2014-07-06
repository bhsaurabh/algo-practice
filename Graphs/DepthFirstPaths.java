/**
 * Depth first search to get all paths from a source vertex
 */
public class DepthFirstPaths {
    private int s;  // source vertex
    private boolean[] marked;   // to check if a vertex is visited
    private int[] edgeTo;   // to keep track of links
    
    /**
     * Constructor prepares for all queries supported
     * 
     * @param G: graph to be traversed
     * @param s: source vertex
     */
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        
        edgeTo[s] = s;  // reach source from itself
        dfs(G, s);  // call recursive DFS
    }
    
    /*
    Recursively visit an unmarked vertex
    */
    private void dfs(Graph G, int v) {
        marked[v]  = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                // visit this vertex
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }
    
    /**
     * Is there a path from source vertex to another one?
     * 
     * @param v: vertex to check
     * 
     * @return true, if there is a path from s to v; else false
     */
    public boolean hasPathTo(int v) {
        return marked[v]; 
    }
    
    /**
     * Return a path to a vertex
     * 
     * @param v: vertex to which path is required
     * 
     * @return path: A stack(iterable) having all vertices from s to v
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            
        }
    }
}