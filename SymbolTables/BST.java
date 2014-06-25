import java.lang.Comparable;

/**
 * A binary search tree implementation of a symbol table
 */
public class BST<Key extends Comparable<Key>, Value> {
    /**
     * Inner class representation of a Node
     */
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        
        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }
    
    private Node root;  // representation of BST
    
    /**
     * Constructor initialises the BST
     */
    public BST() {
        this.root = null;   
    }
    
    /**
     * Get the value associated with a particular key
     * 
     * @param key: the key to be looked up
     * 
     * @return: value associated with key, null if key not found
     */
    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                // key is in left sub-tree
                x = x.left;
            } else if (cmp > 0) {
                // key is in right sub-tree
                x = x.right;
            } else {
                // key is found @ current node
                return x.val;
            }
        }
        return null;    // key not found
    }
    
    /**
     * Insert a key-value pair into the ST or update a key-value pair
     * 
     * @param key, value: the key value pair to be inserted/updated
     */
    public void put(Key key, Value val) {
        // use recursive routine to update root pointer
        root = put(root, key, val);
    }
    
    /*
    Recursive routine to insert K-V pair
    */
    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            // this is the Node to be updated
            x.val = val;
        }
        return x;   // return a link for the parent's use
    }
}