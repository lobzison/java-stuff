public class ThreeSort {
    public static void main(String[] args){
        int v1 = Integer.parseInt(args[0]);
        int v2 = Integer.parseInt(args[1]);
        int v3 = Integer.parseInt(args[2]);

        int v_first = Math.min(v1, Math.min(v2, v3));
        int v_last = Math.max(v1, Math.max(v2, v3));
        int v_mid = v1 + v2 + v3 - v_first - v_last;

        System.out.println(v_first);
        System.out.println(v_mid);
        System.out.println(v_last);
    }
}