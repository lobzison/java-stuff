import edu.princeton.cs.algs4.LinkedStack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;

    private class Node {
        private Item value;
        private Node next;
        private Node previous;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.value;
            current = current.next;
            return item;
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }


    //    public Deque()                           // construct an empty deque
//    public boolean isEmpty()                 // is the deque empty?
//    public int size()                        // return the number of items on the deque
//    public void addFirst(Item item)          // add the item to the front
//    public void addLast(Item item)           // add the item to the end
//    public Item removeFirst()                // remove and return the item from the front
//    public Item removeLast()                 // remove and return the item from the end
//    public Iterator<Item> iterator()         // return an iterator over items in order from front to end

    public static void main(String[] args) { // unit testing (optional)
        int n = 1;
    }

}
