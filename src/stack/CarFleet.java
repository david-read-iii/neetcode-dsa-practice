package stack;

import java.util.Stack;

/*
 * Car Fleet
 *
 * There are n cars traveling to the same destination on a one-lane highway.
 *
 * You are given two arrays of integers position and speed, both of length n.
 *
 * - position[i] is the position of the ith car (in miles)
 * - speed[i] is the speed of the ith car (in miles per hour)
 *
 * The destination is at position target miles.
 *
 * A car can not pass another car ahead of it. It can only catch up to another car and then drive at the same speed as
 * the car ahead of it.
 *
 * A car fleet is a non-empty set of cars driving at the same position and same speed. A single car is also considered a
 * car fleet.
 *
 * If a car catches up to a car fleet the moment the fleet reaches the destination, then the car is considered to be
 * part of the fleet.
 *
 * Return the number of different car fleets that will arrive at the destination.
 *
 * Example 1:
 *
 * Input: target = 10, position = [1,4], speed = [3,2]
 * Output: 1
 * Explanation: The cars starting at 1 (speed 3) and 4 (speed 2) become a fleet, meeting each other at 10, the destination.
 *
 * Example 2:
 *
 * Input: target = 10, position = [4,1,0,7], speed = [2,2,1,1]
 * Output: 3
 * Explanation: The cars starting at 4 and 7 become a fleet at position 10. The cars starting at 1 and 0 never catch up
 * to the car ahead of them. Thus, there are 3 car fleets that will arrive at the destination.
 *
 * Constraints:
 *
 * 1. n == position.length == speed.length.
 * 2. 1 <= n <= 1000
 * 3. 0 < target <= 1000
 * 4. 0 < speed[i] <= 100
 * 5. 0 <= position[i] < target
 * 6. All the values of position are unique.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(nlogn) time and O(n) space, where n is the size of the input array.
 */
public class CarFleet {

    public static int carFleet(int target, int[] position, int[] speed) {

        // Sort position and speed arrays in O(logn) time. Sort position array, and make same swaps in speed array.
        mergeSort(position, speed);

        Stack<Double> carFleets = new Stack<>();
        for (int i = 0; i < position.length; i++) {
            double currentTimeToTarget = (double) (target - position[i]) / speed[i];
            /* Form a new car fleet if one of the following is true:
             * - No car fleets formed yet.
             * - Current car will not pass a car ahead of it. */
            if (carFleets.isEmpty() || currentTimeToTarget > carFleets.peek()) {
                carFleets.push(currentTimeToTarget);
            }
        }

        return carFleets.size();
    }

    public static void mergeSort(int[] position, int[] speed) {
        sortInternal(position, speed, 0, position.length - 1);
    }

    public static void sortInternal(int[] position, int[] speed, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sortInternal(position, speed, l, m);
            sortInternal(position, speed, m + 1, r);
            mergeInternal(position, speed, l, m, r);
        }
    }

    public static void mergeInternal(int[] position, int[] speed, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] tempPositionL = new int[n1]; // Holds position[l] to position[m].
        int[] tempSpeedL = new int[n1]; // Holds speed[l] to speed[m].
        System.arraycopy(position, l, tempPositionL, 0, n1);
        System.arraycopy(speed, l, tempSpeedL, 0, n1);


        int[] tempPositionR = new int[n2]; // Holds position[m + 1] to position[r].
        int[] tempSpeedR = new int[n2]; // Holds speed[m + 1] to speed[r].
        System.arraycopy(position, m + 1, tempPositionR, 0, n2);
        System.arraycopy(speed, m + 1, tempSpeedR, 0, n2);

        int i = 0;
        int j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (tempPositionL[i] >= tempPositionR[j]) {
                position[k] = tempPositionL[i];
                speed[k] = tempSpeedL[i];
                i++;
            } else {
                position[k] = tempPositionR[j];
                speed[k] = tempSpeedR[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of tempPositionL/tempSpeedL, if any.
        while (i < n1) {
            position[k] = tempPositionL[i];
            speed[k] = tempSpeedL[i];
            i++;
            k++;
        }

        // Copy remaining elements of tempPositionR/tempSpeedR, if any.
        while (j < n2) {
            position[k] = tempPositionR[j];
            speed[k] = tempSpeedR[j];
            j++;
            k++;
        }
    }

    public static void printExample(int target, int[] position, int[] speed, String exampleId) {
        System.out.println("carFleet" + exampleId + "=" + carFleet(target, position, speed));
    }

    public static void main(String[] args) {
        printExample(10, new int[]{1, 4}, new int[]{3, 2}, "1");
        printExample(10, new int[]{4, 1, 0, 7}, new int[]{2, 2, 1, 1}, "2");
        printExample(10, new int[]{6, 8}, new int[]{3, 2}, "3");
    }
}
