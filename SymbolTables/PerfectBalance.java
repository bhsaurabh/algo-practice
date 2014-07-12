import java.util.Arrays;

/**
 * Builds a perfectly balanced BST order
 */
public class PerfectBalance <Key extends Comparable<Key>, Value> {
    public static BST<Key, Value> getBST(Key[] keys, Value[] vals) {
        // sort keys -> we will return a blank BST
        // make a keys copy and sort that
        Key[] keys_copy = (Key[])new Comparable[keys.length];
        Arrays.sort(keys_copy);
        BST<Key, Value> bst = new BST<Key, Value>();
        insert(bst, keys_copy, 0, keys_copy.length-1);
        return bst;
    }
    
    private void insert(BST<Key, Value> bst, Key[] keys, int lo, int hi) {
        if (lo == hi) {
            // only 1 key
            bst.put(keys[lo], null);
        } else if (lo > hi) {
            // no key remaining
            return; // base case
        } else {
            int mid = lo + (hi - lo) / 2;
            bst.put(keys[mid], null);
            insert(bst, keys, lo, mid-1);   // recurse on left half
            insert(bst, keys, mid+1, hi);   // recurse on right half
        }
    }
}