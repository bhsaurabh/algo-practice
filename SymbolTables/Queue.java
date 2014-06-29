import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a Queue data structure
 * Uses Linked List of Nodes internally
 * O(1) guarantee for API
 */
public class Queue<Item> implements Iterable<Item> {
    /**
     * Inner class representation of a LL Node
     */
    private class Node {
        Item item;
        Node next;
    }
    
    private Node first, last;   // track queue elements
    
    /**
     * Constructor initialises the Queue
     */
    public Queue() {
        this.first = null;
        this.last = null;
    }
    
    /**
     * Insert an element into the queue (last position)
     * 
     * @param item: Item to be inserted
     */
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
    }
    
    /**
     * Removes & Returns first element from the queue
     * 
     * @return: the first item in queue
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow. Queue is empty.");
        }
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }
    
    /**
     * Check if the queue is empty or not
     */
    public boolean isEmpty() {
        return first == null;
    }
    
    /**
     * Iteration support
     */
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<Item> {
        private Node current, limit;
        
        public QueueIterator() {
            current = first;
            limit = last.next; 
        }
        
        public boolean hasNext() {
            return current != limit;
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