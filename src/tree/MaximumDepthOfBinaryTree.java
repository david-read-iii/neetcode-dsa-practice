package tree;

/*
 * Maximum Depth of Binary Tree
 *
 * Given the root of a binary tree, return its depth.
 *
 * The depth of a binary tree is defined as the number of nodes along the longest path from the root node down to the
 * farthest leaf node.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,null,null,4]
 * Output: 3
 *
 * Example 2:
 *
 * Input: root = []
 * Output: 0
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
class MaximumDepthOfBinaryTree {

    // Time complexity O(n)
    // Space complexity O(n) because when recursive calls stall they take space
    public int maxDepth(TreeNode root) {
        return maxDepthInternal(root, 0);
    }

    public int maxDepthInternal(TreeNode node, int currentDepth) {
        if (node == null) {
            return currentDepth;
        } else {
            return Math.max(
                    maxDepthInternal(node.left, currentDepth + 1),
                    maxDepthInternal(node.right, currentDepth + 1)
            );
        }
    }
}

class MaximumDepthOfBinaryTreeTester {
    public static TreeNode getTreeNodeRoot1() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        return treeNode1;
    }

    public static void printExample(TreeNode root, String exampleId) {
        MaximumDepthOfBinaryTree maximumDepthOfBinaryTree = new MaximumDepthOfBinaryTree();
        System.out.println("maxDepth" + exampleId + "=" + maximumDepthOfBinaryTree.maxDepth(root));
    }

    public static void main(String[] args) {
        printExample(getTreeNodeRoot1(), "1");
        printExample(null, "2");
    }
}
