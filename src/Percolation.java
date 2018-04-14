import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {
    private int[][] opennes;
    private WeightedQuickUnionUF connectivity;
    private int dim;
    private int top_side_idx;
    private int bottom_side_idx;

    public Percolation(int n) {                // create n-by-n grid, with all sites blocked
        int size = (n * n) + 2;
        connectivity = new WeightedQuickUnionUF(size);
        dim = n;
        for (int i=1; i <= n;i++) {
            top_side_idx = 0;
            bottom_side_idx = size - 1;
            connectivity.union(top_side_idx, i);
            connectivity.union(bottom_side_idx, bottom_side_idx - i);
        }
        opennes = new int[n][n];
    }

    public void open(int row, int col){         // open site (row, col) if it is not open already
        verify(row, col);
        opennes[row-1][col-1] = 1;
        int current_convert = convert(row, col);
        int neighbour_convert;
        if (row > 1){
            if (opennes[row-2][col-1] == 1){
                neighbour_convert = convert(row - 1, col);
                connectivity.union(current_convert, neighbour_convert);
            }
        }
        if (row < dim){
            if (opennes[row][col-1] == 1){
                neighbour_convert = convert(row + 1, col);
                connectivity.union(current_convert, neighbour_convert);
            }
        }
        if (col > 1){
            if (opennes[row-1][col-2] == 1){
                neighbour_convert = convert(row, col - 1);
                connectivity.union(current_convert, neighbour_convert);
            }
        }
        if (col < dim){
            if (opennes[row-1][col] == 1){
                neighbour_convert = convert(row, col + 1);
                connectivity.union(current_convert, neighbour_convert);
            }
        }
    }

    public boolean isOpen(int row, int col){    // is site (row, col) open?
        verify(row, col);
        return (opennes[row-1][col-1] == 1);
    }

    public boolean isFull(int row, int col){   // is site (row, col) full?
        verify(row, col);
        int converted_id = convert(row, col);
        return isOpen(row, col) && connectivity.connected(top_side_idx, converted_id);
    }

    public int numberOfOpenSites(){           // number of open sites
        int num = 0;
        for (int i = 0; i < opennes.length; i++){
            for (int j = 0; j < opennes[0].length; i++){
                if (opennes[i][j] == 1) num++;
            }
        }
        return num;
    }

    public boolean percolates(){               // does the system percolate?
        return connectivity.connected(top_side_idx, bottom_side_idx);
    }

    private int convert(int row, int col){
        return ((row - 1)* dim) + col;
    }

    private void verify(int row, int col){
        if (row <= 0 || col <=0 || row >dim || col > dim){
            throw new IllegalArgumentException("index out of range");
        }
    }

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        Percolation perc = new Percolation(size);
        System.out.println("DUH");
    }
}
