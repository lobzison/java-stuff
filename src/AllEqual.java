public class AllEqual {
    public static void main(String[] args){
        int v1 = Integer.parseInt(args[0]);
        int v2 = Integer.parseInt(args[1]);
        int v3 = Integer.parseInt(args[2]);

        if (v1 == v2 && v1 == v3) {
            System.out.println("equal");
        }
        else System.out.println("not equal");
    }
}
