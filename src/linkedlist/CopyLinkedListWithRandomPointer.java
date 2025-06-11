package linkedlist;

import java.util.HashMap;

/*
 * Copy Linked List with Random Pointer
 *
 * You are given the head of a linked list of length n. Unlike a singly linked list, each node contains an additional
 * pointer random, which may point to any node in the list, or null.
 *
 * Create a deep copy of the list.
 *
 * The deep copy should consist of exactly n new nodes, each including:
 *
 * - The original value val of the copied node
 * - A next pointer to the new node corresponding to the next pointer of the original node
 * - A random pointer to the new node corresponding to the random pointer of the original node
 *
 * Note: None of the pointers in the new list should point to nodes in the original list.
 *
 * Return the head of the copied linked list.
 *
 * In the examples, the linked list is represented as a list of n nodes. Each node is represented as a pair of
 * [val, random_index] where random_index is the index of the node (0-indexed) that the random pointer points to, or
 * null if it does not point to any node.
 *
 * Example 1:
 *
 * Input: head = [[3,null],[7,3],[4,0],[5,1]]
 * Output: [[3,null],[7,3],[4,0],[5,1]]
 *
 * Example 2:
 *
 * Input: head = [[1,null],[2,2],[3,2]]
 * Output: [[1,null],[2,2],[3,2]]
 *
 * Constraints:
 *
 * 1. 0 <= n <= 100
 * 2. -100 <= Node.val <= 100
 * 3. random is null or is pointing to some node in the linked list.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n) time and O(n) space, where n is the length of the given
 * list.
 */
class CopyLinkedListWithRandomPointer {

    // Time complexity: O(n)
    // Space complexity: O(n)
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        HashMap<Node, Node> srcNodeToDestNodeHashMap = new HashMap<>();
        Node destHeadNode = new Node(head.val);
        Node srcSelectedNode = head;
        Node destSelectedNode = destHeadNode;

        // Log newly created head node.
        srcNodeToDestNodeHashMap.put(head, destHeadNode);

        while (srcSelectedNode != null) {
            // Set random.
            if (srcSelectedNode.random != null) {
                if (srcNodeToDestNodeHashMap.containsKey(srcSelectedNode.random)) {
                    Node destRandomNode = srcNodeToDestNodeHashMap.get(srcSelectedNode.random);
                    destSelectedNode.random = destRandomNode;
                } else {
                    Node destRandomNode = new Node(srcSelectedNode.random.val);
                    destSelectedNode.random = destRandomNode;
                    srcNodeToDestNodeHashMap.put(srcSelectedNode.random, destRandomNode);
                }
            }

            // Set next.
            if (srcSelectedNode.next != null) {
                if (srcNodeToDestNodeHashMap.containsKey(srcSelectedNode.next)) {
                    Node destNextNode = srcNodeToDestNodeHashMap.get(srcSelectedNode.next);
                    destSelectedNode.next = destNextNode;
                } else {
                    Node destNextNode = new Node(srcSelectedNode.next.val);
                    destSelectedNode.next = destNextNode;
                    srcNodeToDestNodeHashMap.put(srcSelectedNode.next, destNextNode);
                }
            }

            srcSelectedNode = srcSelectedNode.next;
            destSelectedNode = destSelectedNode.next;
        }

        return destHeadNode;
    }
}

class CopyLinkedListWithRandomPointerTester {

    public static void printExample(Node head, String exampleId) {
        CopyLinkedListWithRandomPointer copyLinkedListWithRandomPointer = new CopyLinkedListWithRandomPointer();
        System.out.println("copyRandomList" + exampleId + "=" + copyLinkedListWithRandomPointer.copyRandomList(head));
    }

    public static void main(String[] args) {
        Node node1 = new Node(3);
        Node node2 = new Node(7);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node2.random = node4;
        node3.random = node1;
        node4.random = node2;
        printExample(node1, "1");

        Node node5 = new Node(1);
        Node node6 = new Node(2);
        Node node7 = new Node(3);
        node5.next = node6;
        node6.next = node7;
        node6.random = node7;
        node7.random = node7;
        printExample(node5, "2");

        printExample(null, "3");
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node node = this;
        stringBuilder.append("[");
        while (node != null) {
            stringBuilder.append(node.val);
            if (node.next != null) {
                stringBuilder.append(", ");
            }
            node = node.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
