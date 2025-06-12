package linkedlist;

/*
 * Add Two Numbers
 *
 * You are given two non-empty linked lists, l1 and l2, where each represents a non-negative integer.
 *
 * The digits are stored in reverse order, e.g. the number 123 is represented as 3 -> 2 -> 1 -> in the linked list.
 *
 * Each of the nodes contains a single digit. You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 *
 * Return the sum of the two numbers as a linked list.
 *
 * Example 1:
 *
 * Input: l1 = [1,2,3], l2 = [4,5,6]
 * Output: [5,7,9]
 * Explanation: 321 + 654 = 975.
 *
 * Example 2:
 *
 * Input: l1 = [9], l2 = [9]
 * Output: [8,1]
 *
 * Constraints:
 *
 * 1. 1 <= l1.length, l2.length <= 100.
 * 2. 0 <= Node.val <= 9
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(m + n) time and O(1) space, where m is the length of list l1 and n is the length
 * of list l2.
 */
class AddTwoNumbers {

    // TODO: Rewrite carry logic to be more readable.
    // Time complexity O(n)
    // Space complexity O(1)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultCurr = new ListNode(0);
        ListNode resultHead = resultCurr;

        while (l1 != null || l2 != null) {

            if (resultCurr.next == null) {
                resultCurr.next = new ListNode(0);
            }
            resultCurr = resultCurr.next;

            int sum = getListNodeValue(l1) + getListNodeValue(l2) + getValueAtResultCurr(resultCurr);

            if (sum >= 10) {
                resultCurr.val = getSinglesPlace(sum);
                if (resultCurr.next == null) {
                    resultCurr.next = new ListNode(0);
                }
                resultCurr.next.val = getTensPlace(sum);
            } else {
                resultCurr.val = sum;
            }

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return resultHead.next;
    }

    public int getListNodeValue(ListNode listNode) {
        if (listNode == null) {
            return 0;
        } else {
            return listNode.val;
        }
    }

    public int getValueAtResultCurr(ListNode resultCurr) {
        if (resultCurr.next == null) {
            return resultCurr.val;
        } else {
            return resultCurr.val + (resultCurr.next.val * 10);
        }
    }

    public int getSinglesPlace(int sum) {
        return sum % 10;
    }

    public int getTensPlace(int sum) {
        return sum / 10;
    }
}

class AddTwoNumbersTester {

    public static void printExample(ListNode l1, ListNode l2, String exampleId) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        System.out.println("addTwoNumbers" + exampleId + "=" + addTwoNumbers.addTwoNumbers(l1, l2));
    }

    public static void main(String[] args) {
        printExample(
                new ListNode(1, new ListNode(2, new ListNode(3))),
                new ListNode(4, new ListNode(5, new ListNode(6))),
                "1"
        );
        printExample(
                new ListNode(9),
                new ListNode(9),
                "2"
        );
        printExample(
                new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))))))),
                new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))),
                "3"
        );
        printExample(
                new ListNode(9, new ListNode(9, new ListNode(9))),
                new ListNode(1),
                "4"
        );
    }
}
