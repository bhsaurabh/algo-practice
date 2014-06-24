import java.lang.Comparable;
import java.lang.Iterable;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Priority Queue that holds elements in an unordered array
 */
public class UnorderedPQ<Key extends Comparable<Key>> implements Iterable<Key> {
    private Key[] pq;
    private int N;  // number of elements
    
    /**
     * Constructor initialises the data structure
     */
    public UnorderedPQ() {
        this.pq = (Key[]) new Comparable[1];
        this.N = 0;
    }
    
    /**
     * Insert an element into the PQ
     * 
     * @param key: Key to be inserted in the PQ
     */
    public void insert(Key key) {
       if (N == pq.length) {
           resize(pq.length * 2);
       }
       pq[N++] = key;
    }
    
    /*
    Resizes the PQ
    */
    private void resize(int capacity) {
        Key[] copy = (Key[]) new Comparable[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = pq[i];
        }
        pq = copy;
    }
    
    /**
     * Deletes & returns the maximum element from the PQ
     * 
     * @return the max element in the PQ (This is removed from the PQ)
     */
    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow. PQ is empty.");
        }
        // scan the entire array
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (less(pq[max], pq[i])) {
                max = i;
            }
        }
        // exchange the maximum element with the last element
        // for efficient removal
        exch(max, N-1);
        Key key = pq[N-1];
        pq[N-1] = null;     // prevent loitering
        N--;    // update N
        // check if resizing is needed
        if (N == pq.length/4) {
            resize(pq.length/2);
        }
        return key;
    }
    
    /**
     * Deletes & returns the minimum element from the PQ
     * 
     * @return the min element in the PQ (This is removed from the PQ)
     */
    public Key delMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow. PQ is empty.");
        }
        // scan the entire array
        int min = 0;
        for (int i = 0; i < N; i++) {
            if (less(pq[i], pq[min])) {
                min = i;
            }
        }
        // exchange the maximum element with the last element
        // for efficient removal
        exch(min, N-1);
        Key key = pq[N-1];
        pq[N-1] = null;     // prevent loitering
        N--;    // update N
        // check if resizing is needed
        if (N == pq.length/4) {
            resize(pq.length/2);
        }
        return key;
    }
    
    /*
    Checks if an element is lesser than another
    */
    private boolean less(Comparable p, Comparable q) {
        return p.compareTo(q) < 0;
    }
    
    /*
    Exchange 2 elements in the PQ
    */
    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }
    
    /**
     * Gets the size of the PQ
     * 
     * @return the number of elements in the PQ
     */
    public int size() {
        return N;
    }
    
    /**
     * Check if the PQ is empty or not
     * 
     * @return: true, if empty; false if not
     */
    public boolean isEmpty() {
        return N == 0;
    }
    
    /*
    Iteration functionality
    */
    public Iterator<Key> iterator() {
        return new PQIterator();
    }
    
    private class PQIterator implements Iterator<Key> {
        private int current;
        
        public PQIterator() {
            this.current = N-1;    
        }
        
        public boolean hasNext() {
            return current >= 0;
        }
        
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Cannot iterate over empty PQ.");
            }
            return pq[current--];
        }
        
        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported in Iterator");
        }
    }
}