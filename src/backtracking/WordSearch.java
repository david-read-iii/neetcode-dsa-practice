package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Word Search
 *
 * Given a 2-D grid of characters board and a string word, return true if the word is present in the grid, otherwise
 * return false.
 *
 * For the word to be present it must be possible to form it with a path in the board with horizontally or vertically
 * neighboring cells. The same cell may not be used more than once in a word.
 *
 * Example 1:
 *
 * Input:
 * board = [
 *   ["A","B","C","D"],
 *   ["S","A","A","T"],
 *   ["A","C","A","E"]
 * ],
 * word = "CAT"
 * Output: true
 *
 * Example 2:
 *
 * Input:
 * board = [
 *   ["A","B","C","D"],
 *   ["S","A","A","T"],
 *   ["A","C","A","E"]
 * ],
 * word = "BAT"
 * Output: false
 *
 * Constraints:
 *
 * 1. 1 <= board.length, board[i].length <= 5
 * 2. 1 <= word.length <= 10
 * 3. board and word consists of only lowercase and uppercase English letters.
 *
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(m * (4^n)) time and O(n) space, where m is the number of cells in the given
 * board and n is the size of the given word.
 */
class WordSearch {

    public boolean exist(char[][] board, String word) {
        boolean[] matchFound = new boolean[]{false};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    List<Character> currentWord = new ArrayList<>();
                    Coordinate currentCoordinate = new Coordinate(i, j);
                    Set<Coordinate> coordinatesVisited = new HashSet<>();
                    currentWord.add(board[i][j]);
                    coordinatesVisited.add(currentCoordinate);
                    existInternal(board, word, currentWord, currentCoordinate, coordinatesVisited, matchFound);
                }
            }
        }

        return matchFound[0];
    }

    public void existInternal(char[][] board, String targetWord, List<Character> currentWord, Coordinate currentCoordinate, Set<Coordinate> coordinatesVisited, boolean[] matchFound) {
        if (matchFound[0]) {
            return;
        }

        EqualityType equalityType = currentWordEqualsTargetWord(currentWord, targetWord);
        if (equalityType == EqualityType.EXACT_MATCH) {
            matchFound[0] = true;
        } else if (equalityType == EqualityType.PARTIAL_MATCH) {

            Coordinate topCoordinate = new Coordinate(currentCoordinate.i - 1, currentCoordinate.j);
            if (topCoordinate.i >= 0 && !coordinatesVisited.contains(topCoordinate)) {
                backtrack(board, targetWord, currentWord, topCoordinate, coordinatesVisited, matchFound);
            }

            Coordinate leftCoordinate = new Coordinate(currentCoordinate.i, currentCoordinate.j - 1);
            if (leftCoordinate.j >= 0 && !coordinatesVisited.contains(leftCoordinate)) {
                backtrack(board, targetWord, currentWord, leftCoordinate, coordinatesVisited, matchFound);
            }

            Coordinate rightCoordinate = new Coordinate(currentCoordinate.i, currentCoordinate.j + 1);
            if (rightCoordinate.j < board[rightCoordinate.i].length && !coordinatesVisited.contains(rightCoordinate)) {
                backtrack(board, targetWord, currentWord, rightCoordinate, coordinatesVisited, matchFound);
            }

            Coordinate bottomCoordinate = new Coordinate(currentCoordinate.i + 1, currentCoordinate.j);
            if (bottomCoordinate.i < board.length && !coordinatesVisited.contains(bottomCoordinate)) {
                backtrack(board, targetWord, currentWord, bottomCoordinate, coordinatesVisited, matchFound);
            }
        }
    }

    public void backtrack(char[][] board, String targetWord, List<Character> currentWord, Coordinate coordinateToAdd, Set<Coordinate> coordinatesVisited, boolean[] matchFound) {
        currentWord.add(board[coordinateToAdd.i][coordinateToAdd.j]);
        coordinatesVisited.add(coordinateToAdd);
        existInternal(board, targetWord, currentWord, coordinateToAdd, coordinatesVisited, matchFound);
        currentWord.remove(currentWord.size() - 1);
        coordinatesVisited.remove(coordinateToAdd);
    }

    public EqualityType currentWordEqualsTargetWord(List<Character> currentWord, String targetWord) {
        if (currentWord.size() > targetWord.length()) {
            return EqualityType.NO_MATCH;
        }
        for (int i = 0; i < currentWord.size(); i++) {
            if (currentWord.get(i) != targetWord.charAt(i)) {
                return EqualityType.NO_MATCH;
            }
        }

        if (currentWord.size() < targetWord.length()) {
            return EqualityType.PARTIAL_MATCH;
        } else { // currentWord.size() == targetWord.length()
            return EqualityType.EXACT_MATCH;
        }
    }

    public enum EqualityType {NO_MATCH, PARTIAL_MATCH, EXACT_MATCH}

    public static class Coordinate {
        int i;
        int j;

        Coordinate(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Coordinate) {
                return i == ((Coordinate) obj).i && j == ((Coordinate) obj).j;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return 31 * i + j;
        }
    }
}

class WordSearchTester {

    public static void printExample(char[][] board, String word, String exampleId) {
        WordSearch wordSearch = new WordSearch();
        System.out.println("exist" + exampleId + "=" + wordSearch.exist(board, word));
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A', 'B', 'C', 'D'}, {'S', 'A', 'A', 'T'}, {'A', 'C', 'A', 'E'}};
        printExample(board, "CAT", "1");
        printExample(board, "BAT", "2");
    }
}
