package dynamicprogramming1d;

/*
 * Longest Palindromic Substring
 *
 * Given a string s, return the longest substring of s that is a palindrome.
 *
 * A palindrome is a string that reads the same forward and backward.
 *
 * If there are multiple palindromic substrings that have the same length, return any one of them.
 *
 * Example 1:
 *
 * Input: s = "ababd"
 * Output: "bab"
 * Explanation: Both "aba" and "bab" are valid answers.
 *
 * Example 2:
 *
 * Input: s = "abbc"
 * Output: "bb"
 * Constraints:
 *
 * 1. 1 <= s.length <= 1000
 * 2. s contains only digits and English letters.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n^2) time and O(1) space, where n is the length of the given
 * string.
 */
class LongestPalindromicSubstring {

    int longestLength = 0;
    int startIndex = 0;
    int endIndex = 0;

    // Time complexity O(n^2)
    // Space complexity O(1)
    public String longestPalindrome(String s) {
        for (int centerI = 0; centerI < s.length(); centerI++) {
            checkForPalindromes(centerI - 1, centerI, s); // check for evens
            checkForPalindromes(centerI - 1, centerI + 1, s); // check for odds
        }
        return s.substring(startIndex, endIndex + 1);
    }

    private void checkForPalindromes(int startIndex, int endIndex, String s) {
        while (startIndex >= 0 && endIndex < s.length() && s.charAt(startIndex) == s.charAt(endIndex)) {
            logPalindrome(startIndex, endIndex);
            startIndex--;
            endIndex++;
        }
    }

    private void logPalindrome(int startIndex, int endIndex) {
        int currentLength = endIndex - startIndex + 1;
        if (currentLength > longestLength) {
            longestLength = currentLength;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }
}

class LongestPalindromicSubstringTester {

    public static void printExample(String s, String exampleId) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        System.out.println("longestPalindrome" + exampleId + "=" + longestPalindromicSubstring.longestPalindrome(s));
    }

    public static void main(String[] args) {
        printExample("ababd", "1");
        printExample("abbc", "2");
    }
}
