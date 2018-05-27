import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;


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
        check(p);
        if (this.root == null) {
            this.root = new Node(p, BOX);
            size++;
        } else insert(this.root, p, 0);
    }

    private void insert(Node root, Point2D p, int level) {
        if (root.p.compareTo(p) != 0) {
            int cmp = compare(root, p, level);
            if (cmp < 0) {
                if (root.lb == null) {
                    root.lb = new Node(p, getRect(root.rect, root.p, level, true));
                    size++;
                } else insert(root.lb, p, level + 1);
            } else {
                if (root.rt == null) {
                    root.rt = new Node(p, getRect(root.rect, root.p, level, false));
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
        check(p);
        return contains(this.root, p, 0) != null;
    }

    private Node contains(Node root, Point2D p, int level) {
        if (root == null || root.p.compareTo(p) == 0) return root;
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
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.01);
            min = new Point2D(root.p.x(), box.ymin());
            max = new Point2D(root.p.x(), box.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
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
//        System.out.println("box: "+box+ " point: "+p+"level"+ isLeft);
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

    private void check(Point2D p) {
        if (p == null) throw new IllegalArgumentException("input point is null");
    }

    private void check(RectHV p) {
        if (p == null) throw new IllegalArgumentException("input point is null");
    }

    public Iterable<Point2D> range(RectHV rect) {
        check(rect);
        ArrayList<Point2D> pointsIn = new ArrayList<>();
        range(this.root, rect, pointsIn);
        return pointsIn;
    }

    private void range(Node root, RectHV rect, ArrayList<Point2D> list) {
        if (root == null) return;
        if (rect.intersects(root.rect)) {
            if (rect.contains(root.p)) list.add(root.p);
            range(root.lb, rect, list);
            range(root.rt, rect, list);
        }
    }

    public Point2D nearest(Point2D p) {
        check(p);
        double lowest = Double.POSITIVE_INFINITY;
        double[] low = new double[1];
        low[0] = lowest;
        Point2D[] closest = new Point2D[1];
        nearest(this.root, p, low, closest, 0);
        return closest[0];
    }

    private void nearest(Node root, Point2D p, double[] dist, Point2D[] closest, int level) {
        if (root == null) return;
        if (root.rect.distanceSquaredTo(p) > dist[0]) return;
        double newDist = root.p.distanceSquaredTo(p);
//        System.out.println("distance in: "+ dist[0] + " new distance: "+newDist);
        if (newDist < dist[0]) {
            dist[0] = newDist;
            closest[0] = root.p;
        }
//        System.out.println(dist[0]);
        int nextLevel = level + 1;
        if (level % DIM == 0) {
            if (p.x() < root.p.x()) {
                nearest(root.lb, p, dist, closest, nextLevel);
                nearest(root.rt, p, dist, closest, nextLevel);
            } else {
                nearest(root.rt, p, dist, closest, nextLevel);
                nearest(root.lb, p, dist, closest, nextLevel);
            }
        } else {
            if (p.y() < root.p.y()) {
                nearest(root.lb, p, dist, closest, nextLevel);
                nearest(root.rt, p, dist, closest, nextLevel);
            } else {
                nearest(root.rt, p, dist, closest, nextLevel);
                nearest(root.lb, p, dist, closest, nextLevel);
            }
        }
    }

    public static void main(String[] args) {
        KdTree test = new KdTree();
        System.out.println(test.isEmpty());
        System.out.println(test.size());
        Point2D testPoint = new Point2D(0.7, 0.2);
        test.insert(testPoint);
        System.out.println(test.isEmpty());
        System.out.println(test.size());
        test.insert(testPoint);
        System.out.println(test.isEmpty());
        System.out.println(test.size());

        Point2D testPoint2 = new Point2D(0.5, 0.4);
        Point2D testPoint3 = new Point2D(0.2, 0.3);
        Point2D testPoint4 = new Point2D(0.4, 0.7);
        Point2D testPoint5 = new Point2D(0.9, 0.6);
        test.insert(testPoint2);
        System.out.println(test.isEmpty());
        System.out.println(test.size());
        test.insert(testPoint3);
        System.out.println(test.isEmpty());
        System.out.println(test.size());
        test.insert(testPoint4);
        test.insert(testPoint5);
        System.out.println(test.isEmpty());
        System.out.println(test.size());
        Point2D testPoint6 = new Point2D(0.454, 0.606);


        System.out.println(test.nearest(testPoint6));
//        Point2D testPoint4 = new Point2D(0.4, 0.9);
//        Point2D testPoint5 = new Point2D(0.5, 0.85);
//        Point2D testPoint6 = new Point2D(0.45, 0.6);
//        Point2D testPoint7 = new Point2D(0.40, 0.65);
//        test.insert(testPoint2);
//        test.insert(testPoint3);
//        test.insert(testPoint4);
////        System.out.println(test.isEmpty());
////        System.out.println(test.size());
////        System.out.println(test.contains(testPoint2));
////        System.out.println(test.contains(testPoint5));
//        test.insert(testPoint6);
//        RectHV testRect = new RectHV(0, 0.55, 1, 1);
//        RectHV testRect2 = new RectHV(0, 0.0, 1, 1);
//        System.out.println("--------");
//        for (Point2D point : test.range(testRect)) {
//            System.out.println(point);
//        }
//        test.draw();
//        System.out.println(testRect2.distanceSquaredTo(testPoint));
//        System.out.println(test.nearest(testPoint7));
    }
}