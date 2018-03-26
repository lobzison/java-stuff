import java.util.Arrays;

public class Matrix {
    public static double dot (double[] a, double[] b){
        double sum = 0;
        for (int i = 0; i < a.length; i++){
            sum += a[i] * b[i];
        }
        return sum;
    }

    // return a random m-by-n matrix with values between 0 and 1
    public static double[][] random(int m, int n) {
        double[][] a = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = StdRandom.uniform(0.0, 1.0);
        return a;
    }

    // return n-by-n identity matrix I
    public static double[][] identity(int n) {
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++)
            a[i][i] = 1;
        return a;
    }

    public static double[][] multiply (double[][] a, double[][] b){
        //init the result
        double[][] res = new double[a.length][b[0].length];
        // for every row i a
        for (int i = 0; i < a.length; i++){
            double[] row = a[i];
            // for every column of b
            for (int j = 0; j < b[0].length; j++) {
                //collect a column from b
                double[] col = new double[a.length];
                for (int k = 0; k < col.length; k++) {
                    col[k] = b[k][j];
                }
                System.out.println(Arrays.toString(row)+ " "+ Arrays.toString(col)+ " "+ dot(row, col));
                res[i][j] = dot(row, col);

            }
        }
        return res;
    }

    public static double[][] transpose( double[][] a){
        double[][] res = new double[a[0].length][a.length];
        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < a[i].length; j++){
                res[j][i] = a[i][j];
            }
        }
        return res;
    }

    public static double[] multiply(double[][] a, double[] x){
        double[] res = new double[a.length];
        for (int i = 0; i < a.length; i++){
            res[i] = dot(a[i], x);
        }
        return res;
    }

    public static double[] multiply(double[] x, double[][] a){
        return multiply(a, x);
    }

    public static void main(String[] args){
        StdOut.println("D");
        StdOut.println("--------------------");
        double[][] d = { { 1, 2, 3 }, { 4, 5, 6 }, { 9, 1, 3} };
        StdArrayIO.print(d);
        StdOut.println();

        StdOut.println("I");
        StdOut.println("--------------------");
        double[][] c = Matrix.identity(5);
        StdArrayIO.print(c);
        StdOut.println();

        StdOut.println("A");
        StdOut.println("--------------------");
        double[][] a = Matrix.random(5, 5);
        StdArrayIO.print(a);
        StdOut.println();

        StdOut.println("A^T");
        StdOut.println("--------------------");
        double[][] b = Matrix.transpose(a);
        StdArrayIO.print(b);
        StdOut.println();

        StdOut.println("A * A^T");
        StdOut.println("--------------------");
        double[][] f = Matrix.multiply(a, b);
        StdArrayIO.print(f);
        StdOut.println();


    }
}
