package stack;

import java.util.Objects;
import java.util.Stack;

/*
 * Evaluate Reverse Polish Notation
 *
 * You are given an array of strings tokens that represents a valid arithmetic expression in Reverse Polish Notation.
 *
 * Return the integer that represents the evaluation of the expression.
 *
 * - The operands may be integers or the results of other operations.
 * - The operators include '+', '-', '*', and '/'.
 * - Assume that division between integers always truncates toward zero.
 *
 * Example 1:
 *
 * Input: tokens = ["1","2","+","3","*","4","-"]
 * Output: 5
 * Explanation: ((1 + 2) * 3) - 4 = 5
 *
 * Constraints:
 *
 * 1. 1 <= tokens.length <= 1000.
 * 2. tokens[i] is "+", "-", "*", or "/", or a string representing an integer in the range [-100, 100].
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(n) space, where n is the size of the input array.
 */
public class EvaluateReversePolishNotation {

    public static int evalRPN(String[] tokens) {
        Stack<Integer> operandStack = new Stack<>();
        for (String token : tokens) {
            if (isOperationToken(token)) {
                int operand2 = operandStack.pop();
                int operand1 = operandStack.pop();
                int result = performOperation(operand1, operand2, token);
                operandStack.push(result);
            } else { // assuming token is an operand
                int operandInt = Integer.parseInt(token);
                operandStack.push(operandInt);
            }
        }
        return operandStack.pop();
    }

    public static boolean isOperationToken(String token) {
        return Objects.equals(token, "+")
                || Objects.equals(token, "-")
                || Objects.equals(token, "*")
                || Objects.equals(token, "/");
    }

    public static int performOperation(int operand1, int operand2, String operation) {
        return switch (operation) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            default -> operand1 / operand2; // assuming "/"
        };
    }

    public static void printExample(String[] tokens, String exampleId) {
        System.out.println("evalRPN" + exampleId + "=" + evalRPN(tokens));
    }

    public static void main(String[] args) {
        printExample(new String[]{"1", "2", "+", "3", "*", "4", "-"}, "1");
    }
}
