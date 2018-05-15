import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        check(points);
        int len = points.length;
        Point[] copyPoints = points.clone();
        Arrays.sort(copyPoints);
        for (int p1 = 0; p1 < len - 3; p1++) {
            for (int p2 = p1 + 1; p2 < len - 2; p2++) {
                for (int p3 = p2 + 1; p3 < len - 1; p3++) {
                    for (int p4 = p3 + 1; p4 < len; p4++) {
                        if (copyPoints[p1].slopeTo(copyPoints[p2]) == copyPoints[p2].slopeTo(copyPoints[p3]) &&
                                copyPoints[p2].slopeTo(copyPoints[p3]) == copyPoints[p3].slopeTo(copyPoints[p4])) {
                            LineSegment LS = new LineSegment(copyPoints[p1], copyPoints[p4]);
                            // System.out.println(copyPoints[p1].toString() + copyPoints[p2].toString() + copyPoints[p3].toString() + copyPoints[p4].toString());
                            segments.add(LS);
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

    private void check(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException("input is null");
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new java.lang.IllegalArgumentException("one or more of the points is null");
            for (int j = i + 1; j < points.length; j++) {
                if (points[j] == null) throw new java.lang.IllegalArgumentException("one or more of the points is null");
                if (points[i].compareTo(points[j]) == 0) throw new java.lang.IllegalArgumentException("contains repeats");
            }
        }
//        for (int i = 0; i < points.length - 1; i++) {
//            if (points[i] == null || points[i + 1] == null) throw new java.lang.IllegalArgumentException("one or more of the points is null");
//
//            if (points[i].compareTo(points[i + 1]) == 0) {
//                throw new java.lang.IllegalArgumentException("contains repeats");
//            }
//        }
    }
//    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
//    public           int numberOfSegments()        // the number of line segments
//    public LineSegment[] segments()                // the line segments
}
