import java.lang.Comparable;
import java.lang.Iterable;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Binary heap representation of a max-pq
 */
public final class MinPQ<Key extends Comparable<Key>> implements Iterable<Key> {
    // use immutable keys, clients can not change thtm when on heap
    private Key[] pq;
    private int N;
    
    /**
     * Constructor initialises PQ data structure
     */
    public MinPQ() {
        this.pq = (Key[]) new Comparable[1+1];  // start indexing from 1
        this.N = 0;
    }
    
    /**
     * Check if the PQ is empty
     * 
     * @return true, if empty; false if not
     */
    public boolean isEmpty() {
        return N == 0;
    }
    
    /**
     * Returns the number of elements on the PQ
     * 
     * @return the size of the PQ
     */
    public int size() {
        return N;
    }
    
    /*
    Resizes the PQ
    */
    private void resize(int capacity) {
        Key[] copy = (Key[]) new Comparable[capacity];
        for (int i = 0; i <= N; i++) {
            copy[i] = pq[i];
        }
        pq = copy;
    }
    
    /**
     * Insert an item into the PQ
     * 
     * @param key: item to be inserted
     */
    public void insert(Key key) {
        if (N+1 == pq.length) {
            resize(pq.length * 2);
        }
        pq[++N] = key;
        swim(N);    // maintain heap order
    }
    
    /*
    Swim an element up in the heap to maintain heap-order
    */
    private void swim(int k) {
        // cannot make root swim up
        while (k > 1 && less(k, k/2)) {
            exch(k, k/2);
            k = k/2;
        }
    }
    
    /**
     * Deletes the maximum element from the PQ and returns it
     * 
     * @return the max element in the PQ (this element is deleted!)
     */
    public Key delMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow, PQ is empty.\n");
        }
        // exchange max element with last element
        Key min = pq[1];
        exch(1, N--);
        sink(1);    // maintain heap-order
        pq[N+1] = null;  // prevent loitering
        if (N == pq.length / 4) {
            resize(pq.length / 2);
        }
        return min;
    }
    
    /*
    Sink an element down the heap for heap-ordering
    */
    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && !less(j, j+1)) {
                j++;    // make j point to smaller child
            }
            if (less(k, j)) {
                break;  // no need to continue
            }
            exch(k, j);
            k = j;
        }
    }
    
    /*
    Check if an element is lesser than another
    */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }
    
    /*
    Exchanges 2 elements
    */
    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }
    
    /*
    Iteration functionality
    */
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }
    
    private class HeapIterator implements Iterator<Key> {
        private int current;
        
        public HeapIterator() {
            this.current = N;
        }
        
        public boolean hasNext() {
            return current > 0;
        }
        
        public Key next() {
            return pq[current--];
        }
        
        public void remove() {
            throw new UnsupportedOperationException("remove() not supported for iterators.");
        }
    }
}