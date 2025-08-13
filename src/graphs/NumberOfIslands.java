package graphs;

/*
 * Number of Islands
 *
 * Given a 2D grid where '1' represents land and '0' represents water, count and return the number of islands.
 *
 * An island is formed by connecting adjacent lands horizontally or vertically and is surrounded by water. You may
 * assume water is surrounding the grid (i.e., all the edges are water).
 *
 * Example 1:
 *
 * Input: grid = [
 *     ["0","1","1","1","0"],
 *     ["0","1","0","1","0"],
 *     ["1","1","0","0","0"],
 *     ["0","0","0","0","0"]
 *   ]
 * Output: 1
 *
 * Example 2:
 *
 * Input: grid = [
 *     ["1","1","0","0","1"],
 *     ["1","1","0","0","1"],
 *     ["0","0","1","0","0"],
 *     ["0","0","0","1","1"]
 *   ]
 * Output: 4
 *
 * Constraints:
 *
 * 1. 1 <= grid.length, grid[i].length <= 100
 * 2. grid[i][j] is '0' or '1'.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(m * n) time and O(m * n) space, where m is the number of rows and n is the
 * number of columns in the grid.
 */
class NumberOfIslands {

    private static final char LAND = '1';
    private static final char DISCOVERED_LAND = '2';

    // Time complexity: O(m * n)
    // Space complexity: O(m * n)
    public int numIslands(char[][] grid) {
        int islandCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char currentChar = grid[i][j];
                if (currentChar == LAND) {
                    islandCount++;
                    markDiscoveredLand(grid, i, j);
                }
            }
        }
        return islandCount;
    }

    private void markDiscoveredLand(char[][] grid, int i, int j) {
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[i].length - 1) {
            return;
        }
        char currentChar = grid[i][j];
        if (currentChar == LAND) {
            grid[i][j] = DISCOVERED_LAND;
            markDiscoveredLand(grid, i - 1, j);
            markDiscoveredLand(grid, i + 1, j);
            markDiscoveredLand(grid, i, j - 1);
            markDiscoveredLand(grid, i, j + 1);
        }
    }
}

class NumberOfIslandsTester {

    public static void printExample(char[][] grid, String exampleId) {
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        System.out.println("numIslands" + exampleId + "=" + numberOfIslands.numIslands(grid));
    }

    public static void main(String[] args) {
        printExample(
                new char[][]{
                        {'0', '1', '1', '1', '0'},
                        {'0', '1', '0', '1', '0'},
                        {'1', '1', '0', '0', '0'},
                        {'0', '0', '0', '0', '0'}
                },
                "1"
        );
        printExample(
                new char[][]{
                        {'1', '1', '0', '0', '1'},
                        {'1', '1', '0', '0', '1'},
                        {'0', '0', '1', '0', '0'},
                        {'0', '0', '0', '1', '1'}
                },
                "2"
        );
    }
}
