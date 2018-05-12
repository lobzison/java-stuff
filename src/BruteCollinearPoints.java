import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {
        int len = points.length;

        Point[] pointsCopy = Arrays.copyOf(points, len);
        Arrays.sort(pointsCopy);

        for (int p1 = 0; p1 < len - 3; p1++) {
            for (int p2 = p1 + 1; p2 < len - 2; p2++) {
                for (int p3 = p2 + 1; p3 < len - 1; p3++) {
                    for (int p4 = p3 + 1; p4 < len; p4++) {
                        if (pointsCopy[p1].slopeTo(pointsCopy[p2]) == pointsCopy[p2].slopeTo(pointsCopy[p3]) &&
                                pointsCopy[p2].slopeTo(pointsCopy[p3]) == pointsCopy[p3].slopeTo(pointsCopy[p4])) { {
                                LineSegment LS = new LineSegment(pointsCopy[p1], pointsCopy[p4]);
                                System.out.println(pointsCopy[p1].toString() + pointsCopy[p2].toString() + pointsCopy[p3].toString() + pointsCopy[p4].toString());
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
