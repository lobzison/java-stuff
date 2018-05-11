import java.util.ArrayList;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        int len = points.length;
        for (int p1 = 0; p1 < len; p1++) {
            for (int p2 = 0; p2 < len; p2++) {
                for (int p3 = 0; p3 < len; p3++) {
                    for (int p4 = 0; p4 < len; p4++) {
                        if (points[p1].slopeTo(points[p2]) == points[p2].slopeTo(points[p3]) &&
                                points[p2].slopeTo(points[p3]) == points[p3].slopeTo(points[p4])) {
                            if (points[p1].compareTo(points[p2]) < 0 &&
                                    points[p2].compareTo(points[p3]) < 0 &&
                                    points[p3].compareTo(points[p4]) < 0) {
                                LineSegment LS = new LineSegment(points[p1], points[p4]);
                                segments.add(LS);
                            }
                        }

                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
//    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
//    public           int numberOfSegments()        // the number of line segments
//    public LineSegment[] segments()                // the line segments
}
