import java.util.Iterator;
import java.util.NoSuchElementException;

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
        int[] nonEmpty;
        int k = 0;

        RandomizedQueueIterator() {
            nonEmpty = new int[n];
            int j = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] != null) {
                    nonEmpty[++j] = i;
                }
            }
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return (Item) a[nonEmpty[++k]];
        }

        public boolean hasNext() {
            return k < n;
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
        rescaleIfNeeded();
        int rnd = n;
        while (a[rnd] != null) {
            rnd = StdRandom.uniform(a.length);
        }
        a[rnd] = item;
        n++;
        System.out.println("items: "+Integer.toString(n) +" array size:"+ Integer.toString(a.length));
    }
// TODO: fix the bug with last item
    public Item dequeue() {
        chechUnderflow();
        rescaleIfNeeded();
        int rnd = n;
        while (a[rnd] == null) {
            rnd = StdRandom.uniform(a.length);
        }
        Item val = a[rnd];
        a[rnd] = null;
        n--;
        System.out.println("items: "+Integer.toString(n) +" array size:"+ Integer.toString(a.length));
        return val;
    }

    public Item sample() {
        int rnd = n;
        while (a[rnd] == null) {
            rnd = StdRandom.uniform(a.length);
        }
        return a[rnd];
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rque = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) rque.enqueue(item);
            else if (!rque.isEmpty()) StdOut.print(rque.dequeue() + " ");
        }
        StdOut.println("(" + rque.size() + " left on stack)");
    }

    private void chechUnderflow() {
        if (isEmpty()) throw new NoSuchElementException("deque underflow");
    }


//    public void enqueue(Item item)           // add the item
//    public Item dequeue()                    // remove and return a random item
//    public Item sample()                     // return a random item (but do not remove it)
//    public Iterator<Item> iterator()         // return an independent iterator over items in random order
//    public static void main(String[] args)   // unit testing (optional)
}
