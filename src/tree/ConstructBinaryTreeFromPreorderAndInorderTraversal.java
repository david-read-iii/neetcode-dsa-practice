package tree;

import java.util.HashMap;

/*
 * Construct Binary Tree from Preorder and Inorder Traversal
 *
 * You are given two integer arrays preorder and inorder.
 *
 * - preorder is the preorder traversal of a binary tree
 * - inorder is the inorder traversal of the same tree
 * - Both arrays are of the same size and consist of unique values.
 *
 * Rebuild the binary tree from the preorder and inorder traversals and return its root.
 *
 * Example 1:
 *
 * Input: preorder = [1,2,3,4], inorder = [2,1,3,4]
 * Output: [1,2,3,null,null,null,4]
 *
 * Example 2:
 *
 * Input: preorder = [1], inorder = [1]
 * Output: [1]
 *
 * Constraints:
 *
 * 1. 1 <= inorder.length <= 1000.
 * 2. inorder.length == preorder.length
 * 3. -1000 <= preorder[i], inorder[i] <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(n) space, where n is the number of nodes.
 */
class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    int preorderIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> inorderHashMap = buildInorderHashMap(inorder);
        return buildTreeInternal(preorder, inorderHashMap, 0, inorder.length - 1);
    }

    public TreeNode buildTreeInternal(int[] preorder, HashMap<Integer, Integer> inorderHashMap, int inorderStartIndex, int inorderEndIndex) {
        if (preorderIndex >= preorder.length) {
            return null;
        }
        if (inorderStartIndex > inorderEndIndex) {
            return null;
        }
        int preorderVal = preorder[preorderIndex];
        preorderIndex++;
        int inorderMidIndex = getInorderMidIndex(preorderVal, inorderHashMap);
        return new TreeNode(
                preorderVal,
                buildTreeInternal(preorder, inorderHashMap, inorderStartIndex, inorderMidIndex - 1),
                buildTreeInternal(preorder, inorderHashMap, inorderMidIndex + 1, inorderEndIndex)
        );
    }

    /**
     * Maps each array value to its array index in inorder array.
     */
    public HashMap<Integer, Integer> buildInorderHashMap(int[] inorder) {
        HashMap<Integer, Integer> inorderHashMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderHashMap.put(inorder[i], i);
        }
        return inorderHashMap;
    }

    public int getInorderMidIndex(int val, HashMap<Integer, Integer> inorderHashMap) {
        return inorderHashMap.get(val);
    }
}

class ConstructBinaryTreeFromPreorderAndInorderTraversalTester {

    public static void printExample(int[] preorder, int[] inorder, String exampleId) {
        ConstructBinaryTreeFromPreorderAndInorderTraversal constructBinaryTreeFromPreorderAndInorderTraversal = new ConstructBinaryTreeFromPreorderAndInorderTraversal();
        System.out.println("buildTree" + exampleId + "=" + constructBinaryTreeFromPreorderAndInorderTraversal.buildTree(preorder, inorder));
    }

    public static void main(String[] args) {
        printExample(new int[]{1, 2, 3, 4}, new int[]{2, 1, 3, 4}, "1");
        printExample(new int[]{1}, new int[]{1}, "2");
    }
}
