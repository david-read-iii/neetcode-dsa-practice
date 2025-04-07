package arraysandhashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Group Anagrams
 *
 * Given an array of strings strs, group all anagrams together into sublists. You may return the output in any order.
 *
 * An anagram is a string that contains the exact same characters as another string, but the order of the characters can
 * be different.
 *
 * Example 1:
 *
 * Input: strs = ["act","pots","tops","cat","stop","hat"]
 * Output: [["hat"],["act", "cat"],["stop", "pots", "tops"]]
 *
 * Example 2:
 *
 * Input: strs = ["x"]
 * Output: [["x"]]
 *
 * Example 3:
 *
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Constraints:
 *
 * 1 <= strs.length <= 1000.
 * 0 <= strs[i].length <= 100
 * strs[i] is made up of lowercase English letters.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(m * n) time and O(m) space, where m is the number of strings and n is the length
 * of the longest string.
 */
public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<HashMap<Character, Integer>, List<String>> hashMap = new HashMap<>();
        for (String s : strs) {
            HashMap<Character, Integer> characterOccurrencesHashMap = getCharacterOccurrencesHashMap(s);
            List<String> anagrams = hashMap.getOrDefault(characterOccurrencesHashMap, new ArrayList<>());
            anagrams.add(s);
            hashMap.put(characterOccurrencesHashMap, anagrams);
        }
        return new ArrayList<>(hashMap.values());
    }

    public static HashMap<Character, Integer> getCharacterOccurrencesHashMap(String s) {
        HashMap<Character, Integer> characterOccurrencesHashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            int previousCharacterOccurrence = characterOccurrencesHashMap.getOrDefault(character, 0);
            int newCharacterOccurrence = previousCharacterOccurrence + 1;
            characterOccurrencesHashMap.put(character, newCharacterOccurrence);
        }
        return characterOccurrencesHashMap;
    }

    public static void printExample(String[] strs, String exampleId) {
        List<List<String>> groupAnagrams = groupAnagrams(strs);
        System.out.println("groupAnagrams" + exampleId + "=" + groupAnagrams);
    }

    public static void main(String[] args) {
        printExample(
                new String[]{"act", "pots", "tops", "cat", "stop", "hat"},
                "1"
        );
        printExample(
                new String[]{"x"},
                "2"
        );
        printExample(
                new String[]{""},
                "3"
        );
    }
}
