package linkedlist;

/*
 * Reorder Linked List
 *
 * You are given the head of a singly linked-list.
 *
 * The positions of a linked list of length = 7 for example, can intially be represented as:
 *
 * [0, 1, 2, 3, 4, 5, 6]
 *
 * Reorder the nodes of the linked list to be in the following order:
 *
 * [0, 6, 1, 5, 2, 4, 3]
 *
 * Notice that in the general case for a list of length = n the nodes are reordered to be in the following order:
 *
 * [0, n-1, 1, n-2, 2, n-3, ...]
 *
 * You may not modify the values in the list's nodes, but instead you must reorder the nodes themselves.
 *
 * Example 1:
 *
 * Input: head = [2,4,6,8]
 * Output: [2,8,4,6]
 *
 * Example 2:
 *
 * Input: head = [2,4,6,8,10]
 * Output: [2,10,4,8,6]
 *
 * Constraints:
 *
 * 1. 1 <= Length of the list <= 1000.
 * 2. 1 <= Node.val <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(1) space, where n is the length of the given list.
 */
class ReorderLinkedList {

    // Time complexity: O(n)
    // Space complexity: O(1)
    public void reorderList(ListNode head) {
        ListNode list1 = head;

        // Find list2.
        ListNode slowPrev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            slowPrev = slow;
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }

        // Slow pointer now sitting at beginning of list2!

        ListNode endOfList1 = slowPrev;
        ListNode endOfList2 = slow;

        // Remove connection between endOfList1 and endOfList2.
        endOfList1.next = null;

        // Reverse endOfList2 list to get list2.
        ListNode list2 = reverseLinkedList(endOfList2);

        // Setup new pointers between list1 and list2.
        while (list2 != null) {
            ListNode list1Next = list1.next;
            ListNode list2Next = list2.next;

            list2.next = list1.next;
            list1.next = list2;

            list1 = list1Next;
            list2 = list2Next;
        }
    }

    public ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}

class ReorderLinkedListTester {

    public static void printExample(ListNode head, String exampleId) {
        ReorderLinkedList reorderLinkedList = new ReorderLinkedList();
        reorderLinkedList.reorderList(head);
        System.out.println("reorderList" + exampleId + "=" + head);
    }

    public static void main(String[] args) {
        printExample(new ListNode(2, new ListNode(4, new ListNode(6, new ListNode(8)))), "1");
        printExample(new ListNode(2, new ListNode(4, new ListNode(6, new ListNode(8, new ListNode(10))))), "2");
    }
}
