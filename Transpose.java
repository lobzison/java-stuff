import java.util.Arrays;

public class Transpose {
    public static void main(String[] args){
        int size = Integer.parseInt(args[0]);
        int matrix[][] = new int[size][size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                matrix[i][j] = (int) (Math.random()*10);
            }

        }

        for (int i = 0; i < size; i++){
            System.out.println(Arrays.toString(matrix[i]));
        }

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (i > j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
        System.out.println();
        for (int i = 0; i < size; i++){
            System.out.println(Arrays.toString(matrix[i]));
        }


    }
}
