package heapandpriorityqueue;

import java.util.PriorityQueue;

/*
 * Kth Largest Element in an Array
 *
 * Given an unsorted array of integers nums and an integer k, return the kth largest element in the array.
 *
 * By kth largest element, we mean the kth largest element in the sorted order, not the kth distinct element.
 *
 * Follow-up: Can you solve it without sorting?
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,5,4], k = 2
 * Output: 4
 *
 * Example 2:
 *
 * Input: nums = [2,3,1,1,5,5,4], k = 3
 * Output: 4
 *
 * Constraints:
 *
 * 1. 1 <= k <= nums.length <= 10000
 * 2. -1000 <= nums[i] <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(nlogk) time and O(k) space, where n is the size of the input
 * array, and k represents the rank of the largest number to be returned (i.e., the k-th largest element).
 */
class KthLargestElementInAnArray {

    // Time complexity O( n log(k) )
    // Space complexity O(k)
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.add(num);
            } else if (minHeap.peek() < num) {
                minHeap.poll();
                minHeap.add(num);
            }
        }
        return minHeap.peek();
    }
}

class KthLargestElementInAnArrayTester {

    public static void printExample(int[] nums, int k, String exampleId) {
        KthLargestElementInAnArray kthLargestElementInAnArray = new KthLargestElementInAnArray();
        System.out.println("findKthLargest" + exampleId + "=" + kthLargestElementInAnArray.findKthLargest(nums, k));
    }

    public static void main(String[] args) {
        printExample(new int[]{2, 3, 1, 5, 4}, 2, "1");
        printExample(new int[]{2, 3, 1, 1, 5, 5, 4}, 3, "2");
    }
}
