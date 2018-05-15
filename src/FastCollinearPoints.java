import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        check(points);
        int len = points.length;
        Point[] copyPoints = points.clone();
        Arrays.sort(copyPoints);
        for (int p = 0; p < len - 3; p++) {
            Arrays.sort(copyPoints);
            Arrays.sort(copyPoints, copyPoints[p].slopeOrder());

            for (int first = 1, last = 2; last < len; last++) {
                int i = 0;
                while (last < len && copyPoints[i].slopeTo(copyPoints[first]) == copyPoints[i].slopeTo(copyPoints[last]))
                    last++;
                if (last - first >= 3 && copyPoints[i].compareTo(copyPoints[first])< 0) {
                    segments.add(new LineSegment(copyPoints[i], copyPoints[last - 1]));
                }
                first = last;
            }

        }
//        int start = 0;
//        double previousSlope = Double.NEGATIVE_INFINITY;
//        Point[] copyPoints = points.clone();
//        LineSegment ls;
//        Arrays.sort(copyPoints);
//        for (int p = 0; p < len; p++) {
//            int counter = 1;
//            Arrays.sort(copyPoints, points[p].slopeOrder());
////            System.out.println("main point = " + points[p]);
////            System.out.println("Sorted array = " + Arrays.toString(copyPoints));
//            for (int q = 0; q < len; q++) {
//                double currentSlope = points[p].slopeTo(copyPoints[q]);
//                if (currentSlope == previousSlope) {
//                    if (counter == 1) start = q - 1;
//                    counter++;
//                } else {
//                    if (counter >= 3) {
//                        Point[] linePoints = new Point[counter + 1];
//                        linePoints[0] = points[p];
//                        for (int i = start; i <= q - 1; i++) {
////                            System.out.println("j = " + (i - start + 1) + " i = " + i);
//                            linePoints[i - start + 1] = copyPoints[i];
//                        }
//                        Arrays.sort(linePoints);
////                        System.out.println("start = " + start + " end = " + (q - 1));
////                        System.out.println("Array formed = " + Arrays.toString(linePoints));
//                        //if (isSorted(linePoints)) {
////                            System.out.println("start = " + start + " end = " + (q - 1));
////                            System.out.println(Arrays.toString(linePoints));
//                        ls = new LineSegment(linePoints[0], linePoints[linePoints.length - 1]);
////                            System.out.println("Point p = " + points[p].toString() + "point start = " + copyPoints[start].toString() + "point end = " + copyPoints[q - 1].toString());
////                            System.out.println("Line added = " + ls.toString());
//                        segments.add(ls);
//                        //}
//                    }
//                    counter = 1;
//                }
////                System.out.println(counter);
//                previousSlope = currentSlope;
//            }
//        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    private boolean isSorted(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    private void check(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException("input is null");
        }
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new java.lang.IllegalArgumentException("one or more of the points is null");
            for (int j = i + 1; j < points.length; j++) {
                if (points[j] == null)
                    throw new java.lang.IllegalArgumentException("one or more of the points is null");
                if (points[i].compareTo(points[j]) == 0)
                    throw new java.lang.IllegalArgumentException("contains repeats");
            }
        }
    }
//    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
//    public           int numberOfSegments()        // the number of line segments
//    public LineSegment[] segments()                // the line segments
}
