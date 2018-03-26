import java.util.Arrays;

public class ArraysExperiments {
    public static void main(String[] args){
        int n = 10;
        int a1[] = new int[n];

        System.out.println(Arrays.toString(a1));

        int i = 1;
        Arrays.fill(a1, 10);
        System.out.println(Arrays.toString(a1));
        System.out.println(i);

        int a2[][] = new int[n][n];

        for (int r = 0; r < n; r++){
            for (int c = 0; c < n; c++){
                a2[r][c] = Math.max(r, c);
            }
        }

        for (int i2 = 0; i2 < n; i2++){
            System.out.println(Arrays.toString(a2[i2]));
        }
    }
}
