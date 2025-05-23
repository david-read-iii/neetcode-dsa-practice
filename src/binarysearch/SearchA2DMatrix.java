package binarysearch;

/*
 * Search a 2D Matrix
 *
 * You are given an m x n 2-D integer array matrix and an integer target.
 *
 * - Each row in matrix is sorted in non-decreasing order.
 * - The first integer of every row is greater than the last integer of the previous row.
 *
 * Return true if target exists within matrix or false otherwise.
 *
 * Can you write a solution that runs in O(log(m * n)) time?
 *
 * Example 1:
 *
 * Input: matrix = [[1,2,4,8],[10,11,12,13],[14,20,30,40]], target = 10
 * Output: true
 *
 * Example 2:
 *
 * Input: matrix = [[1,2,4,8],[10,11,12,13],[14,20,30,40]], target = 15
 * Output: false
 *
 * Constraints:
 *
 * 1. m == matrix.length
 * 2. n == matrix[i].length
 * 3. 1 <= m, n <= 100
 * 4. -10000 <= matrix[i][j], target <= 10000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(log(m * n)) time and O(1) space, where m is the number of rows and n is the
 * number of columns in the matrix.
 */
public class SearchA2DMatrix {

    public static boolean searchMatrix(int[][] matrix, int target) {
        return searchMatrixInternal(matrix, target, 0, (matrix.length * matrix[0].length) - 1);
    }

    public static boolean searchMatrixInternal(int[][] matrix, int target, int l, int r) {
        int m = ((r - l) / 2) + l;
        int mValue = getMatrixValue(m, matrix);
        if (target == mValue) {
            return true;
        } else if (l < r) {
            if (target < mValue) {
                return searchMatrixInternal(matrix, target, l, m - 1);
            } else {
                return searchMatrixInternal(matrix, target, m + 1, r);
            }
        } else {
            return false;
        }
    }

    public static int getMatrixValue(int index, int[][] matrix) {
        return matrix[index / matrix[0].length][index % matrix[0].length];
    }

    public static void printExample(int[][] matrix, int target, String exampleId) {
        System.out.println("searchMatrix" + exampleId + "=" + searchMatrix(matrix, target));
    }

    public static void main(String[] args) {
        printExample(new int[][]{{1, 2, 4, 8}, {10, 11, 12, 13}, {14, 20, 30, 40}}, 10, "1");
        printExample(new int[][]{{1, 2, 4, 8}, {10, 11, 12, 13}, {14, 20, 30, 40}}, 15, "2");
        printExample(new int[][]{{1}}, 2, "3");
    }
}
