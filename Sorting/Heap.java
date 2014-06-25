import java.lang.Comparable;

/**
 * Sorts an array using heap sort
 */
public class Heap {
    /**
     * Sorts an array using heap sort
     * 
     * @param a: array to be sorted
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        // make a max-heap out of the array O(2N) = O(N)
        for (int i = N/2; i >= 1; i--) {
            // start from N/2 as i>N/2 are small heaps of size 1 (leaf nodes)
            sink(a, i, N);
        }
        // repeatedly, put maximum element in correct position O(2NlgN) = O(NlgN)
        while (N > 1) {
            exch(a, 1, N--);    // put max element out of heap
            sink(a, 1, N);
        }
    }
    
    /*
    Maintain heap order, by demoting elements to correct position
    */
    private static void sink(Comparable[] a, int i, int N) {
        while (2*i <= N) {
            int j = 2*i;
            if ((j < N) && less(a, j, j+1)) {
                j++;    // make j point to larger of the children
            }
            if (!less(a, i, j)) {
                break;
            }
            exch(a, i, j);  // exchange
            i = j;
        }    
    }
    
    /*
    Checks if an element is lesser than another
    */
    private static boolean less(Comparable[] a, int i, int j) {
        return a[i-1].compareTo(a[j-1]) < 0;    // as array starts @ 0, but heap @ 1
    }
    
    /*
    Exchange 2 elements
    */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = swap;
    }
}