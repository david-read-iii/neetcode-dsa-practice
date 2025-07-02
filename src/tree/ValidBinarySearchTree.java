package tree;

/*
 * Valid Binary Search Tree
 *
 * Given the root of a binary tree, return true if it is a valid binary search tree, otherwise return false.
 *
 * A valid binary search tree satisfies the following constraints:
 *
 * 1. The left subtree of every node contains only nodes with keys less than the node's key.
 * 2. The right subtree of every node contains only nodes with keys greater than the node's key.
 * 3. Both the left and right subtrees are also binary search trees.
 *
 * Example 1:
 *
 * Input: root = [2,1,3]
 * Output: true
 *
 * Example 2:
 *
 * Input: root = [1,2,3]
 * Output: false
 * Explanation: The root node's value is 1 but its left child's value is 2 which is greater than 1.
 *
 * Constraints:
 *
 * 1. 1 <= The number of nodes in the tree <= 1000.
 * 2. -1000 <= Node.val <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(n) space, where n is the number of nodes in the given tree.
 */
class ValidBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return isValidBSTInternal(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValidBSTInternal(TreeNode root, int minAllowed, int maxAllowed) {
        if (root == null) {
            return true;
        }

        if (root.val < minAllowed || root.val > maxAllowed) {
            return false;
        }

        return isValidBSTInternal(root.left, minAllowed, root.val - 1) && isValidBSTInternal(root.right, root.val + 1, maxAllowed);
    }
}

class ValidBinarySearchTreeTester {

    public static TreeNode getRoot1() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode2.left = treeNode1;
        treeNode2.right = treeNode3;
        return treeNode2;
    }

    public static TreeNode getRoot2() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        return treeNode1;
    }

    public static TreeNode getRoot3() {
        TreeNode treeNode2_1 = new TreeNode(2);
        TreeNode treeNode2_2 = new TreeNode(2);
        TreeNode treeNode2_3 = new TreeNode(2);
        treeNode2_1.left = treeNode2_2;
        treeNode2_1.right = treeNode2_3;
        return treeNode2_1;
    }

    public static TreeNode getRoot4() {
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        treeNode5.left = treeNode4;
        treeNode5.right = treeNode6;
        treeNode6.left = treeNode3;
        treeNode6.right = treeNode7;
        return treeNode5;
    }

    public static void printExample(TreeNode root, String exampleId) {
        ValidBinarySearchTree validBinarySearchTree = new ValidBinarySearchTree();
        System.out.println("isValidBST" + exampleId + "=" + validBinarySearchTree.isValidBST(root));
    }

    public static void main(String[] args) {
        printExample(getRoot1(), "1");
        printExample(getRoot2(), "2");
        printExample(getRoot3(), "3");
        printExample(getRoot4(), "4");
    }
}
