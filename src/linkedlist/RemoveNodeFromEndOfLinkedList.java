package linkedlist;

/*
 * Remove Node From End of Linked List
 *
 * You are given the beginning of a linked list head, and an integer n.
 *
 * Remove the nth node from the end of the list and return the beginning of the list.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4], n = 2
 * Output: [1,2,4]
 *
 * Example 2:
 *
 * Input: head = [5], n = 1
 * Output: []
 *
 * Example 3:
 *
 * Input: head = [1,2], n = 2
 * Output: [2]
 *
 * Constraints:
 *
 * 1. The number of nodes in the list is sz.
 * 2. 1 <= sz <= 30
 * 3. 0 <= Node.val <= 100
 * 4. 1 <= n <= sz
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(N) time and O(1) space, where N is the length of the given list.
 */
class RemoveNodeFromEndOfLinkedList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = dummy;

        // Move fast ahead by n + 1 steps.
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both until fast reaches the end.
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow is pointing at the node that needs to be repointed.
        slow.next = slow.next.next;

        return dummy.next;
    }
}

class RemoveNodeFromEndOfLinkedListTester {

    public static void printExample(ListNode head, int n, String exampleId) {
        RemoveNodeFromEndOfLinkedList removeNodeFromEndOfLinkedList = new RemoveNodeFromEndOfLinkedList();
        System.out.println("removeNthFromEnd" + exampleId + "=" + removeNodeFromEndOfLinkedList.removeNthFromEnd(head, n));
    }

    public static void main(String[] args) {
        printExample(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))), 2, "1");
        printExample(new ListNode(5), 1, "2");
        printExample(new ListNode(1, new ListNode(2)), 2, "3");
        printExample(new ListNode(1, new ListNode(2)), 1, "4");
    }
}
