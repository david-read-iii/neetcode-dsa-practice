package graphs;

/*
 * Max Area of Island
 *
 * You are given a matrix grid where grid[i] is either a 0 (representing water) or 1 (representing land).
 *
 * An island is defined as a group of 1's connected horizontally or vertically. You may assume all four edges of the
 * grid are surrounded by water.
 *
 * The area of an island is defined as the number of cells within the island.
 *
 * Return the maximum area of an island in grid. If no island exists, return 0.
 *
 * Example 1:
 *
 * Input: grid = [
 *   [0,1,1,0,1],
 *   [1,0,1,0,1],
 *   [0,1,1,0,1],
 *   [0,1,0,0,1]
 * ]
 * Output: 6
 * Explanation: 1's cannot be connected diagonally, so the maximum area of the island is 6.
 *
 * Constraints:
 *
 * 1 <= grid.length, grid[i].length <= 50
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(m * n) time and O(m * n) space, where m is the number of rows and n is the
 * number of columns in the grid.
 */
class MaxAreaOfIsland {

    private static final int LAND = 1;
    private static final int DISCOVERED_LAND = 2;

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == LAND) {
                    maxArea = Math.max(maxArea, getAreaOfIsland(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    public int getAreaOfIsland(int[][] grid, int i, int j) {
        int[] count = new int[]{0};
        getAreaOfIslandInternal(grid, i, j, count);
        return count[0];
    }

    public void getAreaOfIslandInternal(int[][] grid, int i, int j, int[] count) {
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[i].length - 1) {
            return;
        }
        if (grid[i][j] == LAND) {
            grid[i][j] = DISCOVERED_LAND;
            count[0]++;

            getAreaOfIslandInternal(grid, i - 1, j, count);
            getAreaOfIslandInternal(grid, i + 1, j, count);
            getAreaOfIslandInternal(grid, i, j - 1, count);
            getAreaOfIslandInternal(grid, i, j + 1, count);
        }
    }
}

class MaxAreaOfIslandTester {

    public static void printExample(int[][] grid, String exampleId) {
        MaxAreaOfIsland maxAreaOfIsland = new MaxAreaOfIsland();
        System.out.println("maxAreaOfIsland" + exampleId + "=" + maxAreaOfIsland.maxAreaOfIsland(grid));
    }

    public static void main(String[] args) {
        printExample(
                new int[][]{
                        {0, 1, 1, 0, 1},
                        {1, 0, 1, 0, 1},
                        {0, 1, 1, 0, 1},
                        {0, 1, 0, 0, 1}
                },
                "1"
        );
    }
}
