package slidingwindow;

import java.util.HashMap;

/*
 * Permutation in String
 *
 * You are given two strings s1 and s2.
 *
 * Return true if s2 contains a permutation of s1, or false otherwise. That means if a permutation of s1 exists as a
 * substring of s2, then return true.
 *
 * Both strings only contain lowercase letters.
 *
 * Example 1:
 *
 * Input: s1 = "abc", s2 = "lecabee"
 * Output: true
 * Explanation: The substring "cab" is a permutation of "abc" and is present in "lecabee".
 *
 * Example 2:
 *
 * Input: s1 = "abc", s2 = "lecaabee"
 * Output: false
 *
 * Constraints:
 *
 * 1 <= s1.length, s2.length <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(1) space, where n is the maximum of the lengths of the two
 * strings.
 */
public class PermutationInString {

    public static boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> s1CharacterFrequencies = new HashMap<>();
        for (char c : s1.toCharArray()) {
            putCharacterFrequency(c, s1CharacterFrequencies);
        }

        int l = 0;
        HashMap<Character, Integer> s2SubstringCharacterFrequencies = new HashMap<>();
        for (int r = 0; r < s2.length(); r++) {
            putCharacterFrequency(s2.charAt(r), s2SubstringCharacterFrequencies);

            if (getSubstringLength(l, r) > s1.length()) {
                removeCharacterFrequency(s2.charAt(l), s2SubstringCharacterFrequencies);
                l++;
            }

            if (areCharacterFrequencyHashMapsEqual(s1CharacterFrequencies, s2SubstringCharacterFrequencies)) {
                return true;
            }
        }

        return false;
    }

    public static void putCharacterFrequency(char c, HashMap<Character, Integer> characterFrequencies) {
        characterFrequencies.put(c, getCharacterFrequency(c, characterFrequencies) + 1);
    }

    public static void removeCharacterFrequency(char c, HashMap<Character, Integer> characterFrequencies) {
        int countFrequencyToSet = getCharacterFrequency(c, characterFrequencies) - 1;
        if (countFrequencyToSet == 0) {
            characterFrequencies.remove(c);
        } else {
            characterFrequencies.put(c, countFrequencyToSet);
        }
    }

    public static int getCharacterFrequency(char c, HashMap<Character, Integer> characterFrequencies) {
        return characterFrequencies.getOrDefault(c, 0);
    }

    public static int getSubstringLength(int l, int r) {
        return r - l + 1;
    }

    public static boolean areCharacterFrequencyHashMapsEqual(HashMap<Character, Integer> hashMap1, HashMap<Character, Integer> hashMap2) {
        if (hashMap1.size() != hashMap2.size()) {
            return false;
        }

        for (char c : hashMap1.keySet()) {
            if (!hashMap2.containsKey(c)) {
                return false;
            }
            if (!hashMap1.get(c).equals(hashMap2.get(c))) {
                return false;
            }
        }

        return true;
    }

    public static void printExample(String s1, String s2, String exampleId) {
        System.out.println("checkInclusion" + exampleId + "=" + checkInclusion(s1, s2));
    }

    public static void main(String[] args) {
        printExample("abc", "lecabee", "1");
        printExample("abc", "lecaabee", "2");
    }
}
