/**
 * Compute the MST for an EdgeWeightedGraph
 */
public class KruskalMST {
    private Queue<Edge> mst;
    private int weight; // weight of MST
   
    /**
     * Compute the MST (O(ElgE))
     * 
     * @param G: Edge weighted graph whose MST is required
     */
    public KruskalMST(EdgeWeightedGraph G) {
        this.mst = new Queue<Edge>();    // queue of edges in MST
        this.weight = 0;
        
        // insert all edges onto a MinPQ (effectively sorting it)
        // MinPQ could be a min heap
        // O(E) for construction of MinPQ (using Bottom up method)
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        
        // Keep adding edges from PQ (in ascending order)
        // iff they dont create a cycle
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V()-1) {
            // if MinPQ is empty we get a Minimum Spanning Forest
            // G.V() - 1 edges should be in the MST
            // each delMin() is log(E)
            Edge e = pq.delMin();   // get edges in ascending order of weights
            int v = e.either(), w = e.other();
            // add to MST iff this doesnt create a cycle
            if (!uf.connected(v, w)) {  // O(log*V), total = O(Elog*V)
                mst.enqueue(e);
                this.weight += e.weight();
                // reflect changes in UF data structure
                uf.union(v, w);  // O(log*V), total = O(Vlog*V)
            }
        }
    } 
    
    /**
     * @return edges: edges that make up the MST
     */
    public Iterable<Edge> edges() {
        return mst;  // queue is Iterable 
    }
    
    /**
     * @return weight: weight of all edges in MST
     */
    public int weight() {
        return this.weight; 
    }
}