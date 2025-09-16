package advancedgraphs;

import java.util.Arrays;

/*
 * Min Cost to Connect Points
 *
 * You are given a 2-D integer array points, where points[i] = [xi, yi]. Each points[i] represents a distinct point on a
 * 2-D plane.
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between the two points,
 * i.e. |xi - xj| + |yi - yj|.
 *
 * Return the minimum cost to connect all points together, such that there exists exactly one path between each pair of
 * points.
 *
 * Example 1:
 *
 * Input: points = [[0,0],[2,2],[3,3],[2,4],[4,2]]
 * Output: 10
 *
 * Constraints:
 *
 * 1. 1 <= points.length <= 1000
 * 2. -1000 <= xi, yi <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O((n^2)logn) time and O(n^2) space, where n is the number of
 * points.
 */
class MinCostToConnectPoints {

    public int minCostConnectPoints(int[][] points) {
        int totalCost = 0;
        boolean[] isInMST = new boolean[points.length];
        int[] minCosts = new int[points.length];
        Arrays.fill(minCosts, Integer.MAX_VALUE);

        minCosts[0] = 0;

        for (int i = 0; i < points.length; i++) {
            int minIndex = getMinIndexNotInMST(isInMST, minCosts);
            totalCost += minCosts[minIndex];
            isInMST[minIndex] = true;
            updateMinCosts(minIndex, points, isInMST, minCosts);
        }

        return totalCost;
    }

    public int getMinIndexNotInMST(boolean[] isInMST, int[] minCosts) {
        int minIndex = -1;
        for (int i = 0; i < minCosts.length; i++) {
            if (!isInMST[i]) {
                int currentMin = minIndex == -1
                        ? Integer.MAX_VALUE
                        : minCosts[minIndex];
                int selectedMin = minCosts[i];
                if (selectedMin < currentMin) {
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    public void updateMinCosts(int originPointIndex, int[][] points, boolean[] isInMST, int[] minCosts) {
        for(int i = 0; i < points.length; i++) {
            if (!isInMST[i]) {
                int distance = manhattanDistance(points[originPointIndex][0], points[originPointIndex][1], points[i][0], points[i][1]);
                minCosts[i] = Math.min(minCosts[i], distance);
            }
        }
    }

    public int manhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}

class MinCostToConnectPointsTester {

    public static void printExample(int[][] points, String exampleId) {
        MinCostToConnectPoints minCostToConnectPoints = new MinCostToConnectPoints();
        System.out.println("minCostConnectPoints" + exampleId + "=" + minCostToConnectPoints.minCostConnectPoints(points));
    }

    public static void main(String[] args) {
        printExample(new int[][]{{0, 0}, {2, 2}, {3, 3}, {2, 4}, {4, 2}}, "1");
    }
}
