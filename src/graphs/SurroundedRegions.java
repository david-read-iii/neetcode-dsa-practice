package graphs;

import java.util.Arrays;

/*
 * Surrounded Regions
 *
 * You are given a 2-D matrix board containing 'X' and 'O' characters.
 *
 * If a continuous, four-directionally connected group of 'O's is surrounded by 'X's, it is considered to be surrounded.
 *
 * Change all surrounded regions of 'O's to 'X's and do so in-place by modifying the input board.
 *
 * Example 1:
 *
 * Input: board = [
 *   ["X","X","X","X"],
 *   ["X","O","O","X"],
 *   ["X","O","O","X"],
 *   ["X","X","X","O"]
 * ]
 * Output: [
 *   ["X","X","X","X"],
 *   ["X","X","X","X"],
 *   ["X","X","X","X"],
 *   ["X","X","X","O"]
 * ]
 * Explanation: Note that regions that are on the border are not considered surrounded regions.
 *
 * Constraints:
 *
 * 1. 1 <= board.length, board[i].length <= 200
 * 2. board[i][j] is 'X' or 'O'.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(m * n) time and O(m * n) space, where m is the number of rows and n is the
 * number of columns in the matrix.
 */
class SurroundedRegions {

    public void solve(char[][] board) {

    }
}

class SurroundedRegionsTester {

    public static void printExample(char[][] board, String exampleId) {
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        surroundedRegions.solve(board);
        System.out.println("solve" + exampleId + "=" + Arrays.deepToString(board));
    }

    public static void main(String[] args) {
        printExample(
                new char[][]{
                        {'X', 'X', 'X', 'X'},
                        {'X', 'O', 'O', 'X'},
                        {'X', 'O', 'O', 'X'},
                        {'X', 'X', 'X', 'O'}
                },
                "1"
        );
        /*
                Expected output:

                {'X','O','X','O','X','O','O','O','X','O'},
                {'X','O','O','X','X','X','O','O','O','X'},
                {'O','O','O','O','O','O','O','O','X','X'},
                {'O','O','O','O','O','O','X','O','O','X'},
                {'O','O','X','X','O','X','X','O','O','O'},
                {'X','O','O','X','X','X','X','X','X','O'},
                {'X','O','X','X','X','X','X','O','X','O'},
                {'X','X','O','X','X','X','X','O','O','X'},
                {'O','O','O','O','X','X','X','O','X','O'},
                {'X','X','O','X','X','X','X','O','O','O'}
         */
        printExample(
                new char[][]{
                        {'X', 'O', 'X', 'O', 'X', 'O', 'O', 'O', 'X', 'O'},
                        {'X', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'X'},
                        {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X'},
                        {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X'},
                        {'O', 'O', 'X', 'X', 'O', 'X', 'X', 'O', 'O', 'O'},
                        {'X', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'O'},
                        {'X', 'O', 'X', 'O', 'O', 'X', 'X', 'O', 'X', 'O'},
                        {'X', 'X', 'O', 'X', 'X', 'O', 'X', 'O', 'O', 'X'},
                        {'O', 'O', 'O', 'O', 'X', 'O', 'X', 'O', 'X', 'O'},
                        {'X', 'X', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O'}
                },
                "2"
        );
    }
}
