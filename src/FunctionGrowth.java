public class FunctionGrowth {
    public static void main(String[] args) {
        int START = 16;
        int END   = 2048;

        for (int n = START; n <= END; n *= 2){
            int v_ln = (int) Math.log(n);
            int v_nln = v_ln * n;
            int v_pow2 = n*n;
            int v_pow3 = n*n*n;
            int v_2pown = (int) Math.pow(2, n);

            System.out.print(v_ln);
            System.out.print("\t");
            System.out.print(v_nln);
            System.out.print("\t");
            System.out.print(n);
            System.out.print("\t");
            System.out.print(v_pow2);
            System.out.print("\t");
            System.out.print(v_pow3);
            System.out.print("\t");
            System.out.print(v_2pown);
            System.out.println();
        }
    }
}
