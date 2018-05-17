public class Solver {
    private Board init;

    public Solver(Board initial) {
        this.init = initial;
    }         // find a solution to the initial board (using the A* algorithm)

    public boolean isSolvable() {
        return false;
    }       // is the initial board solvable?

    public int moves() {
        return 1;
    }                     // min number of moves to solve initial board; -1 if unsolvable

    public Iterable<Board> solution() {
        return init.neighbors();
    }      // sequence of boards in a shortest solution; null if unsolvable

    public static void main(String[] args) {
        int i = 0;
    } // solve a slider puzzle (given below)
}
