import java.lang.Comparable;
import java.util.Random;

/**
 * An implementation of Knuth's linear time shuffle
 */
public class Knuth {
    /**
     * Shuffles an array in a uniformly random way
     * 
     * @param a: array to be shuffled
     */
    public static void shuffle(Comparable[] a) {
        Random rand = new Random();
        for (int i = 0; i < a.length; i++) {
            // generate a random number in [0, i]
            int r = rand.nextInt(i+1);
            // exchange elements at positions r and i
            exch(a, r, i);
        } 
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