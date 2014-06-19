import java.lang.Iterable;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * A stack data structure
 * Uses linked lists in the background
 * O(1) time guarantee for supported API
 */
public class StackLL<Item> implements Iterable<Item> {
    /**
     *Inner class representation of a LL Node
     */
    private class Node {
        Item item;
        Node next;
    }
    
    private Node first;  // pointer to stack's top
    
    /**
     * Constructor initialises the stack
     */
    public StackLL() {
        this.first = null;
    }
    
    /**
     * Push an element onto the stack
     * O(1)
     * 
     * @param item: Element to be pushed onto stack
     */
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    
    /**
     * Remove and return an element from the top of the stack
     * O(1)
     * 
     * @returns: Item on stack's top
     */
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow. Stack is empty.");
        }
        Item item = first.item;
        first = first.next;
        return item;
    }
    
    /**
     * Returns the item on the top of the stack.
     * Does not delete it.
     * O(1)
     * 
     * @return: Item on stack's top
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow. Stack is empty.");
        }
        return first.item;
    }
    
    /**
     * Checks if the stack is empty or not
     * 
     * @return: true, if stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }
    
    /**
     * Support iteration
     */
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }
    
    private class LinkedListIterator implements Iterator<Item> {
        private Node current;
        
        public LinkedListIterator() {
            this.current = first;
        }
        
        public boolean hasNext() {
            return current != null;
        }
        
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Underflow. Stack is empty.");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
        
        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported");
        }
    }
}