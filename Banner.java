import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Banner {
    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        String txt = " ";
        for (int i = 0; i < args.length; i++){
            txt += args[i]+ " ";
        }
        double x = 0.5;

        while (true) {
            StdDraw.clear();
            StdDraw.text(x, 0.5, txt);
            StdDraw.text(x - 1, 0.5, txt);
            StdDraw.text(x + 1, 0.5, txt);
            StdDraw.pause(60);
            x += 0.01;
            if (x > 1) x = 0;
            StdDraw.show();
        }

    }
}
