package backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * Palindrome Partitioning
 *
 * Given a string s, split s into substrings where every substring is a palindrome. Return all possible lists of
 * palindromic substrings.
 *
 * You may return the solution in any order.
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 *
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 *
 * Constraints:
 *
 * 1. 1 <= s.length <= 20
 * 2. s contains only lowercase English letters.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n * (2^n)) time and O(n) space, where n is the length of the input string.
 */
class PalindromePartitioning {

    // Time complexity O(n * (2^n))
    // Space complexity O(n)
    public List<List<String>> partition(String s) {
        List<List<String>> allResultsList = new ArrayList<>();
        partitionInternal(s, 0, new ArrayList<>(), allResultsList);
        return allResultsList;
    }

    public void partitionInternal(String s, int currentIndex, List<String> currentResultList, List<List<String>> allResultsList) {

        // Prune if last substring is not a palindrome.
        if (!isLastStringAPalindrome(currentResultList)) {
            return;
        }

        if (currentIndex == s.length()) {
            // At this point, we know all substrings in currentResultList are palindromes and we have processed all letters in s.
            allResultsList.add(new ArrayList<>(currentResultList));
            return;
        }

        if (currentIndex == 0) {
            // Always just add char to new string when at beginning.
            appendToNewString(s.substring(currentIndex, currentIndex + 1), currentResultList);
            partitionInternal(s, 1, currentResultList, allResultsList);
        } else {
            // Try to add char to new string first.
            appendToNewString(s.substring(currentIndex, currentIndex + 1), currentResultList);
            partitionInternal(s, currentIndex + 1, currentResultList, allResultsList);

            // Backtrack.
            removeLatestFromNewString(currentResultList);

            // Now try to add char to same string.
            appendToLastString(s.substring(currentIndex, currentIndex + 1), currentResultList);
            partitionInternal(s, currentIndex + 1, currentResultList, allResultsList);
        }
    }

    public void appendToLastString(String s, List<String> currentResultList) {
        String lastString = currentResultList.remove(currentResultList.size() - 1);
        lastString = lastString.concat(s);
        currentResultList.add(lastString);
    }

    public void appendToNewString(String s, List<String> currentResultList) {
        currentResultList.add(s);
    }

    public void removeLatestFromNewString(List<String> currentResultList) {
        currentResultList.remove(currentResultList.size() - 1);
    }

    public boolean isLastStringAPalindrome(List<String> currentResultList) {
        String lastString = currentResultList.isEmpty() ? "" : currentResultList.get(currentResultList.size() - 1);
        if (isPalindrome(lastString)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return true;
        } else { // Assuming s.length() > 1
            int l = 0;
            int r = s.length() - 1;
            while (l < r) {
                if (s.charAt(l) != s.charAt(r)) {
                    return false;
                }
                l++;
                r--;
            }
            return true;
        }
    }
}

class PalindromePartitioningTester {

    public static void printExample(String s, String exampleId) {
        PalindromePartitioning palindromePartitioning = new PalindromePartitioning();
        System.out.println("partition" + exampleId + "=" + palindromePartitioning.partition(s));
    }

    public static void main(String[] args) {
        printExample("aab", "1");
        printExample("a", "2");
    }
}
