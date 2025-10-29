package dynamicprogramming1d;

import java.util.HashMap;
import java.util.List;

/*
 * Word Break
 *
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated
 * sequence of dictionary words.
 *
 * You are allowed to reuse words in the dictionary an unlimited number of times. You may assume all dictionary words
 * are unique.
 *
 * Example 1:
 *
 * Input: s = "neetcode", wordDict = ["neet","code"]
 * Output: true
 * Explanation: Return true because "neetcode" can be split into "neet" and "code".
 *
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen","ape"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be split into "apple", "pen" and "apple". Notice that we can
 * reuse words and also not use all the words.
 *
 * Example 3:
 *
 * Input: s = "catsincars", wordDict = ["cats","cat","sin","in","car"]
 * Output: false
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 200
 * 2. 1 <= wordDict.length <= 100
 * 3. 1 <= wordDict[i].length <= 20
 * 4. s and wordDict[i] consist of only lowercase English letters.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n * m * t) time and O(n) space, where n is the length of the
 * string s, m is the number of words in wordDict, and t is the maximum length of any word in wordDict.
 */
class WordBreak {

    // Time complexity: O(n * m * t)
    // Space complexity: O(n)
    public boolean wordBreak(String s, List<String> wordDict) {
        HashMap<Integer, Boolean> cache = new HashMap<>();
        return canBreak(0, s, wordDict, cache);
    }

    /**
     * Returns true if the substring starting at start can be broken down into substrings of wordDict.
     */
    public boolean canBreak(int start, String s, List<String> wordDict, HashMap<Integer, Boolean> cache) {
        if (cache.containsKey(start)) {
            return cache.get(start);
        }
        if (start == s.length()) {
            return true;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            String substring = s.substring(start, end);
            if (wordDict.contains(substring)) {
                if (canBreak(end, s, wordDict, cache)) {
                    cache.put(start, true);
                    return true;
                }
            }
        }
        cache.put(start, false);
        return false;
    }
}

class WordBreakTester {

    public static void printExample(String s, List<String> wordDict, String exampleId) {
        WordBreak wordBreak = new WordBreak();
        System.out.println("wordBreak" + exampleId + "=" + wordBreak.wordBreak(s, wordDict));
    }

    public static void main(String[] args) {
        printExample("neetcode", List.of("neet", "code"), "1");
        printExample("applepenapple", List.of("apple", "pen", "ape"), "2");
        printExample("catsincars", List.of("cats", "cat", "sin", "in", "car"), "3");
    }
}
