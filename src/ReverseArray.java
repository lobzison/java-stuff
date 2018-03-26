import java.util.Arrays;

public class ReverseArray {
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        //initialize array with indexes
        int init[] = new int[n];
        for (int i = 0; i < n; i++){
            init[i] = i;
        }

        for (int i = 0; i < n / 2; i++){
            int temp = init[i];
            init[i] = init[n - 1 - i];
            init[n - 1 - i] = temp;
        }

        System.out.println(Arrays.toString(init));
    }
}
