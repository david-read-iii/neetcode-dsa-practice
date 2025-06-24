package tree;

/*
 * Same Binary Tree
 *
 * Given the roots of two binary trees p and q, return true if the trees are equivalent, otherwise return false.
 *
 * Two binary trees are considered equivalent if they share the exact same structure and the nodes have the same values.
 *
 * Example 1:
 *
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 *
 * Example 2:
 *
 * Input: p = [4,7], q = [4,null,7]
 * Output: false
 *
 * Example 3:
 *
 * Input: p = [1,2,3], q = [1,3,2]
 * Output: false
 *
 * Constraints:
 *
 * 1. 0 <= The number of nodes in both trees <= 100.
 * 2. -100 <= Node.val <= 100
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(n) space, where n is the number of nodes in the tree.
 */
class SameBinaryTree {

    // Time complexity O(n)
    // Space complexity O(n)
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == null && q == null;
        }
        if (p.val == q.val) {
            boolean leftIsSameTree = isSameTree(p.left, q.left);
            boolean rightIsSameTree = isSameTree(p.right, q.right);
            return leftIsSameTree && rightIsSameTree;
        } else {
            return false;
        }
    }
}

class SameBinaryTreeTester {
    public static TreeNode getTreeNodeRoot1() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        return treeNode1;
    }

    public static TreeNode getTreeNodeRoot2() {
        TreeNode treeNode1 = new TreeNode(4);
        TreeNode treeNode2 = new TreeNode(7);
        treeNode1.left = treeNode2;
        return treeNode1;
    }

    public static TreeNode getTreeNodeRoot3() {
        TreeNode treeNode1 = new TreeNode(4);
        TreeNode treeNode2 = new TreeNode(7);
        treeNode1.right = treeNode2;
        return treeNode1;
    }

    public static TreeNode getTreeNodeRoot4() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode2;
        return treeNode1;
    }

    public static void printExample(TreeNode p, TreeNode q, String exampleId) {
        SameBinaryTree sameBinaryTree = new SameBinaryTree();
        System.out.println("isSameTree" + exampleId + "=" + sameBinaryTree.isSameTree(p, q));
    }

    public static void main(String[] args) {
        printExample(getTreeNodeRoot1(), getTreeNodeRoot1(), "1");
        printExample(getTreeNodeRoot2(), getTreeNodeRoot3(), "2");
        printExample(getTreeNodeRoot1(), getTreeNodeRoot4(), "3");
    }
}
