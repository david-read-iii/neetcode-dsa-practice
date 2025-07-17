package heapandpriorityqueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * K Closest Points to Origin
 *
 * You are given an 2-D array points where points[i] = [xi, yi] represents the coordinates of a point on an X-Y axis
 * plane. You are also given an integer k.
 *
 * Return the k closest points to the origin (0, 0).
 *
 * The distance between two points is defined as the Euclidean distance (sqrt((x1 - x2)^2 + (y1 - y2)^2)).
 *
 * You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: points = [[0,2],[2,2]], k = 1
 * Output: [[0,2]]
 * Explanation : The distance between (0, 2) and the origin (0, 0) is 2. The distance between (2, 2) and the origin is
 * sqrt(2^2 + 2^2) = 2.82842. So the closest point to the origin is (0, 2).
 *
 * Example 2:
 *
 * Input: points = [[0,2],[2,0],[2,2]], k = 2
 * Output: [[0,2],[2,0]]
 * Explanation: The output [2,0],[0,2] would also be accepted.
 *
 * Constraints:
 *
 * 1. 1 <= k <= points.length <= 1000
 * 2. -100 <= points[i][0], points[i][1] <= 100
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(nlogk) time and O(k) space, where n is the size of the input
 * array, and k is the number of points to be returned.
 */
class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>(
                Comparator.comparingDouble(Point::getDistanceFromOrigin).reversed() // Comparator orders elements based on distanceFromOrigin greatest-to-least
        );
        populateMaxHeap(maxHeap, points, k);
        return buildResultArray(maxHeap, k);
    }

    public void populateMaxHeap(PriorityQueue<Point> maxHeap, int[][] points, int k) {
        for (int[] coordinate : points) {
            Point point = new Point(
                    calculateDistanceFromOrigin(coordinate[0], coordinate[1]),
                    coordinate[0],
                    coordinate[1]
            );

            if (maxHeap.size() < k) {
                maxHeap.add(point);
            } else if (point.getDistanceFromOrigin() < maxHeap.peek().getDistanceFromOrigin()) {
                maxHeap.remove();
                maxHeap.add(point);
            }
        }
    }

    public double calculateDistanceFromOrigin(int x, int y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public int[][] buildResultArray(PriorityQueue<Point> maxHeap, int k) {
        int[][] kClosestPointsToOrigin = new int[k][2];
        for (int i = 0; i < k; i++) {
            Point point = maxHeap.poll();
            kClosestPointsToOrigin[i][0] = point.getXCoordinate();
            kClosestPointsToOrigin[i][1] = point.getYCoordinate();
        }
        return kClosestPointsToOrigin;
    }

    static class Point {

        private final double distanceFromOrigin;
        private final int xCoordinate;
        private final int yCoordinate;

        public Point(double distanceFromOrigin, int xCoordinate, int yCoordinate) {
            this.distanceFromOrigin = distanceFromOrigin;
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
        }

        public double getDistanceFromOrigin() {
            return distanceFromOrigin;
        }

        public int getXCoordinate() {
            return xCoordinate;
        }

        public int getYCoordinate() {
            return yCoordinate;
        }
    }
}

class KClosestPointsToOriginTester {

    public static void printExample(int[][] points, int k, String exampleId) {
        KClosestPointsToOrigin kClosestPointsToOrigin = new KClosestPointsToOrigin();
        System.out.println("kClosest" + exampleId + "=" + Arrays.deepToString(kClosestPointsToOrigin.kClosest(points, k)));
    }

    public static void main(String[] args) {
        printExample(new int[][]{{0, 2}, {2, 2}}, 1, "1");
        printExample(new int[][]{{0, 2}, {2, 0}, {2, 2}}, 2, "2");
    }
}
