import java.lang.Comparable;

/**
 * An implementation of insertion sort to sort an array of comparables
 * 
 * O(n^2) compares and O(n^2) exchanges
 */
public class Insertion {
    /**
     * Sort an array of comparables
     * 
     * @param a: array to be sorted
     */
   public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
   }
   
   /**
    * Sort an array in a given range
    * 
    * @param a: array to be sorted
    * @param lo, hi: lower & upper bounds of range to sort in
    */
   public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo; j--) {
                if (less(a[j], a[j-1])) {
                    exch(a, j, j-1);
                } else {
                    break;  // element is in position
                }
            }
        }       
   }
   
   /*
   Check if an element is lesser than another
   */
   private static boolean less(Comparable p, Comparable q) {
       return p.compareTo(q) < 0;
   }
   
   /*
   Exchange 2 elements
   */
   private static void exch(Comparable[] a, int i, int j) {
       Comparable swap = a[i];
       a[i] = a[j];
       a[j] = swap;
   }
}