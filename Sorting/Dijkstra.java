import java.lang.Comparable;

/**
 * Dijkstra's 3-way quick sort
 */
public class Dijkstra {
    /**
     * 3-way quick sort
     * 
     * @param a: array to be sorted
     */
    public static void sort(Comparable[] a) {
        Knuth.shuffle(a);
        sort(a, 0, a.length-1);
    }
    
    /*
    Recursive sorting routine
    */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;  // nothing to do
        }
        int lt = lo, gt = hi;
        int i = lo;
        Comparable v = a[lo];
        // iterate till pointers cross
        while (i <= gt) {
            if (less(a[i], v)) {
                exch(a, i++, lt++);
            } else if (less(v, a[i])) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }
        
        // sort sub-arrays recursively
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }
    
    /*
    Check if an element is lesser than another
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