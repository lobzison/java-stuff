import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;

public class Solver {
    private Board init;
    private MinPQ<SearchNode> pq;
    private final Comparator<SearchNode> byHamming = new ByHamming();
    private final Comparator<SearchNode> byManhattan = new ByManhattan();
    private final Stack<Board> solution = new Stack<Board>();
    private final int numMoves;

    public Solver(Board initial) {
        this.init = initial;
        SearchNode initSN = new SearchNode(initial, 0, null);
        pq = new MinPQ<SearchNode>(byHamming);
        pq.insert(initSN);
        SearchNode current = pq.delMin();
        while (!current.board.isGoal()) {
            for (Board board : current.board.neighbors()) {
                if (!board.equals(current.predecessor.board)) {
                    SearchNode newNeighbour = new SearchNode(board, current.numMoves + 1, current);
                    pq.insert(newNeighbour);
                }
            }
            current = pq.delMin();
        }
        numMoves = current.numMoves;
        while (current != null) {
            solution.push(current.board);
            current = current.predecessor;
        }

    }         // find a solution to the initial board (using the A* algorithm)

    public boolean isSolvable() {
        return true;
    }       // is the initial board solvable?

    public int moves() {
        return numMoves;
    }                     // min number of moves to solve initial board; -1 if unsolvable

    public Iterable<Board> solution() {
        return solution;
    }      // sequence of boards in a shortest solution; null if unsolvable

    private class SearchNode {
        int numMoves;
        SearchNode predecessor;
        Board board;

        public SearchNode(Board board, int numMoves, SearchNode predecessor) {
            this.board = board;
            this.predecessor = predecessor;
            this.numMoves = numMoves;
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) return true;
            if (that == null) return false;
            if (this.getClass() != that.getClass()) return false;

            SearchNode other = (SearchNode) that;
            return this.board.equals(other);
        }
    }

    private class ByHamming implements Comparator<SearchNode> {
        public int compare(SearchNode n1, SearchNode n2) {
            return (n1.numMoves + n1.board.hamming()) - (n2.numMoves + n2.board.hamming());
        }
    }


    private class ByManhattan implements Comparator<SearchNode> {
        public int compare(SearchNode n1, SearchNode n2) {
            return (n1.numMoves + n1.board.manhattan()) - (n2.numMoves + n2.board.manhattan());
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    } // solve a slider puzzle (given below)
}
