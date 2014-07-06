import java.lang.Comparable;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    // color definitions
    private final boolean RED = true;
    private final boolean BLACK = false;
    
    // new node definition holds color of incoming link
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int count;
        private boolean color;
        
        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            this.count = 1;
            this.color = RED;   // always add a 3-node
        }
    }
    
    /*
    Is a node's incoming link RED??
    */
    private boolean isRed(Node x) {
        if (x == null) {
            return false;   // no dangling red links
        }    
        return x.color == RED;
    }
    
    /**
     * Get the value associated with a key
     * 
     * @param key: whose corresponding value is needed
     * 
     * @return val: value corresponding to key
     */
    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                // check left sub-tree
                x = x.left;
            } else if (cmp > 0) {
                // check right sub-tree
                x = x.right;
            } else {
                // found node
                return x.val;
            }
        }
        return null;    // not found
    }
    
    /**
     * Necessary rotations for BST balancing
     */
    
    private Node rotateLeft(Node t) {
        assert isRed(t.right);
        Node x = t.right;
        t.right = x.left;
        x.left = t;
        x.color = t.color;
        t.color = RED;
        return x;
    }
    
    private Node rotateRight(Node t) {
        assert isRed(t.left);
        Node x = t.left;
        t.left = x.right;
        x.right = t;
        x.color = t.color;
        t.color = RED;
        return x;
    }
    
    private void flipColors(Node t) {
        // split a temporary 4-node
        assert !isRed(t);
        assert isRed(t.left);
        assert isRed(t.right);
        t.color = RED;
        t.left.color = BLACK;
        t.right.color = BLACK;
    }
    // --------------- End of rotation operations -------------------- //
    
    
}