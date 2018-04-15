import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int total;
    private final double[] tresholds;
    private static final double confidence95 = 1.96;
    private final double stddev;
    private final double mean;

    public PercolationStats(int n, int trials) {     // perform trials independent experiments on an n-by-n grid
        verify(n, trials);
        total = n * n;
        tresholds = new double[trials];
        for (int i = 1; i <= trials; i++) {
            int open = 0;
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                while (perc.isOpen(row, col)) {
                    row = StdRandom.uniform(1, n + 1);
                    col = StdRandom.uniform(1, n + 1);
                }
                perc.open(row, col);
                open++;
            }
            tresholds[i - 1] = ((double) open) / ((double) total);
        }
        stddev = StdStats.stddev(tresholds);
        mean = StdStats.mean(tresholds);

    }

    public double mean() {                        // sample mean of percolation threshold
        return mean;
    }

    public double stddev() {                      // sample standard deviation of percolation threshold
        return stddev;
    }

    public double confidenceLo() {                // low  endpoint of 95% confidence interval
        double sqrtTotal = Math.sqrt(total);
        return mean - ((confidence95 * stddev)) / sqrtTotal;
    }

    public double confidenceHi() {                // high  endpoint of 95% confidence interval
        double sqrtTotal = Math.sqrt(total);
        return mean + ((confidence95 * stddev)) / sqrtTotal;
    }

    private void verify(int n, int t) {
        if (n < 1 || t < 1) {
            throw new IllegalArgumentException("trials and number of sides should be more that 1");
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats stat = new PercolationStats(n, t);
        System.out.println("mean                    = " + stat.mean());
        System.out.println("stddev                  = " + stat.stddev());
        double[] conf = new double[2];
        conf[0] = stat.confidenceLo();
        conf[1] = stat.confidenceHi();
        System.out.println("95% confidence interval = [" + conf[0] + ", " + conf[1] + "}");
    }

//    public static void main(String[] args)        // test client (described below)
}
