package stack;

import java.util.ArrayList;
import java.util.List;

/*
 * Generate Parentheses
 *
 * You are given an integer n. Return all well-formed parentheses strings that you can generate with n pairs of
 * parentheses.
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: ["()"]
 *
 * Example 2:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * You may return the answer in any order.
 *
 * Constraints:
 *
 * 1 <= n <= 7
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(4^n / sqrt(n)) time and O(n) space, where n is the number of
 * parenthesis pairs in the string.
 */
public class GenerateParentheses {

    public static List<String> generateParenthesis(int n) {
        List<String> parenthesisList = new ArrayList<>();
        generateParenthesisInternal(n, parenthesisList, new StringBuilder(), 0, 0);
        return parenthesisList;
    }

    public static void generateParenthesisInternal(int n, List<String> result, StringBuilder current, int open, int closed) {
        if (open == closed && open == n) {
            result.add(current.toString());
        } else if (open >= closed && open <= n) {
            generateParenthesisInternal(n, result, new StringBuilder(current.toString()).append("("), open + 1, closed);
            generateParenthesisInternal(n, result, new StringBuilder(current.toString()).append(")"), open, closed + 1);
        }
    }

    public static void printExample(int n, String exampleId) {
        System.out.println("generateParenthesis" + exampleId + "=" + generateParenthesis(n));
    }

    public static void main(String[] args) {
        printExample(1, "1");
        printExample(3, "2");
    }
}
