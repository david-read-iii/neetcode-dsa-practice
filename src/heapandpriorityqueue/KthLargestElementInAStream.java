package heapandpriorityqueue;

import java.util.PriorityQueue;

/*
 * Kth Largest Element in a Stream
 *
 * Design a class to find the kth largest integer in a stream of values, including duplicates. E.g. the 2nd largest from
 * [1, 2, 3, 3] is 3. The stream is not necessarily sorted.
 *
 * Implement the following methods:
 *
 * - constructor(int k, int[] nums) Initializes the object given an integer k and the stream of integers nums.
 * - int add(int val) Adds the integer val to the stream and returns the kth largest integer in the stream.
 *
 * Example 1:
 *
 * Input:
 * ["KthLargest", [3, [1, 2, 3, 3]], "add", [3], "add", [5], "add", [6], "add", [7], "add", [8]]
 * Output:
 * [null, 3, 3, 3, 5, 6]
 * Explanation:
 * KthLargest kthLargest = new KthLargest(3, [1, 2, 3, 3]);
 * kthLargest.add(3);   // return 3
 * kthLargest.add(5);   // return 3
 * kthLargest.add(6);   // return 3
 * kthLargest.add(7);   // return 5
 * kthLargest.add(8);   // return 6
 *
 * Constraints:
 *
 * 1. 1 <= k <= 1000
 * 2. 0 <= nums.length <= 1000
 * 3. -1000 <= nums[i] <= 1000
 * 4. -1000 <= val <= 1000
 * 5. There will always be at least k integers in the stream when you search for the kth integer.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(mlogk) time and O(k) space, where m is the number of times add() is called, and
 * k represents the rank of the largest number to be tracked (i.e., the k-th largest element).
 */
class KthLargest {

    private final int k;
    private final PriorityQueue<Integer> priorityQueue;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        priorityQueue = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (priorityQueue.size() < k) {
            priorityQueue.add(val);
        } else if (val > priorityQueue.peek()) {
            priorityQueue.remove();
            priorityQueue.add(val);
        }
        return priorityQueue.peek();
    }
}

class KthLargestTester {

    public static void runExample1() {
        KthLargest kthLargest = new KthLargest(3, new int[]{1, 2, 3, 3});
        System.out.println(kthLargest.add(3)); // return 3
        System.out.println(kthLargest.add(5)); // return 3
        System.out.println(kthLargest.add(6)); // return 3
        System.out.println(kthLargest.add(7)); // return 5
        System.out.println(kthLargest.add(8)); // return 6
    }

    public static void runExample2() {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3)); // return 4
        System.out.println(kthLargest.add(5)); // return 5
        System.out.println(kthLargest.add(10)); // return 5
        System.out.println(kthLargest.add(9)); // return 8
        System.out.println(kthLargest.add(4)); // return 8
    }

    public static void main(String[] args) {
        runExample1();
        runExample2();
    }
}
