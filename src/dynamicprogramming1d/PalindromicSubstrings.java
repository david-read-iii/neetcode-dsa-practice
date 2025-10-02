package dynamicprogramming1d;

/*
 * Palindromic Substrings
 *
 * Given a string s, return the number of substrings within s that are palindromes.
 *
 * A palindrome is a string that reads the same forward and backward.
 *
 * Example 1:
 *
 * Input: s = "abc"
 * Output: 3
 * Explanation: "a", "b", "c".
 *
 * Example 2:
 *
 * Input: s = "aaa"
 * Output: 6
 * Explanation: "a", "a", "a", "aa", "aa", "aaa". Note that different substrings are counted as different palindromes
 * even if the string contents are the same.
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 1000
 * 2. s consists of lowercase English letters.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n^2) time and O(1) space, where n is the length of the given
 * string.
 */
class PalindromicSubstrings {

    int count = 0;

    // Time complexity O(n^2)
    // Space complexity O(1)
    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            count++;                                // One letter is a palindrome.
            searchForPalindromes(i, i + 1, s);      // Search for even palindromes.
            searchForPalindromes(i - 1, i + 1, s);  // Search for odd palindromes.
        }
        return count;
    }

    public void searchForPalindromes(int startIndex, int endIndex, String s) {
        while (startIndex >= 0 && endIndex < s.length() && s.charAt(startIndex) == s.charAt(endIndex)) {
            count++;
            startIndex--;
            endIndex++;
        }
    }
}

class PalindromicSubstringsTester {

    public static void printExample(String s, String exampleId) {
        PalindromicSubstrings palindromicSubstrings = new PalindromicSubstrings();
        System.out.println("countSubstrings" + exampleId + "=" + palindromicSubstrings.countSubstrings(s));
    }

    public static void main(String[] args) {
        printExample("abc", "1");
        printExample("aaa", "2");
    }
}
