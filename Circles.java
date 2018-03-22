public class Circles {
    public static void main(String[] args) {
        int    num = Integer.parseInt(args[0]);
        double prob = Double.parseDouble(args[1]);
        double min = Double.parseDouble(args[2]);
        double max = Double.parseDouble(args[3]);

        StdDraw.enableDoubleBuffering();

        for (int i = 0; i < num; i++){
            double rad = Math.random() * max + min;
            double b_prob = Math.random();
            double x = Math.random();
            double y = Math.random();

            if (prob > b_prob) StdDraw.setPenColor(StdDraw.BLACK);
            else StdDraw.setPenColor(StdDraw.WHITE);

            StdDraw.filledCircle(x, y, rad);

        }
        StdDraw.show();
    }
}
