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

    public Deque() {
        n = 0;
        first = null;
        last = null;
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

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public boolean isEmpty() { // is the deque empty?
        return n == 0;
    }

    public int size() { // return the number of items on the deque
        return n;
    }

    public void addFirst(Item item) {// add the item to the front
        checkNulls(item);
        Node oldfirst = first;
        first = new Node();
        first.value = item;
        if (n == 0) {
            last = first;
        }
        if (oldfirst != null) {
            oldfirst.previous = first;
        }
        first.next = oldfirst;
        n++;
    }

    public void addLast(Item item) {// add the item to the end
        checkNulls(item);
        Node oldlast = last;
        last = new Node();
        last.value = item;
        if (n == 0) {
            first = last;
        }
        if (oldlast != null) {
            oldlast.next = last;
        }
        last.previous = oldlast;
        n++;
    }

    public Item removeFirst() {// remove and return the item from the front
        checkUnderflow();
        Item item = first.value;
        first.previous = null;
        if (first.next != null) {
            first = first.next;
        }
        resetPointers();
        n--;
        return item;
    }

    public Item removeLast() {// remove and return the item from the end
        checkUnderflow();
        Item item = last.value;
        last.next = null;
        if (last.previous != null) {
            last = last.previous;
        }
        resetPointers();
        n--;
        return item;
    }

    private void resetPointers() {
        if (n == 1) {
            first = null;
            last = null;
        }
    }

    private void checkUnderflow() {
        if (isEmpty()) throw new NoSuchElementException("deque underflow");
    }

    private void checkNulls(Item item) {
        if (item == null) throw new IllegalArgumentException("no no no, i clean");
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            switch (item) {
                case "#":
                    StdOut.print(deque.removeFirst() + ' ');
                    break;
                case "@":
                    StdOut.print(deque.removeLast() + ' ');
                    break;
                case "addfirst":
                    deque.addFirst(item);
                    break;
                case "addlast":
                    deque.addLast(item);
                    break;
            }
        }
        StdOut.println("(" + deque.size() + " left on deque)");
    }

}
