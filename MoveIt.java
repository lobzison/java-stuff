import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class MoveIt {
    public static void main(String[] args) {
        while (true) {
            Random rand = new Random();
            int x = rand.nextInt(1000) + 1;
            int y = rand.nextInt(1000) + 1;
            try {
                // These coordinates are screen coordinates
                int xCoord = x;
                int yCoord = y;

                // Move the cursor
                Robot robot = new Robot();
                robot.mouseMove(xCoord, yCoord);
                TimeUnit.SECONDS.sleep(2);
            } catch (AWTException | InterruptedException e) {
            }
        }
    }
}


