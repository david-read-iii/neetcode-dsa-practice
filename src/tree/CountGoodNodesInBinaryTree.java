package tree;

/*
 * Count Good Nodes in Binary Tree
 *
 * Within a binary tree, a node x is considered good if the path from the root of the tree to the node x contains no
 * nodes with a value greater than the value of node x
 *
 * Given the root of a binary tree root, return the number of good nodes within the tree.
 *
 * Example 1:
 *
 * Input: root = [2,1,1,3,null,1,5]
 * Output: 3
 *
 * Example 2:
 *
 * Input: root = [1,2,-1,3,4]
 * Output: 4
 *
 * Constraints:
 *
 * 1. 1 <= number of nodes in the tree <= 100
 * 2. -100 <= Node.val <= 100
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time andO(n) space, where n is the number of nodes in the given tree.
 */
class CountGoodNodesInBinaryTree {

    // Time complexity O(n)
    // Space complexity O(n)
    public int goodNodes(TreeNode root) {
        return goodNodesInternal(root, Integer.MIN_VALUE);
    }

    public int goodNodesInternal(TreeNode current, int max) {
        if (current == null) {
            return 0;
        }

        if (current.val >= max) {
            max = current.val;
            return 1 + goodNodesInternal(current.left, max) + goodNodesInternal(current.right, max);
        } else {
            return goodNodesInternal(current.left, max) + goodNodesInternal(current.right, max);
        }
    }
}

class CountGoodNodesInBinaryTreeTester {

    public static TreeNode getRoot1() {
        TreeNode treeNode1_1 = new TreeNode(1);
        TreeNode treeNode1_2 = new TreeNode(1);
        TreeNode treeNode1_3 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode2.left = treeNode1_1;
        treeNode2.right = treeNode1_2;
        treeNode1_1.left = treeNode3;
        treeNode1_2.left = treeNode1_3;
        treeNode1_2.right = treeNode5;
        return treeNode2;
    }

    public static TreeNode getRoot2() {
        TreeNode treeNodeNegative1 = new TreeNode(-1);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNodeNegative1;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;
        return treeNode1;
    }

    public static void printExample(TreeNode root, String exampleId) {
        CountGoodNodesInBinaryTree countGoodNodesInBinaryTree = new CountGoodNodesInBinaryTree();
        System.out.println("goodNodes" + exampleId + "=" + countGoodNodesInBinaryTree.goodNodes(root));
    }

    public static void main(String[] args) {
        printExample(getRoot1(), "1");
        printExample(getRoot2(), "2");
    }
}
