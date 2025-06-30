package tree;

import java.util.ArrayList;
import java.util.List;

/*
 * Binary Tree Right Side View
 *
 * You are given the root of a binary tree. Return only the values of the nodes that are visible from the right side of
 * the tree, ordered from top to bottom.
 *
 * Example 1:
 *
 * Input: root = [1,2,3]
 * Output: [1,3]
 *
 * Example 2:
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,3,7]
 *
 * Constraints:
 *
 * 1. 0 <= number of nodes in the tree <= 100
 * 2. -100 <= Node.val <= 100
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(n) space, where n is the number of nodes in the given tree.
 */
class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<List<TreeNode>> treeNodesByLevel = buildTreeNodeByLevel(root);
        return getRightSideViewList(treeNodesByLevel);
    }

    public List<List<TreeNode>> buildTreeNodeByLevel(TreeNode root) {
        List<List<TreeNode>> result = new ArrayList<>();
        buildTreeNodesByLevelInternal(root, 0, result);
        return result;
    }


    public void buildTreeNodesByLevelInternal(TreeNode root, int level, List<List<TreeNode>> result) {
        if (root == null) {
            return;
        }

        List<TreeNode> list;
        if (level >= result.size()) {
            list = new ArrayList<>();
            result.add(list);
        } else {
            list = result.get(level);
        }

        list.add(root);

        if (root.left != null) {
            buildTreeNodesByLevelInternal(root.left, level + 1, result);
        }

        if (root.right != null) {
            buildTreeNodesByLevelInternal(root.right, level + 1, result);
        }
    }

    public List<Integer> getRightSideViewList(List<List<TreeNode>> treeNodesByLevel) {
        List<Integer> result = new ArrayList<>();
        for (List<TreeNode> treeNodes : treeNodesByLevel) {
            result.add(treeNodes.getLast().val);
        }
        return result;
    }
}

class BinaryTreeRightSideViewTester {

    public static TreeNode getRoot1() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        return treeNode1;
    }

    public static TreeNode getRoot2() {
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

    public static TreeNode getRoot3() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        treeNode1.left = treeNode2;
        return treeNode1;
    }

    public static void printExample(TreeNode root, String exampleId) {
        BinaryTreeRightSideView binaryTreeRightSideView = new BinaryTreeRightSideView();
        System.out.println("rightSideView" + exampleId + "=" + binaryTreeRightSideView.rightSideView(root));
    }

    public static void main(String[] args) {
        printExample(getRoot1(), "1");
        printExample(getRoot2(), "2");
        printExample(getRoot3(), "3");
    }
}
