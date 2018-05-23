import java.util.TreeSet;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.ArrayList;

public class PointSET {
    private final TreeSet<Point2D> pointSet;

    public PointSET () {
        pointSet = new TreeSet<>();
    }

    public boolean isEmpty() {
        return pointSet.isEmpty();
    }

    public int size() {
        return pointSet.size();
    }

    public void insert(Point2D p) {
        pointSet.add(p);
    }

    public boolean contains(Point2D p) {
        return pointSet.contains(p);
    }

    public void draw() {
        for (Point2D point : pointSet) {
            point.draw();
        }
    }

    private void check (Point2D p) {
        if (p == null) throw new IllegalArgumentException("input point is null");
    }

    public Iterable<Point2D> range(RectHV rect) {
        ArrayList<Point2D> pointsIn = new ArrayList<>();
        for (Point2D p : pointSet) {
            if (rect.contains(p)) pointsIn.add(p);
        }
        return pointsIn;
    }

    public Point2D nearest(Point2D p) {
        double lowest = Double.POSITIVE_INFINITY;
        Point2D closest = p;
        for (Point2D point : pointSet) {
            double dist = p.distanceTo(point);
            if (dist < lowest) {
                closest = point;
                lowest = dist;
            }
        }
        return closest;
    }

    public static void main(String[] args) {
        PointSET test = new PointSET();
        Point2D point1 = new Point2D(0,0);
        Point2D point2 = new Point2D(1,1);
        Point2D point3 = new Point2D(1,0);
        Point2D point4 = new Point2D(0,1);
        Point2D pointTest = new Point2D(0.9,0.9);
        test.insert(point1);
        test.insert(point2);
        test.insert(point3);
        test.insert(point4);
        RectHV rect = new RectHV(0,0,0.2,0.2);
        System.out.println(test.range(rect));
        System.out.println(test.nearest(pointTest));

    }
}
