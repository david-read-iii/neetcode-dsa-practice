package tree;

/*
 * Balanced Binary Tree
 *
 * Given a binary tree, return true if it is height-balanced and false otherwise.
 *
 * A height-balanced binary tree is defined as a binary tree in which the left and right subtrees of every node differ
 * in height by no more than 1.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,null,null,4]
 * Output: true
 *
 * Example 2:
 *
 * Input: root = [1,2,3,null,null,4,null,5]
 * Output: false
 *
 * Example 3:
 *
 * Input: root = []
 * Output: true
 *
 * Constraints:
 *
 * 1. The number of nodes in the tree is in the range [0, 1000].
 * 2. -1000 <= Node.val <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(n) space, where n is the number of nodes in the tree.
 */
class BalancedBinaryTree {

    // Time complexity O(n)
    // Space complexity O(n)
    public boolean isBalanced(TreeNode root) {
        int heightOrUnbalanced = getHeightOrUnbalanced(root);
        return heightOrUnbalanced != -1;
    }

    /*
     * Returns the height of the binary tree. If an unbalance tree is detected at any point, -1 is returned and
     * propagated upwards.
     */
    public int getHeightOrUnbalanced(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int heightLeftSubtree = getHeightOrUnbalanced(node.left);
        if (heightLeftSubtree == -1) {
            return -1;
        }
        int heightRightSubtree = getHeightOrUnbalanced(node.right);
        if (heightRightSubtree == -1) {
            return -1;
        }
        int difference = Math.abs(heightLeftSubtree - heightRightSubtree);
        if (difference > 1) {
            return -1;
        }
        return Math.max(heightLeftSubtree, heightRightSubtree) + 1;
    }
}

class BalancedBinaryTreeTester {
    public static TreeNode getTreeNodeRoot1() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        return treeNode1;
    }

    public static TreeNode getTreeNodeRoot2() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode4.left = treeNode5;
        return treeNode1;
    }

    public static void printExample(TreeNode root, String exampleId) {
        BalancedBinaryTree balancedBinaryTree = new BalancedBinaryTree();
        System.out.println("isBalanced" + exampleId + "=" + balancedBinaryTree.isBalanced(root));
    }

    public static void main(String[] args) {
        printExample(getTreeNodeRoot1(), "1");
        printExample(getTreeNodeRoot2(), "2");
        printExample(null, "3");
    }
}
