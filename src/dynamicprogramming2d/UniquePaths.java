package dynamicprogramming2d;

/*
 * Unique Paths
 *
 * There is an m x n grid where you are allowed to move either down or to the right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that can be taken from the top-left corner
 * of the grid (grid[0][0]) to the bottom-right corner (grid[m - 1][n - 1]).
 *
 * You may assume the output will fit in a 32-bit integer.
 *
 * Example 1:
 *
 * Input: m = 3, n = 6
 * Output: 21
 *
 * Example 2:
 *
 * Input: m = 3, n = 3
 * Output: 6
 *
 * Constraints:
 *
 * 1. 1 <= m, n <= 100
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(m * n) time and O(m * n) space, where m is the number of rows
 * and n is the number of columns in the grid.
 */
class UniquePaths {

    public int uniquePaths(int m, int n) {

        // dp[x][y] is the number of unique paths from coordinate (x,y) to the bottom right coordinate (m-1,n-1)
        int[][] dp = new int[m][n];

        // There is only one unique path possible from the bottom row and right column coordinates to the bottom right coordinate trivially
        for (int x = 0; x < dp.length; x++) {
            dp[x][dp[x].length - 1] = 1;
        }
        for (int y = 0; y < dp[dp.length - 1].length - 1; y++) {
            dp[dp.length - 1][y] = 1;
        }

        // Build dp out right to left, bottom to top to ensure dependent coordinates are filled before execution.
        for (int x = dp.length - 2; x >= 0; x--) {
            for (int y = dp[x].length - 2; y >=0; y--) {
                buildDp(x, y, dp);
            }
        }

        return dp[0][0];
    }

    public void buildDp(int x, int y, int[][] dp) {
        int countBottomCoordinate = x < dp.length - 1
                ? dp[x + 1][y]
                : 0;
        int countRightCoordinate = y < dp[x].length - 1
                ? dp[x][y + 1]
                : 0;
        dp[x][y] = countBottomCoordinate + countRightCoordinate;
    }
}

class UniquePathsTester {

    public static void printExample(int m, int n, String exampleId) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println("uniquePaths" + exampleId + "=" + uniquePaths.uniquePaths(m, n));
    }

    public static void main(String[] args) {
        printExample(3, 6, "1");
        printExample(3, 3, "2");
    }
}
