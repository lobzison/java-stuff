import edu.princeton.cs.algs4.StdDraw;

public class ColinearTestClient {
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            // System.out.println(p);
            p.draw();
        }
        //StdDraw.point(21000, 10000);
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
//        Point[] test = new Point[3];
//        test[0] = new Point(10 ,10);
//        test[1] = null;
//        test[2] = new Point(20, 20);
//        FastCollinearPoints test2 = new FastCollinearPoints(test);
    }
}
