package backtracking;

import java.util.*;

/*
 * Letter Combinations of a Phone Number
 *
 * You are given a string digits made up of digits from 2 through 9 inclusive.
 *
 * Each digit (not including 1) is mapped to a set of characters as shown below:
 *
 * A digit could represent any one of the characters it maps to.
 *
 * Return all possible letter combinations that digits could represent. You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: digits = "34"
 * Output: ["dg","dh","di","eg","eh","ei","fg","fh","fi"]
 *
 * Example 2:
 *
 * Input: digits = ""
 * Output: []
 *
 * Constraints:
 *
 * 1. 0 <= digits.length <= 4
 * 2. 2 <= digits[i] <= 9
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n * (4^n)) time and O(n) space, where n is the length of the input string.
 */
class LetterCombinationsOfAPhoneNumber {

    private final Map<Character, Set<Character>> DIGIT_TO_CHARS_HASH_MAP = Map.of(
            '2', Set.of('a', 'b', 'c'),
            '3', Set.of('d', 'e', 'f'),
            '4', Set.of('g', 'h', 'i'),
            '5', Set.of('j', 'k', 'l'),
            '6', Set.of('m', 'n', 'o'),
            '7', Set.of('p', 'q', 'r', 's'),
            '8', Set.of('t', 'u', 'v'),
            '9', Set.of('w', 'x', 'y', 'z')
    );

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> resultList = new ArrayList<>();
        letterCombinationsInternal(digits, 0, new StringBuilder(), resultList);
        return resultList;
    }

    public void letterCombinationsInternal(String digits, int index, StringBuilder currentResult, List<String> resultList) {
        if (currentResult.length() == digits.length()) {
            resultList.add(currentResult.toString());
            return;
        }
        char currentDigit = digits.charAt(index);
        Set<Character> currentChars = DIGIT_TO_CHARS_HASH_MAP.get(currentDigit);
        for (char currentChar : currentChars) {
            currentResult.append(currentChar);
            letterCombinationsInternal(digits, index + 1, currentResult, resultList);
            currentResult.deleteCharAt(currentResult.length() - 1);
        }
    }
}

class LetterCombinationsOfAPhoneNumberTester {

    public static void printExample(String digits, String exampleId) {
        LetterCombinationsOfAPhoneNumber letterCombinationsOfAPhoneNumber = new LetterCombinationsOfAPhoneNumber();
        System.out.println("letterCombinations" + exampleId + "=" + letterCombinationsOfAPhoneNumber.letterCombinations(digits));
    }

    public static void main(String[] args) {
        printExample("34", "1");
        printExample("", "2");
    }
}
