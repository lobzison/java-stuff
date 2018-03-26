public class StaticMethods {
    public static void main(String[] args){
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int z = Integer.parseInt(args[2]);

        System.out.println(max3(x, y, z));
    }

    public static int max3(int x, int y, int z){
        return Math.max(x, Math.max(y, z));
    }

    public static double max3(double x, double y, double z){
        return Math.max(x, Math.max(y, z));
    }
}