public class StaticMethods {
    public static void main(String[] args){
        int[] a = { 3, 1, 4, 1, 5 };
        int[] b = { 3, 1, 4, 1 };
        int[] c = { 3, 1, 4, 1, 5 };
        int[] d = { 2, 7, 1, 8, 2 };

        StdOut.println(eq(a, a));
        StdOut.println(eq(a, b));
        StdOut.println(eq(a, c));
        StdOut.println(eq(a, d));
    }

    public static int max3(int x, int y, int z){
        return Math.max(x, Math.max(y, z));
    }

    public static double max3(double x, double y, double z){
        return Math.max(x, Math.max(y, z));
    }

    public static boolean majority(boolean x1, boolean x2, boolean x3){
        return (x1 && x2) || (x1 && x3) || (x2 && x3);
    }

    public static boolean eq(int[] a1, int[] a2){
        if (a1.length != a2.length) return false;
        for (int i = 0; i < a1.length; i++){
            if (a1[i] != a2[i]) return false;
        }
        return true;
    }
}