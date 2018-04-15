import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] opennes;
    private final WeightedQuickUnionUF connectivity;
    private final int dim;
    private final int topSideIdx;
    private final int bottomSideIdx;
    private int numOpened = 0;

    public Percolation(int n) {                // create n-by-n grid, with all sites blocked
        verifyN(n);
        int size = (n * n) + 2;
        connectivity = new WeightedQuickUnionUF(size);
        dim = n;
        topSideIdx = 0;
        bottomSideIdx = size - 1;
        for (int i = 1; i <= n; i++) {
            connectivity.union(topSideIdx, i);
            connectivity.union(bottomSideIdx, bottomSideIdx - i);
        }
        opennes = new boolean[n][n];
    }

    public void open(int row, int col) {         // open site (row, col) if it is not open already
        verify(row, col);
        if (!opennes[row - 1][col - 1]) {
            opennes[row - 1][col - 1] = true;
            numOpened++;
            int currentConvert = convert(row, col);
            int neighbourConvert;
            if (row > 1) {
                if (opennes[row - 2][col - 1]) {
                    neighbourConvert = convert(row - 1, col);
                    connectivity.union(currentConvert, neighbourConvert);
                }
            }
            if (row < dim) {
                if (opennes[row][col - 1]) {
                    neighbourConvert = convert(row + 1, col);
                    connectivity.union(currentConvert, neighbourConvert);
                }
            }
            if (col > 1) {
                if (opennes[row - 1][col - 2]) {
                    neighbourConvert = convert(row, col - 1);
                    connectivity.union(currentConvert, neighbourConvert);
                }
            }
            if (col < dim) {
                if (opennes[row - 1][col]) {
                    neighbourConvert = convert(row, col + 1);
                    connectivity.union(currentConvert, neighbourConvert);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {    // is site (row, col) open?
        verify(row, col);
        return (opennes[row - 1][col - 1]);
    }

    public boolean isFull(int row, int col) {   // is site (row, col) full?
        verify(row, col);
        int convertedId = convert(row, col);
        return isOpen(row, col) && connectivity.connected(topSideIdx, convertedId);
    }

    public int numberOfOpenSites() {           // number of open sites
        return numOpened;
    }

    public boolean percolates() {               // does the system percolate?
        return connectivity.connected(topSideIdx, bottomSideIdx);
    }

    private int convert(int row, int col) {
        return ((row - 1) * dim) + col;
    }

    private void verify(int row, int col) {
        if (row <= 0 || col <= 0 || row > dim || col > dim) {
            throw new IllegalArgumentException("index out of range");
        }
    }

    private void verifyN(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("number need to be more that 1");
        }
    }

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        System.out.println("DUH " + size);
    }
}
