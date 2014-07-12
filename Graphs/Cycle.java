/**
 * Find a cycle in an undirected graph
 */
public class Cycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    
    /**
     * Finds a cycle in the graph
     * 
     * @param G: Graph to find cycle in
     */
    public Cycle(Graph G) {
        // check if there exists a self-loop
        if hasSelfLoop(G) {
            return;
        }
        // check if there exist any parallel edges
        if hasParallelEdges(G) {
            return;
        }
        // do a DFS over all vertices
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, -1, v);
            }
        }
    }  
    
    /*
    Recursive DFS routine
    */
    private void dfs(Graph G, int u, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) {
                // a cycle has been found!
                return;
            }
            if (!marked[w]) {
                dfs(G, v, w);
                edgeTo[w] = v;
            } else if (w != u) {
                cycle = new Stack<Integer>();
                // we have come across something marked
            }
        }
    }
    
    /*
    Check for any self-loops
    */
    private boolean hasSelfLoop(Graph G) {
        // check all vertices for a reference to themselves
        for (int v = 0; v < G.V(); v++) {
            // check adjacency list
            for (int w : G.adj(v)) {
                if (w == v) {
                    // self loop found
                    cycle = new Stack<Integer>();
                    cycle.push(v);
                    cycle.push(v);
                    return true;
                }
            }
        }
        return false;   // reached here => no self-loops
    }
    
    /*
     * Parallel edges cause cycles. Find them!
     */
    private boolean hasParallelEdges(Graph G) {
        boolean[] marked = new boolean[G.V()];  // keeps track if a vertex is already seen
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                } else {
                    // found a v-w parallel edge
                    cycle = new Stack<Integer>();
                    cycle.push(v);
                    cycle.push(w);
                    cycle.push(v);
                    return true;
                }
            }
        } 
        return false;   // no parallel edges
    }
}