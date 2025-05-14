package stack;

import java.util.EmptyStackException;
import java.util.Stack;

/*
 * Minimum Stack
 *
 * Design a stack class that supports the push, pop, top, and getMin operations.
 *
 * - MinStack() initializes the stack object.
 * - void push(int val) pushes the element val onto the stack.
 * - void pop() removes the element on the top of the stack.
 * - int top() gets the top element of the stack.
 * - int getMin() retrieves the minimum element in the stack.
 *
 * Each function should run in O(1) time.
 *
 * Example 1:
 *
 * Input: ["MinStack", "push", 1, "push", 2, "push", 0, "getMin", "pop", "top", "getMin"]
 * Output: [null,null,null,null,0,null,2,1]
 * Explanation:
 * MinStack minStack = new MinStack();
 * minStack.push(1);
 * minStack.push(2);
 * minStack.push(0);
 * minStack.getMin(); // return 0
 * minStack.pop();
 * minStack.top();    // return 2
 * minStack.getMin(); // return 1
 *
 * Constraints:
 *
 * 1. -2^31 <= val <= 2^31 - 1.
 * 2. pop, top and getMin will always be called on non-empty stacks.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(1) time for each function call and O(n) space, where n is the maximum number of
 * elements present in the stack.
 */
class MinStack {

    private final Stack<Integer> stack;
    private final Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        minStack.push(Math.min(peekOrDefault(minStack, Integer.MAX_VALUE), val));
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    private int peekOrDefault(Stack<Integer> stack, int defaultValue) {
        int peekValue;
        try {
            peekValue = stack.peek();
        } catch (EmptyStackException e) {
            peekValue = defaultValue;
        }
        return peekValue;
    }
}

class MinStackTester {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(2);
        minStack.push(0);
        int min1 = minStack.getMin();
        minStack.pop();
        int top = minStack.top();
        int min2 = minStack.getMin();

        System.out.println("min1=" + min1);
        System.out.println("top=" + top);
        System.out.println("min2=" + min2);
    }
}
