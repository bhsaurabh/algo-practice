import java.lang.Comparable;

/**
 * An implementation of merge sort
 * Stable, O(nlgn) time & O(n) extra space
 */
public class Merge {
    /**
     * Sorts an array of comparables
     * 
     * @param a: array to be sorted
     */
    public static void sort(Comparable[] a) {
        // create an auxilliary array
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
    }

    /**
     * Recursive sorting routine
     */
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        // check if we have something to sort
        if (hi <= lo) {
            return;
        }
        // cutoff to insertion sort for small arrays
        int CUTOFF = 7;
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        // sort recursively
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        // check if a merge is necessary or not
        if (less(a[mid], a[mid+1])) {
            // merge is not necessary
            return;
        }
        merge(a, aux, lo, mid, hi);
    }
    
    /**
     * Linear time merge routine
     * O(n) time & O(n) space
     */
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
       assert isSorted(a, lo, mid);
       assert isSorted(a, mid+1, hi);
       
       // Copy all elements into auxilliary array (O(n))
       for (int i = lo; i <= hi; i++) {
           aux[i] = a[i];
       }
       
       // maintain pointers to copy back to a
       int i = lo, j = mid+1;
       for (int k = lo; k <= hi; k++) {
           if (i > mid) {
               // i is out of range
               a[k] = aux[j++];
           } else if (j > hi) {
               // j is out of range...both cannot be out opf range simultaneously
               a[k] = aux[i++];
           } else if (less(aux[j], aux[i])) {
               a[k] = aux[j++];
           } else {
               // even if aux[i] == aux[j]; this maintains stability
               a[k] = aux[i++];
           }
       }
       
       assert isSorted(a, lo, hi);
    }
    
    /**
     * Check if an element is lesser than another
     */
    private static boolean less(Comparable p, Comparable q) {
        return p.compareTo(q) < 0;
    }
    
    /**
     * Check if an array is sorted in a given range
     */
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            if (less(a[i+1], a[i])) {
                return false;
            }
        }
        return true;
    }
}