/**
 * An implementation of the QuickUnion algorithm
 * for the Union Find data structure
 */
public class QuickUnionUF {
    private int[] id;   // id array to keep track of CC
    
    /**
     * Constructor to initialise data structure
     * 
     * @param N is the number of objects to be considered
     */
    public QuickUnionUF(int N) {
       this.id = new int[N];
       // every element is in its own CC initially
       for (int i = 0; i < N; i++) {
           id[i] = i;
       }
    }
    
    /*
    Find the root of an element in the forest
    */
    private int root(int i) {
        while (i != id[i]) {
            i = id[i];  // move up the tree, till parent is reached
        }
        return i;
    }
    
    /**
     * Check if elements i and j are connected
     * 
     * @param i, j: elements to be checked for connection
     * 
     * @return true, if i & j are connected; false otherwise
     */
    public boolean connected(int i, int j) {
        return root(i) == root(j);  // 2 connected elements have same root
    }
    
    /**
     * Connect 2 elements
     * 
     * @params p, q: elements to be connected
     */
    public void union(int p, int q) {
       int p_root = root(p);
       int q_root = root(q);
       // make p's tree a sub-tree of q's tree
       id[p_root] = q_root;
    }
}