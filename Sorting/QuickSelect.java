import java.lang.Comparable;

/**
 * Order statistics implementation
 * Can be used to find top k elements
 * O(2N) = O(N)
 */
public class QuickSelect {
    /**
     * Select the kth largest element (from left in sorted format)
     * 
     * @param a: array of comparable to find element from
     * @param k: rank of element to be found
     *
     * @return el: the element whose rank is k
     */
    public static Comparable select(Comparable[] a, int k) {
        // shuffle the array for probablistic guarantee
        Knuth.shuffle(a);
        // call selection routine
        return select(a, k, 0, a.length-1);
    }
    
    /*
    Quick select routine
    */
    private static Comparable select(Comparable[] a, int k, int lo, int hi) {
        boolean give_top_elems = false;  // give top k elements
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j == k) {
                // element found!
                if (give_top_elems) {
                    for (int i = j; i < a.length; i++) {
                        System.out.print(a[i] + " ");
                    }
                    System.out.println();
                }
                return a[j];
            } else if (k < j) {
                // select in left half only
                hi = j - 1;
            } else {
                // select in right half only
                lo = j + 1;
            }
        }
        return a[k];
    }
    
    /*
    Partitioning routine
    */
    private static int partition(Comparable[] a, int lo, int hi) {
        // use a[lo] as pivot element
        int i = lo, j = hi + 1;
        while (true) {
            // increment i till larger elemt found
            while (less(a[++i], a[lo])) {
                if (i == hi) {
                    break;  // keep in bounds
                }
            }
            
            // decrement j till smaller element found
            while (less(a[lo], a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            
            // check if pointers cross
            if (i >= j) {
                break;
            }
            
            exch(a, i, j);
        }
        // j is at pivot's position
        exch(a, j, lo);
        return j;
    }
    
    /* 
    Compare 2 elements
    */
    private static boolean less(Comparable p, Comparable q) {
        return p.compareTo(q) < 0;
    }
    
    /*
    Exchange 2 elements in an array
    */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}