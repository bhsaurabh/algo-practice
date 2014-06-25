public class STTest {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<Integer, String>();
        // insertions
        System.out.println("Inserting elements...");
        bst.put(1, "one");
        bst.put(2, "two");
        bst.put(3, "three");
        bst.put(4, "four");
        bst.put(5, "five");
        // retrieval
        System.out.println("1 : " + bst.get(1));
        System.out.println("4 : " + bst.get(4));
        // update
        System.out.println("Checking updation of values...");
        System.out.println("3 : " + bst.get(3));
        bst.put(3, "three new");
        System.out.println("3 : " + bst.get(3));
        // get min
        System.out.println("Min: " + bst.min());
        // get max
        System.out.println("Max: " + bst.max());
        // floor
        System.out.println("Floor of 3: " + bst.floor(3));
        System.out.println("Floor of 6: " + bst.floor(6));
        System.out.println("Floor of 7: " + bst.floor(7));
        System.out.println("Floor of 0: " + bst.floor(0));
        // ceiling
        System.out.println("Ceiling of 3: " + bst.ceil(3));
        System.out.println("Ceiling of 6: " + bst.ceil(6));
        System.out.println("Ceiling of 1: " + bst.ceil(1));
        System.out.println("Ceiling of 0: " + bst.ceil(0));
    }
}