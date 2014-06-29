import java.lang.Comparable;
import java.lang.Iterable;

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
        private int count;  // number of elements rooted here
        
        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            this.count = 1;  // this is set in the put() method
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
        x.count = 1 + size(x.left) + size(x.right);  // update sub-tree counts
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
        Node x = min(root);
        if (x == null) {
            return null;
        }
        return x.key;
    }
    
    /*
    Find minimum key in BST
    */
    private Node min(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }
    
    /**
     * Return the maximum key in the BST
     * 
     * @return the max key in the BST
     */
    public Key max() {
        Node x = max(root);
        if (x == null) {
            return null;
        }
        return x.key;
    }
    
    /*
    Find maximum key in BST
    */
    private Node max(Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
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
    
    /**
     * Get number of elements in the ST
     * 
     * @return number of elements in ST
     */
    public int size() {
        return size(root);
    }
    
    /*
    Get subtree count of a node 
    */
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.count;
    } 
    
    /**
     * Get the number of keys < key
     * 
     * @param key: key whose rank is to be found
     * 
     * @return rank: the rank of param key
     */
    public int rank(Key key) {
        return rank(root, key);  // use recursive routine    
    }
    
    /*
    Recursive routine to calculate rank
    */
    private int rank(Node x, Key key) {
        if (x == null) {
            // reached end of tree, no key in BST is lesser than given param
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return size(x.left);
        } else if (cmp < 0) {
            return rank(x.left, key);
        } else {
            return 1 + size(x.left) + rank(x.right, key);
        }
    }
    
    /**
     * Get all keys in BST in sorted order
     * 
     * @return: an iterable over all keys in sorted order
     */
    public Iterable<Key> keys() {
        // use inorder traversal to get keys in sorted order
        Queue<Key> q = new Queue<Key>();    // queues are iterable data structures
        inorder(root, q);   // add keys to queue recursively
        return q;
    }
    
    /*
    Recursively create interable having keys in inorder
    */
    private void inorder(Node x, Queue<Key> q) {
        if (x == null) {
            return;
        }   
        inorder(x.left, q);     // left
        q.enqueue(x.key);       // root
        inorder(x.right, q);    // right
    }
    
    /*
    Recursively create interable having keys in preorder
    */
    private void preorder(Node x, Queue<Key> q) {
        if (x == null) {
            return;
        }   
        q.enqueue(x.key);       // root
        preorder(x.left, q);     // left
        preorder(x.right, q);    // right
    }
    
    /*
    Recursively create interable having keys in postorder
    */
    private void postorder(Node x, Queue<Key> q) {
        if (x == null) {
            return;
        }
        postorder(x.left, q);     // left
        postorder(x.right, q);    // right   
        q.enqueue(x.key);       // root
    }
    
    /**
     * Remove a key from the symbol table
     * Uses Hibbard deletion - this is asymmetric!!!!
     * 
     * @param key: key to be deleted
     */
    public void delete(Key key) {
        root = delete(root, key);
    }
    
    /*
    Recursive deletion routine
    */
    private Node delete(Node x, Key key) {
        if (x == null) {
            // key not found!
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            // delete this node!!
            if (x.right == null) {
                return x.left;  // return child
            }
            // use hibbard deletion
            // step 1: find next minimum and hold on to it
            Node t = min(x.right);
            // Step 2: Make min's right child point to deleteMin(x.right)
            t.right = deleteMin(x.right);
            t.left = x.left;
            // Step 3: replace link from x to t
            t.count = 1 + size(t.left) + size(t.right);
            return t;
        }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }
    
    /*
    Recursively find and delete the minimum element in a BST
    */
    private Node deleteMin(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left != null) {
            x.left = deleteMin(x.left);
        }
        else {
            return x.right;  // return right child 
        }
        // update counts
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }
}