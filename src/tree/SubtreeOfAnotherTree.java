package tree;

/*
 * Subtree of Another Tree
 *
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same
 * structure and node values of subRoot and false otherwise.
 *
 * A subtree of a binary tree is a tree that consists of a node in tree and all of this node's descendants. The tree
 * could also be considered as a subtree of itself.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5], subRoot = [2,4,5]
 * Output: true
 *
 * Example 2:
 *
 * Input: root = [1,2,3,4,5,null,null,6], subRoot = [2,4,5]
 * Output: false
 *
 * Constraints:
 *
 * 1. 0 <= The number of nodes in both trees <= 100.
 * 2. -100 <= root.val, subRoot.val <= 100
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(m * n) time and O(m + n) space, where n and m are the number
 * of nodes in root and subRoot, respectively.
 */
class SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        if (root.val == subRoot.val
                && isSameTree(root.left, subRoot.left)
                && isSameTree(root.right, subRoot.right)) {
            return true;
        } else {
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == null && q == null;
        }
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }
}

class SubtreeOfAnotherTreeTester {
    public static TreeNode getRoot1() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        return treeNode1;
    }

    public static TreeNode getRoot2() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode4.left = treeNode6;
        return treeNode1;
    }

    public static TreeNode getRoot3() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(1);
        treeNode1.left = treeNode2;
        return treeNode1;
    }

    public static TreeNode getSubRoot1And2() {
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(5);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        return treeNode1;
    }

    public static TreeNode getSubroot3() {
        TreeNode treeNode1 = new TreeNode(1);
        return treeNode1;
    }

    public static void printExample(TreeNode root, TreeNode subRoot, String exampleId) {
        SubtreeOfAnotherTree subtreeOfAnotherTree = new SubtreeOfAnotherTree();
        System.out.println("isSubtree" + exampleId + "=" + subtreeOfAnotherTree.isSubtree(root, subRoot));
    }

    public static void main(String[] args) {
        printExample(getRoot1(), getSubRoot1And2(), "1");
        printExample(getRoot2(), getSubRoot1And2(), "2");
        printExample(getRoot3(), getSubroot3(), "3");
    }
}
