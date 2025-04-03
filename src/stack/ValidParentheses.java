package stack;

import java.util.Stack;

/*
 Valid Parentheses

 You are given a string s consisting of the following characters: '(', ')', '{', '}', '[' and ']'.

 The input string s is valid if and only if:

 1. Every open bracket is closed by the same type of close bracket.
 2. Open brackets are closed in the correct order.
 3. Every close bracket has a corresponding open bracket of the same type.

 Return true if s is a valid string, and false otherwise.

 Example 1:

 Input: s = "[]"
 Output: true

 Example 2:

 Input: s = "([{}])"
 Output: true

 Example 3:

 Input: s = "[(])"
 Output: false
 Explanation: The brackets are not closed in the correct order.

 Constraints:

 1 <= s.length <= 1000

 Recommended Time & Space Complexity

 You should aim for a solution with O(n) time and O(n) space, where n is the length of the given string.
 */
public class ValidParentheses {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char selectedChar = s.charAt(i);

            if (isOpenBracket(selectedChar)) {
                stack.push(selectedChar);
            } else { // Assumed that selectedChar is a closing bracket
                char correspondingOpeningBracketChar = toOpeningBracketChar(selectedChar);

                if (isNotEmpty(stack) && stack.peek() == correspondingOpeningBracketChar) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static boolean isOpenBracket(char c) {
        return c == '(' || c == '{' || c == '[';
    }

    public static char toOpeningBracketChar(char closingBracketChar) {
        return switch (closingBracketChar) {
            case ')' -> '(';
            case '}' -> '{';
            default -> '['; // Assumed that closingBracketChar is ']'
        };
    }

    public static boolean isNotEmpty(Stack<Character> stack) {
        return !stack.isEmpty();
    }

    public static void printExample(String s, String exampleId) {
        boolean isValid = isValid(s);
        System.out.println("isValid" + exampleId + "=" + isValid);
    }

    public static void main(String[] args) {
        // Example 1
        printExample("[]", "1");

        // Example 2
        printExample("([{}])", "2");

        // Example 3
        printExample("[(])", "3");

        // Example 4
        printExample("]", "4");
    }
}
