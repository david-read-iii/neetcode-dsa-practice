package arraysandhashing;

import java.util.HashSet;

/*
 * Valid Sudoku
 *
 * You are given a 9 x 9 Sudoku board. A Sudoku board is valid if the following rules are followed:
 * 1. Each row must contain the digits 1-9 without duplicates.
 * 2. Each column must contain the digits 1-9 without duplicates.
 * 3. Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without duplicates.
 * Return true if the Sudoku board is valid, otherwise return false
 *
 * Note: A board does not need to be full or be solvable to be valid.
 *
 * Example 1:
 *
 * Input: board =
 * [["1","2",".",".","3",".",".",".","."],
 *  ["4",".",".","5",".",".",".",".","."],
 *  [".","9","8",".",".",".",".",".","3"],
 *  ["5",".",".",".","6",".",".",".","4"],
 *  [".",".",".","8",".","3",".",".","5"],
 *  ["7",".",".",".","2",".",".",".","6"],
 *  [".",".",".",".",".",".","2",".","."],
 *  [".",".",".","4","1","9",".",".","8"],
 *  [".",".",".",".","8",".",".","7","9"]]
 * Output: true
 *
 * Example 2:
 *
 * Input: board =
 * [["1","2",".",".","3",".",".",".","."],
 *  ["4",".",".","5",".",".",".",".","."],
 *  [".","9","1",".",".",".",".",".","3"],
 *  ["5",".",".",".","6",".",".",".","4"],
 *  [".",".",".","8",".","3",".",".","5"],
 *  ["7",".",".",".","2",".",".",".","6"],
 *  [".",".",".",".",".",".","2",".","."],
 *  [".",".",".","4","1","9",".",".","8"],
 *  [".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: There are two 1's in the top-left 3x3 sub-box.
 *
 * Constraints:
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit 1-9 or '.'.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n^2) time and O(n^2) space, where n is the number of rows in
 * the square grid.
 */
public class ValidSudoku {

