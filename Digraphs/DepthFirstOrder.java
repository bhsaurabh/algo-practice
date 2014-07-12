/**
 * Topological sorting for a DAG
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Stack<Integer> reversePost;
    
    /**
     * Constructor calls methods to compute tological order
     * 
     * @param G: Digraph to be processed (acyclic)
     */
    public DepthFirstOrder(Digraph G) {
        this.marked = new boolean[G.V()];
        this.reversePost = new Stack<Integer>();
        
        // start dfs from all unmarked vertices
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }
    
    /*
    Recursive DFS method
    */
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            dfs(G, w);
        }
        reversePost.push(v);    // push on stack once this vertex is done
    }
    
    /**
     * @return reversePost: vertices in topologically sorted order
     */
    public Iterable<Integer> reversePost() {
        return reversePost; 
    }
}