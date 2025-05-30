package binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Time Based Key-Value Store
 *
 * Implement a time-based key-value data structure that supports:
 *
 * - Storing multiple values for the same key at specified time stamps
 * - Retrieving the key's value at a specified timestamp
 *
 * Implement the TimeMap class:
 *
 * - TimeMap() Initializes the object.
 * - void set(String key, String value, int timestamp) Stores the key key with the value value at the given time
 * timestamp.
 * - String get(String key, int timestamp) Returns the most recent value of key if set was previously called on it and
 * the most recent timestamp for that key prev_timestamp is less than or equal to the given timestamp
 * (prev_timestamp <= timestamp). If there are no values, it returns "".
 * Note: For all calls to set, the timestamps are in strictly increasing order.
 *
 * Example 1:
 *
 * Input: ["TimeMap", "set", ["alice", "happy", 1], "get", ["alice", 1], "get", ["alice", 2], "set", ["alice", "sad", 3], "get", ["alice", 3]]
 * Output: [null, null, "happy", "happy", null, "sad"]
 * Explanation:
 * TimeMap timeMap = new TimeMap();
 * timeMap.set("alice", "happy", 1);  // store the key "alice" and value "happy" along with timestamp = 1.
 * timeMap.get("alice", 1);           // return "happy"
 * timeMap.get("alice", 2);           // return "happy", there is no value stored for timestamp 2, thus we return the value at timestamp 1.
 * timeMap.set("alice", "sad", 3);    // store the key "alice" and value "sad" along with timestamp = 3.
 * timeMap.get("alice", 3);           // return "sad"
 *
 * Constraints:
 *
 * 1. 1 <= key.length, value.length <= 100
 * 2. key and value only include lowercase English letters and digits.
 * 3. 1 <= timestamp <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(1) time for set(), O(logn) time for get(), and O(m * n) space, where n is the
 * total number of values associated with a key, and m is the total number of keys.
 */
class TimeMap {

    private final HashMap<String, List<DataModel>> hashMap;

    public TimeMap() {
        hashMap = new HashMap<>();
    }

    // Time complexity: O(1)
    // Space complexity: O(n * m) (n = # of keys; m = # of values associated with each key)
    public void set(String key, String value, int timestamp) {
        List<DataModel> dataModelsAtKey = hashMap.getOrDefault(key, new ArrayList<>());
        dataModelsAtKey.add(new DataModel(value, timestamp));
        hashMap.put(key, dataModelsAtKey);
    }

    // Time complexity: O(log(n))
    // Space complexity: O(n * m) (n = # of keys; m = # of values associated with each key)
    public String get(String key, int timestamp) {
        List<DataModel> dataModelsAtKey = hashMap.getOrDefault(key, new ArrayList<>());
        int l = 0;
        int r = dataModelsAtKey.size() - 1;
        String result = "";

        while (l <= r) {
            int m = ((r - l) / 2) + l;

            if (timestamp < dataModelsAtKey.get(m).timestamp()) {
                r = m - 1; // search left
            } else {
                result = dataModelsAtKey.get(m).value();
                l = m + 1; // search right
            }
        }

        return result;
    }
}

record DataModel(String value, int timestamp) {
}

class TimeMapTester {

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("alice", "happy", 1);
        String s1 = timeMap.get("alice", 1);
        String s2 = timeMap.get("alice", 2);
        timeMap.set("alice", "sad", 3);
        String s3 = timeMap.get("alice", 3);

        System.out.println("s1=" + s1);
        System.out.println("s2=" + s2);
        System.out.println("s3=" + s3);
    }
}
