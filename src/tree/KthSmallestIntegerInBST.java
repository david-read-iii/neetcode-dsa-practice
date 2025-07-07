package tree;

import java.util.HashMap;

/*
 * Kth Smallest Integer in BST
 *
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) in the tree.
 *
 * A binary search tree satisfies the following constraints:
 *
 * 1. The left subtree of every node contains only nodes with keys less than the node's key.
 * 2. The right subtree of every node contains only nodes with keys greater than the node's key.
 * 3. Both the left and right subtrees are also binary search trees.
 *
 * Example 1:
 *
 * Input: root = [2,1,3], k = 1
 * Output: 1
 *
 * Example 2:
 *
 * Input: root = [4,3,5,2,null], k = 4
 * Output: 5
 *
 * Constraints:
 *
 * 1. 1 <= k <= The number of nodes in the tree <= 1000.
 * 2. 0 <= Node.val <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n) time and O(n) space, where n is the number of nodes in the
 * given tree.
 */
class KthSmallestIntegerInBST {
    
    int currentK = 1;

    public int kthSmallest(TreeNode root, int k) {
        HashMap<Integer, Integer> kSmallestToTreeNodeValMap = new HashMap<>();
        buildHashMap(root, kSmallestToTreeNodeValMap);
        System.out.println("hashMap=" + kSmallestToTreeNodeValMap);
        return kSmallestToTreeNodeValMap.get(k);
    }

    public void buildHashMap(TreeNode node, HashMap<Integer, Integer> kSmallestToTreeNodeValMap) {
        if (node == null) {
            return;
        }
        buildHashMap(node.left, kSmallestToTreeNodeValMap);

        System.out.println("node.val=" + node.val + " | currentK=" + currentK);
        kSmallestToTreeNodeValMap.put(currentK, node.val);
        currentK++;

        buildHashMap(node.right, kSmallestToTreeNodeValMap);
    }
}

class KthSmallestIntegerInBSTTester {

    public static TreeNode getRoot1() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode2.left = treeNode1;
        treeNode2.right = treeNode3;
        return treeNode2;
    }

    public static TreeNode getRoot2() {
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode4.left = treeNode3;
        treeNode4.right = treeNode5;
        treeNode3.left = treeNode2;
        return treeNode4;
    }

    public static void printExample(TreeNode root, int k, String exampleId) {
        KthSmallestIntegerInBST kthSmallestIntegerInBST = new KthSmallestIntegerInBST();
        System.out.println("kthSmallest" + exampleId + "=" + kthSmallestIntegerInBST.kthSmallest(root, k));
    }

    public static void main(String[] args) {
        printExample(getRoot1(), 1, "1");
        printExample(getRoot2(), 4, "2");
    }
}
