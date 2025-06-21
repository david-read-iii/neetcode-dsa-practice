package tree;

/*
 * Diameter of Binary Tree
 *
 * The diameter of a binary tree is defined as the length of the longest path between any two nodes within the tree. The
 * path does not necessarily have to pass through the root.
 *
 * The length of a path between two nodes in a binary tree is the number of edges between the nodes.
 *
 * Given the root of a binary tree root, return the diameter of the tree.
 *
 * Example 1:
 *
 * Input: root = [1,null,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [1,2,3,5] or [5,3,2,4].
 *
 * Example 2:
 *
 * Input: root = [1,2,3]
 * Output: 2
 *
 * Constraints:
 *
 * 1. 1 <= number of nodes in the tree <= 100
 * 2. -100 <= Node.val <= 100
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(n) space, where n is the number of nodes in the tree.
 */
class DiameterOfBinaryTree {

    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        getMaxLengthThroughNode(root);
        return maxDiameter;
    }

    public int getMaxLengthThroughNode(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int maxLengthLeft = getMaxLengthThroughNode(node.left);
        int maxLengthRight = getMaxLengthThroughNode(node.right);
        maxDiameter = Math.max(maxDiameter, maxLengthLeft + maxLengthRight);
        return Math.max(maxLengthLeft, maxLengthRight) + 1;
    }
}

class DiameterOfBinaryTreeTester {
    public static TreeNode getTreeNodeRoot1() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode1.right = treeNode2;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;
        treeNode3.left = treeNode5;
        return treeNode1;
    }

    public static TreeNode getTreeNodeRoot2() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        return treeNode1;
    }

    public static void printExample(TreeNode root, String exampleId) {
        DiameterOfBinaryTree diameterOfBinaryTree = new DiameterOfBinaryTree();
        System.out.println("diameterOfBinaryTree" + exampleId + "=" + diameterOfBinaryTree.diameterOfBinaryTree(root));
    }

    public static void main(String[] args) {
        printExample(getTreeNodeRoot1(), "1");
        printExample(getTreeNodeRoot2(), "2");
    }
}
