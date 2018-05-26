import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class KdTree {
    private Node root;
    private int size;
    private final static int DIM = 2;
    private final static RectHV BOX = new RectHV(0, 0, 1, 1);

    public KdTree() {
        this.root = null;
        this.size = 0;
    }

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
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
            this.root = new Node(p, BOX);
            size++;
        } else insert(this.root, p, 0);
    }
//TODO fix rectangels in nodes
    private void insert(Node root, Point2D p, int level) {
        if (root.p != p) {
            int cmp = compare(root, p, level);
            if (cmp < 0) {
                if (root.lb == null) {
                    root.lb = new Node(p, getRect(root.rect, p, level, true));
                    size++;
                } else insert(root.lb, p, level + 1);
            } else {
                if (root.rt == null) {
                    root.rt = new Node(p, getRect(root.rect, p, level, false));
                    size++;
                } else insert(root.rt, p, level + 1);
            }
        }
    }

    private int compare(Node n, Point2D p, int level) {
        if (level % DIM == 0) {
            return Double.compare(p.x(), n.p.x());
        } else {
            return Double.compare(p.y(), n.p.y());
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

    public void draw() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius();
        BOX.draw();

        draw(this.root, BOX, 0);
    }

    private void draw(Node root, RectHV box, int level) {
        if (root == null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.02);
        root.p.draw();

        Point2D min, max;
        if (level % DIM == 0) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.setPenRadius(0.01);
            min = new Point2D(root.p.x(), box.ymin());
            max = new Point2D(root.p.x(), box.ymax());
        } else {
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.setPenRadius(0.01);
            min = new Point2D(box.xmin(), root.p.y());
            max = new Point2D(box.xmax(), root.p.y());
        }
        min.drawTo(max);

        int nextLevel = level + 1;
        draw(root.lb, getRect(box, root.p, level, true), nextLevel);
        draw(root.rt, getRect(box, root.p, level, false), nextLevel);

    }

    private RectHV getRect(RectHV box, Point2D p, int level, boolean isLeft) {
        RectHV resultBox;
        if (isLeft) {
            if (level % DIM == 0) {
                resultBox = new RectHV(box.xmin(), box.ymin(), p.x(), box.ymax());
            } else {

                resultBox = new RectHV(box.xmin(), box.ymin(), box.xmax(), p.y());
            }
        } else {
            if (level % DIM == 0) {
                resultBox = new RectHV(p.x(), box.ymin(), box.xmax(), box.ymax());
            } else {
                resultBox = new RectHV(box.xmin(), p.y(), box.xmax(), box.ymax());
            }
        }
        return resultBox;
    }

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
        Point2D testPoint4 = new Point2D(0.4, 0.9);
        Point2D testPoint5 = new Point2D(0.5, 0.85);
        Point2D testPoint6 = new Point2D(0.45, 0.6);
        test.insert(testPoint2);
        test.insert(testPoint3);
        test.insert(testPoint4);
//        System.out.println(test.isEmpty());
//        System.out.println(test.size());
//        System.out.println(test.contains(testPoint2));
//        System.out.println(test.contains(testPoint5));
        test.insert(testPoint6);
        test.draw();
    }
}