/**
 * An implementation of the QuickFind algorithm
 * for the Union Find data structure
 */
public class QuickFindUF {
    private int[] id;   // id array to keep track of CC
    
    /**
     * Constructor to initialise data structure
     * 
     * @param N is the number of objects to be considered
     */
    public QuickFindUF(int N) {
       this.id = new int[N];
       // every element is in its own CC initially
       for (int i = 0; i < N; i++) {
           id[i] = i;
       }
    }
    
    /**
     * Check if elements i and j are connected
     * 
     * @param i, j: elements to be checked for connection
     * 
     * @return true, if i & j are connected; false otherwise
     */
    public boolean connected(int i, int j) {
        return id[i] == id[j];
    }
    
    /**
     * Connect 2 elements
     * 
     * @params p, q: elements to be connected
     */
    public void union(int p, int q) {
       int p_id = id[p];
       int q_id = id[q];
       // replace all occurences of p_id with q_id
       for (int i = 0; i < id.length; i++) {
           if (id[i] == p_id) {
               id[i] = q_id;
           }
       }
    }
}