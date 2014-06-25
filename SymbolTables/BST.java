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
    
    /**
     * Is the BST empty?
     * 
     * @return true, if BST is empty; false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }
    
    /**
     * Return the minimum key in the BST
     * 
     * @return the min key in the BST
     */
    public Key min() {
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }
    
    /**
     * Return the maximum key in the BST
     * 
     * @return the max key in the BST
     */
    public Key max() {
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }
    
    /**
     * Compute the floor of a key
     *
     * @param key: key whose floor is needed
     * 
     * @return the largest key in the BST which is lesser than param: key
     */
    public Key floor(Key key) {
        Node x = floor(root, key);  // use recursive routine for computation
        if (x != null) {
            return x.key;
        }
        return null;
    }
    
    /*
    Recursive method to calculate floor of a key
    */
    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            // this is the floor
            return x;
        } else if (cmp < 0) {
            // floor has to be in left sub-tree
            return floor(x.left, key);
        } else {
            // floor is current node if not in right sub-tree
            Node tmp = floor(x.right, key);
            if (tmp == null) {
                return x;
            }
            return tmp;
        }
    }
    
    /**
     * Compute the ceilingf of a key
     *
     * @param key: key whose ceiling is needed
     * 
     * @return the smallest key in the BST which is larger than param: key
     */
    public Key ceil(Key key) {
        Node x = ceil(root, key);  // use recursive routine for computation
        if (x != null) {
            return x.key;
        }
        return null;
    }
    
    /*
    Recursive method to calculate ceiling of a key
    */
    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            // this is the floor
            return x;
        } else if (cmp > 0) {
            // ceiling has to be in right sub-tree
            return ceil(x.right, key);
        } else {
            // ceil is current node if not in left sub-tree
            Node tmp = ceil(x.left, key);
            if (tmp == null) {
                return x;
            }
            return tmp;
        }
    }
}