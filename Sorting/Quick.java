import java.lang.Comparable;

/**
 * An implementation of the classic quick sort algorithm
 * Worst case O(n^2), generally & most probably O(nlgn)
 * In place sorting algorithm
 * Performs poorly when large number of equal keys are present
 */
public class Quick {
    /**
     * Sort an array of comparables using quicksort
     * 
     * @param a: array to be sorted
     */
    public static void sort(Comparable[] a) {
         Knuth.shuffle(a);  // shuffle for probablistic guarantee
         sort(a, 0, a.length-1);
         // complete the sort with an insertion sort pass (partially sorted array here)
         Insertion.sort(a, 0, a.length-1);
    }
    
    /*
    Recursive sorting routine
    */
    private static void sort(Comparable[] a, int lo, int hi) {
        int CUTOFF = 10;
        if (hi <= lo + CUTOFF - 1) {
            return;  // pick smaller arrays later with 1 final insertion sort pass
        }
        
        // partition
        int p = partition(a, lo, hi);
        // recursively sort sub-arrays
        sort(a, lo, p-1);
        sort(a, p+1, hi);
    }
    
    /*
    Partitioning routine
    */
    private static int partition(Comparable[] a, int lo, int hi) {
        // choose a[lo] as pivot element
        int i = lo, j = hi + 1;
        while (true) {
            // increment i till larger element encountered
            while (less(a[++i], a[lo])) {
                if (i >= hi) {
                    break;  // stay in bounds
                }
            }
            
            // decrement j till smaller element encountered
            while (less(a[lo], a[--j])) {
                if (j <= lo) {
                    break;
                }
            }
            
            // check if pointers have crossed\
            if (j <= i) {
                break;
            }
            exch(a, i, j);
        }
        // j is in final position of pivot
        exch(a, lo, j);
        return j;
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