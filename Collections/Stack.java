import java.lang.Iterable;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.ConcurrentModificationException;

/**
 * A stack data structure
 * Uses resizing arrays in the background
 * O(1) amortized time guarantee for supported API
 */
public class Stack<Item> implements Iterable<Item> {
    private Item[] s;
    private int N;  // location of next insertion
    private int push_count, pop_count;  // number of pushes/pops performed

    /**
     * Constructor initialises the stack
     */
    public Stack() {
        this.s = (Item[]) new Object[1];  // cannot create arrays of generics
        this.push_count = 0;
        this.pop_count = 0;
    }
    
    /*
    Resizes internal stack array
    */
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }
    
    /**
     * Push an element onto the stack
     * O(1) amortized
     * 
     * @param item: Element to be pushed onto stack
     */
    public void push(Item item) {
        if (N == s.length) {
            resize(s.length * 2);
        }
        s[N++] = item;
        push_count++;
    }
    
    /**
     * Remove and return an element from the top of the stack
     * O(1) amortized
     * 
     * @returns: Item on stack's top
     */
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Underflow. Stack is empty.");
        }
        Item item = s[--N];
        s[N] = null;    // prevent loitering
        if (N == s.length/4) {
            resize(s.length/2);
        }
        pop_count++;
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
        return s[N-1];
    }
    
    /**
     * Checks if the stack is empty or not
     * 
     * @return: true, if stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return N == 0;
    }
    
    /**
     * Supports iteration
     */
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }
    
    private class ReverseArrayIterator implements Iterator<Item> {
        private int current;
        private int pushes, pops;
        
        public ReverseArrayIterator() {
            this.current = N;
            // store the number of pushes and pops for fail fast iteration
            pushes = push_count;
            pops = pop_count;
        }
        
        public boolean hasNext() {
            if ((pushes != push_count) || (pops != pop_count)) {
                throw new ConcurrentModificationException("Stack modified during interation.");
            }
            return current > 0;
        }
        
        public Item next() {
            if ((pushes != push_count) || (pops != pop_count)) {
                throw new ConcurrentModificationException("Stack modified during interation.");
            }
            if (!hasNext()) {
                throw new NoSuchElementException("Underflow. Stack is empty.");
            }
            return s[--current];
        }
        
        public void remove() {
            throw new UnsupportedOperationException("remove() is not supported");
        }
    }
}