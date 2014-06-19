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
       // Insert elements in position one-by-one
       for (int i = 0; i < a.length; i++) {
           for (int j = i; j > 0; j--) {
               if (less(a[j], a[j-1])) {
                   exch(a, j-1, j);
               } else {
                   break;   // element is in position
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