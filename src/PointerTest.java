public class PointerTest {
    private Node test;

    public PointerTest() {
        test = new Node(10, 15);
        testFunc(test);
    }

    public static void main(String[] args) {
        PointerTest test = new PointerTest();
        System.out.println(test);

    }

    private class Node {
        private int a = 1;
        private int b = 2;

        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.test.a) + " " + String.valueOf(this.test.b);
    }

    private void testFunc (Node n) {
        Node p = n;
        p = new Node(100, 200);
    }

}
