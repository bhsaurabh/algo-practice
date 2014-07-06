public class BreadthFirstPaths {
    private int s;  // source vertex
    private int[] edgeTo;
    private int[] distTo;
    
    /**
     * Prepares to answer client queries
     * 
     * @param G: Graph to be traversed
     * @param s: source vertex
     */
    public BreadthFirstPaths(Graph G, int s) {
        this.s = s;
        this.edgeTo = new int[G.V()];
        this.distTo = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            this.distTo[i] = -1;
        }
        
        bfs(G, s);  // call BFS routine
    }
    
    /*
    Breadth First Search
    */
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(s);
        edgeTo[s] = s;
        distTo[s] = 0;
        
        while (!q.isEmpty()) {
            int v = q.dequeue();
            int new_dist = distTo[v] + 1;   // distance of adjacent vertices
            for (int w : G.adj(v)) {
                if (distTo[w] == -1) {
                    // vertex w has not yet been visited
                    q.enqueue(w);
                    edgeTo[w] = v;
                    distTo[w] = new_dist;
                }
            }
        }
    }
    
    /**
     * Check if there exists a path from the souce vertex to another vertex
     * 
     * @param v: vertex to be checked
     * 
     * @return true, if there exists a path; false otherwise
     */
    public boolean hasPathTo(int v) {
        return distTo[v] != -1; 
    }
    
    /**
     * Get the path from source to another vertex
     * 
     * @param v: vertex to which path is required
     * 
     * @return path: an iterable which has all vertices on path from s to v
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;    // no path
        } 
        Stack<Integer> path = new Stack<Integer>(); // as vertices are in reverse order
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}