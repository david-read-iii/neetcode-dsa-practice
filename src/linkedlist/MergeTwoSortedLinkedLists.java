package linkedlist;

/*
 * Merge Two Sorted Linked Lists
 *
 * You are given the heads of two sorted linked lists list1 and list2.
 *
 * Merge the two lists into one sorted linked list and return the head of the new sorted linked list.
 *
 * The new list should be made up of nodes from list1 and list2.
 *
 * Example 1:
 *
 * Input: list1 = [1,2,4], list2 = [1,3,5]
 * Output: [1,1,2,3,4,5]
 *
 * Example 2:
 *
 * Input: list1 = [], list2 = [1,2]
 * Output: [1,2]
 *
 * Example 3:
 *
 * Input: list1 = [], list2 = []
 * Output: []
 *
 * Constraints:
 *
 * 1. 0 <= The length of each list <= 100.
 * 2. -100 <= Node.val <= 100
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n + m) time and O(1) space, where n is the length of list1 and m is the length
 * of list2.
 */
class MergeTwoSortedLinkedLists {

    // Time Complexity: O(n + m) where n is the length of list1 and m is the length of list2
    // Space Complexity: O(1)
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode();
        ListNode currentNode = dummyNode;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                currentNode.next = list1;
                currentNode = currentNode.next;
                list1 = list1.next;
            } else { // currentList1Node.val >= currentList2Node.val
                currentNode.next = list2;
                currentNode = currentNode.next;
                list2 = list2.next;
            }
        }

        // At this point, either list1 or list2 holds the rest of the ListNodes to add.
        if (list1 != null) {
            currentNode.next = list1;
        } else { // list2 != null
            currentNode.next = list2;
        }

        return dummyNode.next; // actual head is at next
    }
}

class MergeTwoSortedLinkedListsTester {

    public static void printExample(ListNode list1, ListNode list2, String exampleId) {
        MergeTwoSortedLinkedLists mergeTwoSortedLinkedLists = new MergeTwoSortedLinkedLists();
        System.out.println("mergeTwoLists" + exampleId + "=" + mergeTwoSortedLinkedLists.mergeTwoLists(list1, list2));
    }

    public static void main(String[] args) {
        printExample(
                new ListNode(1, new ListNode(2, new ListNode(4))),
                new ListNode(1, new ListNode(3, new ListNode(5))),
                "1"
        );
        printExample(
                null,
                new ListNode(1, new ListNode(2)),
                "2"
        );
        printExample(
                null,
                null,
                "3"
        );
    }
}
