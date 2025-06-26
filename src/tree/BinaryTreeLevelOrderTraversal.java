package tree;

import java.util.ArrayList;
import java.util.List;

/*
 * Binary Tree Level Order Traversal
 *
 * Given a binary tree root, return the level order traversal of it as a nested list, where each sublist contains the
 * values of nodes at a particular level in the tree, from left to right.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [[1],[2,3],[4,5,6,7]]
 *
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 *
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 * Constraints:
 *
 * 1. 0 <= The number of nodes in both trees <= 1000.
 * 2. -1000 <= Node.val <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(n) space, where n is the number of nodes in the given tree.
 */
class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        addNodeToList(root, list, 0);
        return list;
    }

    public void addNodeToList(TreeNode node, List<List<Integer>> list, int level) {
        if (node == null) {
            return;
        }

        if (list.size() <= level) {
            list.add(new ArrayList<>());
        }

        list.get(level).add(node.val);

        addNodeToList(node.left, list, level + 1);
        addNodeToList(node.right, list, level + 1);
    }
}

class BinaryTreeLevelOrderTraversalTester {

    public static TreeNode getRoot1() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        return treeNode1;
    }

    public static TreeNode getRoot2() {
        return new TreeNode(1);
    }

    public static void printExample(TreeNode root, String exampleId) {
        BinaryTreeLevelOrderTraversal binaryTreeLevelOrderTraversal = new BinaryTreeLevelOrderTraversal();
        System.out.println("levelOrder" + exampleId + "=" + binaryTreeLevelOrderTraversal.levelOrder(root));
    }

    public static void main(String[] args) {
        printExample(getRoot1(), "1");
        printExample(getRoot2(), "2");
        printExample(null, "3");
    }
}
