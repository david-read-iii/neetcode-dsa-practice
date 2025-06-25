package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Lowest Common Ancestor in Binary Search Tree
 *
 * Given a binary search tree (BST) where all node values are unique, and two nodes from the tree p and q, return the
 * lowest common ancestor (LCA) of the two nodes.
 *
 * The lowest common ancestor between two nodes p and q is the lowest node in a tree T such that both p and q as
 * descendants. The ancestor is allowed to be a descendant of itself.
 *
 * Example 1:
 *
 * Input: root = [5,3,8,1,4,7,9,null,2], p = 3, q = 8
 * Output: 5
 *
 * Example 2:
 *
 * Input: root = [5,3,8,1,4,7,9,null,2], p = 3, q = 4
 * Output: 3
 * Explanation: The LCA of nodes 3 and 4 is 3, since a node can be a descendant of itself.
 *
 * Constraints:
 *
 * 1. 2 <= The number of nodes in the tree <= 100.
 * 2. -100 <= Node.val <= 100
 * 3. p != q
 * 4. p and q will both exist in the BST.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(h) time and O(h) space, where h is the height of the given
 * tree.
 */
class LowestCommonAncestorInBinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathToP = getListPathFromTreeNodeToRoot(root, p);
        Set<Integer> pathToQ = getPathValuesSetFromTreeNodeToRoot(root, q);

        for (int i = pathToP.size() - 1; i >= 0; i--) {
            TreeNode currentTreeNode = pathToP.get(i);
            if (pathToQ.contains(currentTreeNode.val)) {
                return currentTreeNode;
            }
        }

        // No LCA detected.
        return null;
    }

    public List<TreeNode> getListPathFromTreeNodeToRoot(TreeNode root, TreeNode searchNode) {
        List<TreeNode> path = new ArrayList<>();
        getListPathFromTreeNodeToRootInternal(root, searchNode, path);
        return path;
    }

    public void getListPathFromTreeNodeToRootInternal(TreeNode currentNode, TreeNode searchNode, List<TreeNode> path) {
        path.add(currentNode);
        if (currentNode.val > searchNode.val) {
            // searchNode in left subtree
            getListPathFromTreeNodeToRootInternal(currentNode.left, searchNode, path);
        } else if (currentNode.val < searchNode.val) {
            // searchNode in right subtree
            getListPathFromTreeNodeToRootInternal(currentNode.right, searchNode, path);
        }

        // currentNode.val == searchNode.val, because we assume searchNode is always in tree somewhere
    }

    public Set<Integer> getPathValuesSetFromTreeNodeToRoot(TreeNode root, TreeNode searchNode) {
        Set<Integer> path = new HashSet<>();
        getPathValuesSetFromTreeNodeToRootInternal(root, searchNode, path);
        return path;
    }

    public void getPathValuesSetFromTreeNodeToRootInternal(TreeNode currentNode, TreeNode searchNode, Set<Integer> path) {
        path.add(currentNode.val);
        if (currentNode.val > searchNode.val) {
            // searchNode in left subtree
            getPathValuesSetFromTreeNodeToRootInternal(currentNode.left, searchNode, path);
        } else if (currentNode.val < searchNode.val) {
            // searchNode in right subtree
            getPathValuesSetFromTreeNodeToRootInternal(currentNode.right, searchNode, path);
        }

        // currentNode.val == searchNode.val, because we assume searchNode is always in tree somewhere
    }
}

class LowestCommonAncestorInBinarySearchTreeTester {

    public static void printExample(TreeNode root, TreeNode p, TreeNode q, String exampleId) {
        LowestCommonAncestorInBinarySearchTree lowestCommonAncestorInBinarySearchTree = new LowestCommonAncestorInBinarySearchTree();
        System.out.println("lowestCommonAncestor" + exampleId + "=" + lowestCommonAncestorInBinarySearchTree.lowestCommonAncestor(root, p, q));
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        TreeNode treeNode9 = new TreeNode(9);
        treeNode5.left = treeNode3;
        treeNode5.right = treeNode8;
        treeNode3.left = treeNode1;
        treeNode3.right = treeNode4;
        treeNode1.right = treeNode2;
        treeNode8.left = treeNode7;
        treeNode8.right = treeNode9;

        printExample(treeNode5, treeNode3, treeNode8, "1");
        printExample(treeNode5, treeNode3, treeNode4, "2");
    }
}
