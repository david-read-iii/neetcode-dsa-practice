package tries;

import java.util.HashMap;

/*
 * Implement Trie (Prefix Tree)
 *
 * A prefix tree (also known as a trie) is a tree data structure used to efficiently store and retrieve keys in a set of
 * strings. Some applications of this data structure include auto-complete and spell checker systems.
 *
 * Implement the PrefixTree class:
 *
 * PrefixTree() Initializes the prefix tree object.
 * - void insert(String word) Inserts the string word into the prefix tree.
 * - boolean search(String word) Returns true if the string word is in the prefix tree (i.e., was inserted before), and
 * false otherwise.
 * - boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix,
 * and false otherwise.
 *
 * Example 1:
 *
 * Input:
 * ["Trie", "insert", "dog", "search", "dog", "search", "do", "startsWith", "do", "insert", "do", "search", "do"]
 * Output:
 * [null, null, true, false, true, null, true]
 *
 * Explanation:
 * PrefixTree prefixTree = new PrefixTree();
 * prefixTree.insert("dog");
 * prefixTree.search("dog");    // return true
 * prefixTree.search("do");     // return false
 * prefixTree.startsWith("do"); // return true
 * prefixTree.insert("do");
 * prefixTree.search("do");     // return true
 *
 * Constraints:
 *
 * 1. 1 <= word.length, prefix.length <= 1000
 * 2. word and prefix are made up of lowercase English letters.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time for each function call and O(t) space, where n is the length of the
 * given string and t is the total number of nodes created in the Trie.
 */
class PrefixTree {

    private final PrefixTreeNode rootNode;

    public PrefixTree() {
        rootNode = new PrefixTreeNode();
    }

    public void insert(String word) {
        PrefixTreeNode parentNode = rootNode;
        for (int i = 0; i < word.length(); i++) {
            char childCharacter = word.charAt(i);
            PrefixTreeNode childNode = getChildNodeOrNull(parentNode, childCharacter);
            if (childNode == null) {
                childNode = putNewChildNode(parentNode, childCharacter);
            }
            if (i == word.length() - 1) {
                childNode.isValidEndNode = true;
            }
            parentNode = childNode;
        }
    }

    public boolean search(String word) {
        PrefixTreeNode endNode = findEndNodeOrNull(word);
        return endNode != null && endNode.isValidEndNode;
    }

    public boolean startsWith(String prefix) {
        return findEndNodeOrNull(prefix) != null;
    }

    public PrefixTreeNode getChildNodeOrNull(PrefixTreeNode parentNode, char c) {
        return parentNode.childNodes.get(c);
    }

    public PrefixTreeNode putNewChildNode(PrefixTreeNode parentNode, char c) {
        PrefixTreeNode newNode = new PrefixTreeNode();
        parentNode.childNodes.put(c, newNode);
        return newNode;
    }

    public PrefixTreeNode findEndNodeOrNull(String prefix) {
        PrefixTreeNode parentNode = rootNode;
        for (int i = 0; i < prefix.length(); i++) {
            char childCharacter = prefix.charAt(i);
            PrefixTreeNode childNode = getChildNodeOrNull(parentNode, childCharacter);
            if (childNode == null) {
                return null;
            }
            parentNode = childNode;
        }
        return parentNode;
    }

    static class PrefixTreeNode {
        HashMap<Character, PrefixTreeNode> childNodes;
        boolean isValidEndNode;

        public PrefixTreeNode() {
            this.childNodes = new HashMap<>();
            this.isValidEndNode = false;
        }
    }
}

class PrefixTrieTester {

    public static void main(String[] args) {
        PrefixTree prefixTree = new PrefixTree();
        prefixTree.insert("dog");
        System.out.println(prefixTree.search("dog"));    // return true
        System.out.println(prefixTree.search("do"));     // return false
        System.out.println(prefixTree.startsWith("do"));       // return true
        prefixTree.insert("do");
        System.out.println(prefixTree.search("do"));     // return true
    }
}
