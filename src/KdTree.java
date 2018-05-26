import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
    private Node root;
    private int size;
    private final static int DIM = 2;

    public KdTree() {
        this.root = null;
        this.size = 0;
    }

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D p) {
            this.p = p;
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (this.root == null) {
            this.root = new Node(p);
            size++;
        } else insert(this.root, p, 0);
    }

    private void insert(Node root, Point2D p, int level) {
        if (root.p != p) {
            int cmp = compare(root, p, level);
            if (cmp < 0) {
                if (root.lb == null) {
                    root.lb = new Node(p);
                    size++;
                } else insert(root.lb, p, level + 1);
            } else {
                if (root.rt == null) {
                    root.rt = new Node(p);
                    size++;
                } else insert(root.rt, p, level + 1);
            }
        }
    }

    private int compare(Node n, Point2D p, int level) {
        if (level % DIM == 0) {
            return Double.compare(n.p.x(), p.x());
        } else {
            return Double.compare(n.p.y(), p.y());
        }
    }


    public boolean contains(Point2D p) {
        return contains(this.root, p, 0) != null;
    }

    private Node contains(Node root, Point2D p, int level) {
        if (root == null || root.p == p) return root;
        else {
            Node nextNode;
            if (compare(root, p, level) < 0) {
                nextNode = root.lb;
            } else {
                nextNode = root.rt;
            }
            return contains(nextNode, p, level + 1);
        }
    }
//
//    public void draw() {
//        for (Point2D point : KDList) {
//            point.draw();
//        }
//    }

//    private void check (Point2D p) {
//        if (p == null) throw new IllegalArgumentException("input point is null");
//    }
//
//    public Iterable<Point2D> range(RectHV rect) {
//        ArrayList<Point2D> pointsIn = new ArrayList<>();
//        for (Point2D p : KDList) {
//            if (rect.contains(p)) pointsIn.add(p);
//        }
//        return pointsIn;
//    }
//
//    public Point2D nearest(Point2D p) {
//        double lowest = Double.POSITIVE_INFINITY;
//        Point2D closest = null;
//        for (Point2D point : KDList) {
//            double dist = p.distanceSquaredTo(point);
//            if (dist < lowest) {
//                closest = point;
//                lowest = dist;
//            }
//        }
//        return closest;
//    }

    public static void main(String[] args) {
        KdTree test = new KdTree();
        System.out.println(test.isEmpty());
        System.out.println(test.size());
        Point2D testPoint = new Point2D(0.5, 0.5);
        test.insert(testPoint);
        System.out.println(test.isEmpty());
        System.out.println(test.size());
        test.insert(testPoint);
        Point2D testPoint2 = new Point2D(0.3, 0.3);
        Point2D testPoint3 = new Point2D(0.7, 0.8);
        Point2D testPoint4 = new Point2D(0.5, 0.9);
        Point2D testPoint5 = new Point2D(0.5, 0.85);
        test.insert(testPoint2);
        test.insert(testPoint3);
        test.insert(testPoint4);
        System.out.println(test.isEmpty());
        System.out.println(test.size());
        System.out.println(test.contains(testPoint2));
        System.out.println(test.contains(testPoint5));
    }
}