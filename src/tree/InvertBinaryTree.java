package tree;

/*
 * Invert Binary Tree
 *
 * You are given the root of a binary tree root. Invert the binary tree and return its root.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,3,2,7,6,5,4]
 *
 * Example 2:
 *
 * Input: root = [3,2,1]
 * Output: [3,1,2]
 *
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 * Constraints:
 *
 * 1. 0 <= The number of nodes in the tree <= 100.
 * 2. -100 <= Node.val <= 100
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(n) space, where n is the number of nodes in the tree.
 */
class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode destNode = new TreeNode(root.val);
        destNode.left = invertTree(root.right);
        destNode.right = invertTree(root.left);
        return destNode;
    }
}

class InvertBinaryTreeTester {
    public static TreeNode getTreeNodeRoot1() {
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

    public static TreeNode getTreeNodeRoot2() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode3.left = treeNode2;
        treeNode3.right = treeNode1;
        return treeNode3;
    }

    public static void printExample(TreeNode root, String exampleId) {
        InvertBinaryTree invertBinaryTree = new InvertBinaryTree();
        System.out.println("invertTree" + exampleId + "=" + invertBinaryTree.invertTree(root));
    }

    public static void main(String[] args) {
        printExample(getTreeNodeRoot1(), "1");
        printExample(getTreeNodeRoot2(), "2");
        printExample(null, "3");
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
