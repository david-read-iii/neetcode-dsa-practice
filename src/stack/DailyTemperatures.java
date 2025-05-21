package stack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

/*
 * Daily Temperatures
 *
 * You are given an array of integers temperatures where temperatures[i] represents the daily temperatures on the ith
 * day.
 *
 * Return an array result where result[i] is the number of days after the ith day before a warmer temperature appears on
 * a future day. If there is no day in the future where a warmer temperature will appear for the ith day, set result[i]
 * to 0 instead.
 *
 * Example 1:
 *
 * Input: temperatures = [30,38,30,36,35,40,28]
 * Output: [1,4,1,2,1,0,0]
 *
 * Example 2:
 *
 * Input: temperatures = [22,21,20]
 * Output: [0,0,0]
 *
 * Constraints:
 *
 * 1 <= temperatures.length <= 1000.
 * 1 <= temperatures[i] <= 100
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n) time and O(n) space, where n is the size of the input
 * array.
 */
public class DailyTemperatures {

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] dailyTemperatures = new int[temperatures.length];
        Stack<Integer> unsetIndices = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            if (isIncreaseTempFromPreviousTemp(temperatures, i)) {
                while (stackHasTopTempLessThanCurrentTemp(unsetIndices, temperatures, i)) {
                    int indexToSet = unsetIndices.pop();
                    int valueToSet = i - indexToSet;
                    dailyTemperatures[indexToSet] = valueToSet;
                }
            }

            unsetIndices.push(i);
        }

        return dailyTemperatures;
    }

    public static boolean isIncreaseTempFromPreviousTemp(int[] temperatures, int i) {
        return i > 0 && temperatures[i] > temperatures[i - 1];
    }

    public static boolean stackHasTopTempLessThanCurrentTemp(Stack<Integer> unsetIndices, int[] temperatures, int i) {
        try {
            return temperatures[unsetIndices.peek()] < temperatures[i];
        } catch (EmptyStackException e) {
            return false;
        }
    }

    public static void printExample(int[] temperatures, String exampleId) {
        System.out.println("dailyTemperatures" + exampleId + "=" + Arrays.toString(dailyTemperatures(temperatures)));
    }

    public static void main(String[] args) {
        printExample(new int[]{30, 38, 30, 36, 35, 40, 28}, "1");
        printExample(new int[]{22, 21, 20}, "2");
    }
}
