package arraysandhashing;

import java.util.HashSet;

/*
 * Contains Duplicate
 *
 * Given an integer array nums, return true if any value appears more than once in the array, otherwise return
 * false.
 *
 * Example 1:
 * Input: nums = [1, 2, 3, 3]
 * Output: true
 *
 * Example 2:
 * Input: nums = [1, 2, 3, 4]
 * Output: false
 */
public class ContainsDuplicate {

    public static boolean containsDuplicate(int[] array) {
        HashSet<Integer> occurrences = new HashSet<>();
        for (int value : array) {
            boolean isDuplicate = occurrences.contains(value);
            if (isDuplicate) {
                return true;
            } else {
                occurrences.add(value);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Example 1
        int[] array1 = new int[]{1, 2, 3, 3};
        boolean containsDuplicate1 = containsDuplicate(array1);
        System.out.println("containsDuplicate1=" + containsDuplicate1);

        // Example 2
        int[] array2 = new int[]{1, 2, 3, 4};
        boolean containsDuplicate2 = containsDuplicate(array2);
        System.out.println("containsDuplicate2=" + containsDuplicate2);
    }
}
