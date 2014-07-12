public class DirectedBFS {
    private int s;
    private int[] distTo;
    private int[] edgeTo;
    
    /**
     * Constructor readies for any queries
     * 
     * @param G: Digraph to be traversed
     * @param s: source vertex
     */
    public DirectedBFS(Digraph G, int s) {
        this.s = s;
        this.distTo = new int[G.V()];
        this.edgeTo = new int[G.V()];
        
        for (int i = 0; i < G.V(); i++) {
            this.distTo = -1;   // to store that this has not been visited
        }
        bfs(G, s);
    }
    
    /*
    Non-recursive BFS implementation
    */
    private void bfs(Digraph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(s);
        distTo[s] = 0;
        edgeTo[s] = s;
        
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked(w)) {
                    q.enqueue(w);
                    distTo[w] = distTo[v] + 1;
                    edgeTo[w] = v;
                }
            }
        }
    }
    
    /*
    Check if a vertex has been visited or not
    */
    private boolean marked(int v) {
        return distTo[v] != -1;  
    }
    
    /**
     * Check if there exists a path from source s to vertex v
     * 
     * @param v: vertex to be checked for connectivity
     * 
     * @return val: true if there is a path; false otherwise
     */
    public boolean hasPathTo(int v) {
        return marked(v); 
    }
    
    /**
     * Get the path from s to v
     * 
     * @param v: vertex to which path is needed
     * 
     * @return path: Iterable over all vertices that make up the path from s to v
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        } 
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);   // complete path
        return path;
    }
    
    /**
     * Get the shortest distance to a given vertex
     * 
     * @param v: vertex to which distance is needed
     * 
     * @return dist: distance to vertex
     */
    public int distTo(int v) {
        return distTo[v]; 
    }
}