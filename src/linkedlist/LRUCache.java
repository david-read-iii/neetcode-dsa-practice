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

    private int capacity;
    private HashMap<Integer, LruCacheNode> cache;
    private LruCacheNode head;
    private LruCacheNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new LruCacheNode(0, 0);
        this.tail = new LruCacheNode(0, 0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    // Time complexity O(1)
    // Space complexity O(n)
    public int get(int key) {
        if (cache.containsKey(key)) {
            LruCacheNode node = cache.get(key);
            removeNode(node);
            insertNode(node);
            return node.val;
        }
        return -1;
    }

    // Time complexity O(1)
    // Space complexity O(n)
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            removeNode(cache.get(key));
        }
        LruCacheNode newNode = new LruCacheNode(key, value);
        cache.put(key, newNode);
        insertNode(newNode);

        if (cache.size() > capacity) {
            LruCacheNode leastRecentlyUpdatedNode = head.next;
            removeNode(leastRecentlyUpdatedNode);
            cache.remove(leastRecentlyUpdatedNode.key);
        }
    }

    private void removeNode(LruCacheNode node) {
        LruCacheNode prevNode = node.prev;
        LruCacheNode nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void insertNode(LruCacheNode node) {
        LruCacheNode prevNode = tail.prev;
        prevNode.next = node;
        node.prev = prevNode;
        node.next = tail;
        tail.prev = node;
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

class LruCacheNode {
    int key;
    int val;
    LruCacheNode prev;
    LruCacheNode next;

    LruCacheNode() {
    }

    public LruCacheNode(int key, int val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        LruCacheNode lruCacheNode = this;
        stringBuilder.append("[");
        while (lruCacheNode.prev != null) {
            lruCacheNode = lruCacheNode.prev;
        }
        while (lruCacheNode != null) {
            stringBuilder.append("(").append(lruCacheNode.key).append(", ").append(lruCacheNode.val).append(")");
            if (lruCacheNode.next != null) {
                stringBuilder.append(", ");
            }
            lruCacheNode = lruCacheNode.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
