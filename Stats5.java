public class Stats5{
    public static void main(String[] args){
        double v1 = Math.random();
        double v2 = Math.random();
        double v3 = Math.random();
        double v4 = Math.random();
        double v5 = Math.random();

        double v_avg = (v1 + v2 + v3 + v4 + v5) / 5;
        double v_max = Math.max(v1, Math.max(v2, Math.max(v3, Math.max(v4, v5))));
        double v_min = Math.min(v1, Math.min(v2, Math.min(v3, Math.min(v4, v5))));

        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);
        System.out.println(v5);
        System.out.println(v_avg);
        System.out.println(v_max);
        System.out.println(v_min);
        
    }
}