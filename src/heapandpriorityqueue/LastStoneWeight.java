package heapandpriorityqueue;

import java.util.PriorityQueue;

/*
 Last Stone Weight

 You are given an array of integers stones where stones[i] represents the weight of the ith stone.

 We want to run a simulation on the stones as follows:

 - At each step we choose the two heaviest stones, with weight x and y and smash them togethers
 - If x == y, both stones are destroyed
 - If x < y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.

 Continue the simulation until there is no more than one stone remaining.

 Return the weight of the last remaining stone or return 0 if none remain.

 Example 1:

 Input: stones = [2,3,6,2,4]
 Output: 1
 Explanation:
 - We smash 6 and 4 and are left with a 2, so the array becomes [2,3,2,2].
 - We smash 3 and 2 and are left with a 1, so the array becomes [1,2,2].
 - We smash 2 and 2, so the array becomes [1].

 Example 2:

 Input: stones = [1,2]
 Output: 1

 Constraints:

 1 <= stones.length <= 20
 1 <= stones[i] <= 100

 Recommended Time & Space Complexity

 You should aim for a solution as good or better than O(nlogn) time and O(n) space, where n is the size of the input
 array.
 */
class LastStoneWeight {

    // Time complexity O(n log(n))
    // Space complexity O(n)
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                (o1, o2) -> o2 - o1 // Comparator for MaxHeap.
        );
        for (int stone: stones) {
            maxHeap.add(stone);
        }
        while (maxHeap.size() > 1) {
            int prevLargestStone = maxHeap.poll();
            int prevSecondLargestStone = maxHeap.poll();
            int newLargestStone = prevLargestStone - prevSecondLargestStone;
            if (newLargestStone > 0) {
                maxHeap.add(newLargestStone);
            }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
}

class LastStoneWeightTester {

    public static void printExample(int[] stones, String exampleId) {
        LastStoneWeight lastStoneWeight = new LastStoneWeight();
        System.out.println("lastStoneWeight" + exampleId + "=" + lastStoneWeight.lastStoneWeight(stones));
    }

    public static void main(String[] args) {
        printExample(new int[]{2, 3, 6, 2, 4}, "1");
        printExample(new int[]{1, 2}, "2");
    }
}
