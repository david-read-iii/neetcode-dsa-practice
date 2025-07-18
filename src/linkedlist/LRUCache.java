package linkedlist;

import java.util.HashMap;

/*
 * LRU Cache
 *
 * Implement the Least Recently Used (LRU) cache class LRUCache. The class should support the following operations
 *
 * - LRUCache(int capacity) Initialize the LRU cache of size capacity.
 * - int get(int key) Return the value corresponding to the key if the key exists, otherwise return -1.
 * - void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to
 *   the cache. If the introduction of the new pair causes the cache to exceed its capacity, remove the least recently
 *   used key.
 *
 * A key is considered used if a get or a put operation is called on it.
 *
 * Ensure that get and put each run in O(1) average time complexity.
 *
 * Example 1:
 *
 * Input:
 * ["LRUCache", [2], "put", [1, 10],  "get", [1], "put", [2, 20], "put", [3, 30], "get", [2], "get", [1]]
 * Output:
 * [null, null, 10, null, null, 20, -1]
 * Explanation:
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 10);  // cache: {1=10}
 * lRUCache.get(1);      // return 10
 * lRUCache.put(2, 20);  // cache: {1=10, 2=20}
 * lRUCache.put(3, 30);  // cache: {2=20, 3=30}, key=1 was evicted
 * lRUCache.get(2);      // returns 20
 * lRUCache.get(1);      // return -1 (not found)
 *
 * Constraints:
 *
 * 1. 1 <= capacity <= 100
 * 2. 0 <= key <= 1000
 * 3. 0 <= value <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(1) time for each put() and get() function call and an overall space of O(n),
 * where n is the capacity of the LRU cache.
 */
class LRUCache {

    public LRUCache(int capacity) {

    }

    public int get(int key) {
        return 0;
    }

    public void put(int key, int value) {

    }
}

class LRUCacheTester {

    public static void testCase1() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 10);                // cache: {1=10}
        int return1 = lRUCache.get(1);      // return 10
        lRUCache.put(2, 20);                // cache: {1=10, 2=20}
        lRUCache.put(3, 30);                // cache: {2=20, 3=30}, key=1 was evicted
        int return2 = lRUCache.get(2);      // returns 20
        int return3 = lRUCache.get(1);      // return -1 (not found)

        System.out.println("return1=" + return1);
        System.out.println("return2=" + return2);
        System.out.println("return3=" + return3);
    }


    public static void testCase2() {
        LRUCache lRUCache = new LRUCache(2);
        int return1 = lRUCache.get(2); // return -1
        lRUCache.put(2, 6);
        int return2 = lRUCache.get(1); // return -1
        lRUCache.put(1, 5);
        lRUCache.put(1, 2);
        int return3 = lRUCache.get(1); // return 2
        int return4 = lRUCache.get(2); // return 6

        System.out.println("return1=" + return1);
        System.out.println("return2=" + return2);
        System.out.println("return3=" + return3);
        System.out.println("return4=" + return4);
    }

    public static void testCase3() {
        LRUCache lRUCache = new LRUCache(4);
        lRUCache.put(1, 1);
        lRUCache.put(2, 2);
        lRUCache.put(3, 3);
        lRUCache.get(1); // return 1
        lRUCache.get(2); // return 2
        lRUCache.get(4); // return -1
        lRUCache.put(4, 4);
        lRUCache.get(1); // return 1
        lRUCache.get(2); // return 2
        lRUCache.get(3); // return 3
        lRUCache.get(4); // return 4
        lRUCache.get(2); // return 2
        lRUCache.put(5, 5);
        lRUCache.get(1);
        lRUCache.get(2);
        lRUCache.get(3);
        lRUCache.get(4);
        lRUCache.get(5);
        lRUCache.get(2);
        lRUCache.get(3);
        lRUCache.get(4);
        lRUCache.put(6, 6);
        lRUCache.get(1);
        lRUCache.get(2);
        lRUCache.get(3);
        lRUCache.get(4);
        lRUCache.get(5);
        lRUCache.get(6);
    }

    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
    }
}