    /*
     * Hashset solution.
     * Time complexity: O(n^2)
     * Space complexity: O(n^2)
     */
    public static boolean isValidSudoku(char[][] board) {
        HashSet<Character>[] horizontalCharacterOccurrences = new HashSet[9];
        HashSet<Character>[] verticalCharacterOccurrences = new HashSet[9];
        HashSet<Character>[] quadrantCharacterOccurrences = new HashSet[9];
        setupHashSets(horizontalCharacterOccurrences, verticalCharacterOccurrences, quadrantCharacterOccurrences);
        for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < board[rowIndex].length; columnIndex++) {
                boolean isValidHorizontal = updateAndCheckHorizontalCharacterOccurrences(horizontalCharacterOccurrences, rowIndex, columnIndex, board);
                if (!isValidHorizontal) {
                    return false;
                }
                boolean isValidVertical = updateAndCheckVerticalCharacterOccurrences(verticalCharacterOccurrences, rowIndex, columnIndex, board);
                if (!isValidVertical) {
                    return false;
                }
                boolean isValidQuadrant = updateAndCheckQuadrantCharacterOccurrences(quadrantCharacterOccurrences, rowIndex, columnIndex, board);
                if (!isValidQuadrant) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void setupHashSets(
            HashSet<Character>[] horizontalCharacterOccurrences,
            HashSet<Character>[] verticalCharacterOccurrences,
            HashSet<Character>[] quadrantCharacterOccurrences
    ) {
        for (int i = 0; i < horizontalCharacterOccurrences.length; i++) {
            horizontalCharacterOccurrences[i] = new HashSet<>();
            verticalCharacterOccurrences[i] = new HashSet<>();
            quadrantCharacterOccurrences[i] = new HashSet<>();
        }
    }

    public static boolean updateAndCheckHorizontalCharacterOccurrences(
            HashSet<Character>[] horizontalArrayCharacterOccurrences,
            int rowIndex,
            int columnIndex,
            char[][] board
    ) {
        HashSet<Character> hashSetToUpdateAndCheck = horizontalArrayCharacterOccurrences[rowIndex];
        char characterToAdd = board[rowIndex][columnIndex];
        if (Character.isDigit(characterToAdd)) {
            if (hashSetToUpdateAndCheck.contains(characterToAdd)) {
                return false;
            } else {
                hashSetToUpdateAndCheck.add(characterToAdd);
                return true;
            }
        } else {
            return true;
        }
    }

    public static boolean updateAndCheckVerticalCharacterOccurrences(
            HashSet<Character>[] verticalArrayCharacterOccurrences,
            int rowIndex,
            int columnIndex,
            char[][] board
    ) {
        HashSet<Character> hashSetToUpdateAndCheck = verticalArrayCharacterOccurrences[columnIndex];
        char characterToAdd = board[rowIndex][columnIndex];
        if (Character.isDigit(characterToAdd)) {
            if (hashSetToUpdateAndCheck.contains(characterToAdd)) {
                return false;
            } else {
                hashSetToUpdateAndCheck.add(characterToAdd);
                return true;
            }
        } else {
            return true;
        }
    }

    public static boolean updateAndCheckQuadrantCharacterOccurrences(
            HashSet<Character>[] quadrantCharacterOccurrences,
            int rowIndex,
            int columnIndex,
            char[][] board
    ) {
        int quadrant = getQuadrant(rowIndex, columnIndex);
        HashSet<Character> hashSetToUpdateAndCheck = quadrantCharacterOccurrences[quadrant];
        char characterToAdd = board[rowIndex][columnIndex];
        if (Character.isDigit(characterToAdd)) {
            if (hashSetToUpdateAndCheck.contains(characterToAdd)) {
                return false;
            } else {
                hashSetToUpdateAndCheck.add(characterToAdd);
                return true;
            }
        } else {
            return true;
        }
    }

    public static int getQuadrant(int rowIndex, int columnIndex) {
        if (rowIndex <= 2) {
            if (columnIndex <= 2) {
                return 0;
            } else if (columnIndex >= 6) {
                return 2;
            } else {
                return 1;
            }
        } else if (rowIndex >= 6) {
            if (columnIndex <= 2) {
                return 6;
            } else if (columnIndex >= 6) {
                return 8;
            } else {
                return 7;
            }
        } else {
            if (columnIndex <= 2) {
                return 3;
            } else if (columnIndex >= 6) {
                return 5;
            } else {
                return 4;
            }
        }
    }

    public static void printExample(char[][] board, String exampleId) {
        System.out.println("isValidSudoku" + exampleId + "=" + isValidSudoku(board));
    }

    public static void main(String[] args) {
        printExample(
                new char[][]{
                        {'1', '2', '.', '.', '3', '.', '.', '.', '.'},
                        {'4', '.', '.', '5', '.', '.', '.', '.', '.'},
                        {'.', '9', '8', '.', '.', '.', '.', '.', '3'},
                        {'5', '.', '.', '.', '6', '.', '.', '.', '4'},
                        {'.', '.', '.', '8', '.', '3', '.', '.', '5'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '.', '.', '.', '.', '.', '2', '.', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '8'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                },
                "1"
        );

        printExample(
                new char[][]{
                        {'1', '2', '.', '.', '3', '.', '.', '.', '.'},
                        {'4', '.', '.', '5', '.', '.', '.', '.', '.'},
                        {'.', '9', '1', '.', '.', '.', '.', '.', '3'},
                        {'5', '.', '.', '.', '6', '.', '.', '.', '4'},
                        {'.', '.', '.', '8', '.', '3', '.', '.', '5'},
                        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                        {'.', '.', '.', '.', '.', '.', '2', '.', '.'},
                        {'.', '.', '.', '4', '1', '9', '.', '.', '8'},
                        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                },
                "2"
        );

        printExample(
                new char[][]{
                        {'.', '.', '.', '.', '.', '.', '.', '.', '2'},
                        {'.', '.', '.', '.', '.', '.', '6', '.', '.'},
                        {'.', '.', '1', '4', '.', '.', '8', '.', '.'},
                        {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '3', '.', '.', '.', '.'},
                        {'5', '.', '8', '6', '.', '.', '.', '.', '.'},
                        {'.', '9', '.', '.', '.', '.', '4', '.', '.'},
                        {'.', '.', '.', '.', '5', '.', '.', '.', '.'}
                },
                "3"
        );
    }
}
