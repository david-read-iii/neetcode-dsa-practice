package linkedlist;

/*
 * Reverse Linked List
 *
 * Given the beginning of a singly linked list head, reverse the list, and return the new beginning of the list.
 *
 * Example 1:
 *
 * Input: head = [0,1,2,3]
 * Output: [3,2,1,0]
 *
 * Example 2:
 *
 * Input: head = []
 * Output: []
 *
 * Constraints:
 *
 * 1. 0 <= The length of the list <= 1000.
 * 2. -1000 <= Node.val <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(1) space, where n is the length of the given list.
 */
class Solution {

    // Time complexity O(n)
    // Space complexity O(1)
    public ListNode reverseList(ListNode head) {
        ListNode previousListNode = null;
        ListNode currentListNode = head;
        while (currentListNode != null) {
            ListNode nextListNode = currentListNode.next;
            currentListNode.next = previousListNode;
            previousListNode = currentListNode;
            currentListNode = nextListNode;
        }
        return previousListNode;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ListNode listNode = this;
        stringBuilder.append("[");
        while (listNode != null) {
            stringBuilder.append(listNode.val);
            if (listNode.next != null) {
                stringBuilder.append(", ");
            }
            listNode = listNode.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

class SolutionTester {

    public static void printExample(ListNode head, String exampleId) {
        Solution solution = new Solution();
        System.out.println("reverseList" + exampleId + "=" + solution.reverseList(head));
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(0);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        printExample(listNode1, "1");
    }
}
