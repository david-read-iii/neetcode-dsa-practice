package linkedlist;

/*
 * Linked List Cycle Detection
 *
 * Given the beginning of a linked list head, return true if there is a cycle in the linked list. Otherwise, return
 * false.
 *
 * There is a cycle in a linked list if at least one node in the list can be visited again by following the next
 * pointer.
 *
 * Internally, index determines the index of the beginning of the cycle, if it exists. The tail node of the list will
 * set it's next pointer to the index-th node. If index = -1, then the tail node points to null and no cycle exists.
 *
 * Note: index is not given to you as a parameter.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4], index = 1
 * Output: true
 *
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 *
 * Example 2:
 *
 * Input: head = [1,2], index = -1
 * Output: false
 *
 * Constraints:
 *
 * 1. 1 <= Length of the list <= 1000.
 * 2. -1000 <= Node.val <= 1000
 * 3. index is -1 or a valid index in the linked list.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(1) space, where n is the length of the given list.
 */
class LinkedListCycleDetection {

    public boolean hasCycle(ListNode head) {
        if (head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast.next != null && fast.next.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }
}

class LinkedListCycleDetectionTester {

    public static void printExample(ListNode head, String exampleId) {
        LinkedListCycleDetection linkedListCycleDetection = new LinkedListCycleDetection();
        System.out.println("hasCycle" + exampleId + "=" + linkedListCycleDetection.hasCycle(head));
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode2;
        printExample(listNode1, "1");

        printExample(new ListNode(1, new ListNode(2)), "2");
    }
}
