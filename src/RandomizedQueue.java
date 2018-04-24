import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int n;
    private Item[] a;

    public RandomizedQueue() {// construct an empty randomized queue
        a = (Item[]) new Object[2];
        n = 0;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {
        int[] rndIdx = new int[n];
        int current = 0;

        RandomizedQueueIterator() {
            for (int i = 0; i < n; i++) {
                rndIdx[i] = i;
            }
            StdRandom.shuffle(rndIdx);
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return (Item) a[rndIdx[current++]];
        }

        public boolean hasNext() {
            return current < n;
        }

        public void remove() {
            throw new UnsupportedOperationException("no no no");
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void rescaleArray(int newSize) {
        Item[] newArray = (Item[]) new Object[newSize];
        for (int i = 0; i < n; i++) {
            newArray[i] = a[i];
        }
        a = newArray;
    }

    private void rescaleIfNeeded() {
        if (n == a.length) {
            rescaleArray(a.length * 2);
        }
        if (n > 0 && n == a.length / 4) {
            rescaleArray(a.length / 2);
        }

    }

    public void enqueue(Item item) {
        checkNull(item);
        rescaleIfNeeded();
        a[n++] = item;
    }

    public Item dequeue() {
        checkUnderflow();
        rescaleIfNeeded();
        int rnd = StdRandom.uniform(n);
        Item val = a[rnd];
        a[rnd] = a[--n];
        a[n] = null; // avoid loitering
        return val;
    }

    public Item sample() {
        checkUnderflow();
        int rnd = StdRandom.uniform(n);
        return a[rnd];
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rque = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) rque.enqueue(item);
            else StdOut.print(rque.dequeue() + " ");
        }
        StdOut.println("(" + rque.size() + " left on stack)");
    }

    private void checkUnderflow() {
        if (isEmpty()) throw new NoSuchElementException("deque underflow");
    }

    private void checkNull(Item item) {
        if (item == null) throw new IllegalArgumentException("no nulls in my queueueue");
    }


//    public void enqueue(Item item)           // add the item
//    public Item dequeue()                    // remove and return a random item
//    public Item sample()                     // return a random item (but do not remove it)
//    public Iterator<Item> iterator()         // return an independent iterator over items in random order
//    public static void main(String[] args)   // unit testing (optional)
}
