public class Checkboard {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        double SIDE = 1.0 / n;
        boolean isRed = true;
        boolean isPrevStartRed = isRed;
        double HALF_SIDE = SIDE / 2.0 ;
        double x = HALF_SIDE;
        double y = HALF_SIDE;

        StdDraw.enableDoubleBuffering();

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (j == 0) isPrevStartRed = isRed;
                if (isRed) StdDraw.setPenColor(StdDraw.RED);
                else StdDraw.setPenColor(StdDraw.BLACK);

                StdDraw.filledRectangle(x, y, HALF_SIDE, HALF_SIDE);
                x += SIDE;
                isRed = !isRed;
            }
            isRed = !isPrevStartRed;
            x = HALF_SIDE;
            y += SIDE;
        }

        StdDraw.show();
    }
}
