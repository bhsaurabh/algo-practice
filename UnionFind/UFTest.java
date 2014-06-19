import java.util.Scanner;
public class UFTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        while (sc.hasNextInt()) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                System.out.println(p + " and " + q + " have been connected.");
            } else {
                System.out.println(p + " and " + q + " are already connected.");
            }
        }
        
    }
}