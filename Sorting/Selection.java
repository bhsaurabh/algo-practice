import java.lang.Comparable;

/**
 * An implementation of the selection sort algorithm
 * O(N^2) compares; O(N) exchanges
 */
public class Selection {
    
    /**
     * Sort an array of comparables
     * 
     * @params a: array of comparables to be sorted
     */
    public static void sort(Comparable[] a) {
        // repeatedly find minimum element, and insert it in position
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    // found a new minimum, update records
                    min = j;
                }
            }
            exch(a, min, i);    // insert minimum element in position
        }      
    }
    
    /*
    Check if a comparable is less than another
    */
    private static boolean less(Comparable p, Comparable q) {
        return p.compareTo(q) < 0;
    }
    
    /*
    Exchange 2 values in an array
    */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}