package arraysandhashing;

import java.util.*;

/*
 * Top K Frequent Elements
 *
 * Given an integer array nums and an integer k, return the k most frequent elements within the array.
 *
 * The test cases are generated such that the answer is always unique.
 *
 * You may return the output in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,3,3,3], k = 2
 * Output: [2,3]
 *
 * Example 2:
 *
 * Input: nums = [7,7], k = 1
 * Output: [7]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^4.
 * -1000 <= nums[i] <= 1000
 * 1 <= k <= number of distinct elements in nums.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(n) space, where n is the size of the input array.
 */
public class TopKFrequentElements {

    // Bucket sort solution.
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> occurrencesHashMap = getOccurrencesHashMap(nums); // (num, occurrence)
        List<Integer>[] occurrencesList = getOccurrencesArray(occurrencesHashMap, nums); // index is occurrence, value is a list of nums that have that occurrence
        return getTopKFrequent(occurrencesList, k);
    }

    public static HashMap<Integer, Integer> getOccurrencesHashMap(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(
                    num,
                    hashMap.getOrDefault(num, 0) + 1
            );
        }
        return hashMap;
    }

    public static List<Integer>[] getOccurrencesArray(HashMap<Integer, Integer> occurrencesHashMap, int[] nums) {
        List<Integer>[] occurrencesList = new List[nums.length + 1];
        for (int i = 0; i < occurrencesList.length; i++) {
            occurrencesList[i] = new ArrayList<>();
        }
        for (int hashMapKey : occurrencesHashMap.keySet()) { // hashMapKey is num
            int hashMapValue = occurrencesHashMap.get(hashMapKey); // hashMapValue is occurrences of num
            List<Integer> numsWithOccurrences = occurrencesList[hashMapValue];
            numsWithOccurrences.add(hashMapKey);
        }
        return occurrencesList;
    }

    public static int[] getTopKFrequent(List<Integer>[] occurrencesArray, int k) {
        int[] topKFrequent = new int[k];
        int topKFrequentInsertIndex = 0;

        for (int occurrences = occurrencesArray.length - 1; occurrences > -1; occurrences--) {
            List<Integer> numsWithOccurrences = occurrencesArray[occurrences];
            for (int num : numsWithOccurrences) {
                topKFrequent[topKFrequentInsertIndex] = num;
                topKFrequentInsertIndex++;
                if (topKFrequentInsertIndex == k) {
                    return topKFrequent;
                }
            }
        }
        return topKFrequent;
    }

    // Primitive solution.
    public static int[] topKFrequentPrimitive(int[] nums, int k) {
        HashMap<Integer, Integer> occurrencesHashMap = new HashMap<>();
        List<Integer> topKFrequentList = new ArrayList<>();
        int topKFrequentListMinimumValue = Integer.MAX_VALUE;
        for (int num : nums) {
            int occurrencesOfNum = putInOccurrencesHashMap(occurrencesHashMap, num);

            if (!topKFrequentList.contains(num)) {
                if (topKFrequentList.size() < k) {
                    topKFrequentList.add(num);
                    topKFrequentListMinimumValue = getValueWithMinOccurrencesInList(topKFrequentList, occurrencesHashMap);
                } else if (occurrencesOfNum > occurrencesHashMap.get(topKFrequentListMinimumValue)) {
                    topKFrequentList.remove((Integer) topKFrequentListMinimumValue);
                    topKFrequentList.add(num);
                    topKFrequentListMinimumValue = getValueWithMinOccurrencesInList(topKFrequentList, occurrencesHashMap);
                }
            } else {
                topKFrequentListMinimumValue = getValueWithMinOccurrencesInList(topKFrequentList, occurrencesHashMap);
            }
        }
        return toIntArray(topKFrequentList);
    }

    public static int[] toIntArray(List<Integer> list) {
        int[] output = new int[list.size()];
        int nextIndex = 0;
        for (int value : list) {
            output[nextIndex] = value;
            nextIndex++;
        }
        return output;
    }

    public static int putInOccurrencesHashMap(HashMap<Integer, Integer> occurrencesHashMap, int i) {
        int occurrences = occurrencesHashMap.getOrDefault(i, 0);
        occurrences++;
        occurrencesHashMap.put(i, occurrences);
        return occurrences;
    }

    public static int getValueWithMinOccurrencesInList(List<Integer> topKFrequentList, HashMap<Integer, Integer> occurrencesHashMap) {
        int minValue = -1;
        int minOccurrences = Integer.MAX_VALUE;
        for (int value : topKFrequentList) {
            int valueOccurrences = occurrencesHashMap.get(value);
            if (valueOccurrences < minOccurrences) {
                minValue = value;
                minOccurrences = valueOccurrences;
            }
        }
        return minValue;
    }

    public static void printExample(int[] nums, int k, String exampleId) {
        int[] topKFrequent = topKFrequent(nums, k);
        System.out.println("topKFrequent" + exampleId + "=" + Arrays.toString(topKFrequent));
    }

    public static void main(String[] args) {
        printExample(
                new int[]{1, 2, 2, 3, 3, 3},
                2,
                "1"
        );
        printExample(
                new int[]{7, 7},
                1,
                "2"
        );
        printExample(
                new int[]{3, 0, 1, 0},
                1,
                "3"
        );
        printExample(
                new int[]{4, 1, -1, 2, -1, 2, 3},
                2,
                "4"
        );
        printExample(
                new int[]{5, 2, 5, 3, 5, 3, 1, 1, 3},
                2,
                "5"
        );
    }
}
