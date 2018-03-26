public class CartesianToPolar {
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);

        double r = Math.sqrt(x * x + y * y);
        double thetta = Math.atan2((double)y, (double)x);

        System.out.println("Radial distance is:"+ r + " Counterclockwise angle is:"+ thetta);
    }
}
