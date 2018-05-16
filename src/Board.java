import java.util.Arrays;

public class Board {
    private final int[][] blocks;
    public final int[][] goal;

    public Board(int[][] blocks) {
        checkInput(blocks);
        this.blocks = blocks;
        int dim = dimension();
        goal = new int[dim][dim];
        for (int i = 0, val = 1; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (i == dim - 1 && j == dim - 1) {
                    goal[i][j] = 0;
                } else {
                    goal[i][j] = val;
                    val++;
                }
            }
        }

    }

    public int dimension() {
        return this.blocks.length;
    }

    private void checkInput(int[][] blocks) {
        if (!isSquare(blocks)) throw new IllegalArgumentException("Board is not square");
    }

    private boolean isSquare(int[][] blocks) {
        return blocks.length == blocks[0].length;
    }

    public boolean isGoal() {
        int dim = dimension();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (this.blocks[i][j] != this.goal[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        int n = dimension();
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", this.blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        int[][] solved3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] unsloved3 = {{1, 2, 4}, {3, 0, 6}, {7,8,5}};

        Board solved3b = new Board(solved3);
        Board unsloved3b = new Board(unsloved3);

        String str_board = unsloved3b.toString();
        System.out.println(str_board);

        System.out.println(Arrays.deepToString(solved3b.goal));
        System.out.println(Arrays.deepToString(unsloved3b.goal));
        System.out.println(unsloved3b.isGoal());
        System.out.println(solved3b.isGoal());
    }

}


//public class Board {
//    public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks
//    // (where blocks[i][j] = block in row i, column j)
//    public int dimension()                 // board dimension n
//    public int hamming()                   // number of blocks out of place
//    public int manhattan()                 // sum of Manhattan distances between blocks and goal
//    public boolean isGoal()                // is this board the goal board?
//    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
//    public boolean equals(Object y)        // does this board equal y?
//    public Iterable<Board> neighbors()     // all neighboring boards
//    public String toString()               // string representation of this board (in the output format specified below)
//
//    public static void main(String[] args) // unit tests (not graded)
//}