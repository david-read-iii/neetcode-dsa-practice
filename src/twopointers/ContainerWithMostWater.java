package twopointers;

/*
 * Container With Most Water
 *
 * You are given an integer array heights where heights[i] represents the height of the ith bar.
 *
 * You may choose any two bars to form a container. Return the maximum amount of water a container can store.
 *
 * Example 1:
 *
 * Input: height = [1,7,2,5,4,7,3,6]
 * Output: 36
 *
 * Example 2:
 *
 * Input: height = [2,2,2]
 * Output: 4
 *
 * Constraints:
 *
 * 2 <= height.length <= 1000
 * 0 <= height[i] <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(1) space, where n is the size of the input array.
 */
public class ContainerWithMostWater {

    public static int maxArea(int[] heights) {
        int i = 0;
        int j = heights.length - 1;
        int maxArea = -1;

        while (i != j) {
            int currentArea = findArea(i, j, heights);
            if (maxArea < currentArea) {
                maxArea = currentArea;
            }
            if (heights[i] > heights[j]) {
                j--;
            } else { // heights[i] <= heights[j]
                i++;
            }
        }
        return maxArea;
    }

    public static int findArea(int i, int j, int[] heights) {
        int minHeight = Math.min(heights[i], heights[j]);
        return minHeight * findDistance(i, j);
    }

    public static int findDistance(int i, int j) {
        return Math.abs(i - j);
    }

    public static void printExample(int[] heights, String exampleId) {
        System.out.println("maxArea" + exampleId + "=" + maxArea(heights));
    }

    public static void main(String[] args) {
        printExample(new int[]{1, 7, 2, 5, 4, 7, 3, 6}, "1");
        printExample(new int[]{2, 2, 2}, "2");
    }
}
