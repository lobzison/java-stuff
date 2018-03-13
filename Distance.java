public class Distance {
    public static void main(String[] args){
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);

        double dist = Math.sqrt(Math.pow(x, 2)+ Math.pow(y, 2));

        System.out.println("Distance between (0, 0) and (" + args[0] + ", " + args[1] + ") is " + dist);
    }
}
