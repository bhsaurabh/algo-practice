import java.util.Scanner;

/**
 * A test client for MinPQ
 * Keeps track of the top-M entries in a stream of data
 */
public class TopM {
    public static void main(String[] args) {
        MinPQ<Integer> pq = new MinPQ<Integer>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter M: ");
        int M = sc.nextInt();
        System.out.println("Enter data: ");
        while (sc.hasNextInt()) {
            int a = sc.nextInt();
            pq.insert(a);
            if (pq.size() > M) {
                pq.delMin();    // remove minimum elements to keep track of top-M
            }
        }
        // Iterate over the PQ
        System.out.println("Top " + M + " values are: ");
        for (int num : pq) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}