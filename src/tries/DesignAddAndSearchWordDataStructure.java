package tries;

import java.util.HashMap;

/*
 * Design Add and Search Word Data Structure
 *
 * Design a data structure that supports adding new words and searching for existing words.
 *
 * Implement the WordDictionary class:
 *
 * - void addWord(word) Adds word to the data structure.
 * - bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
 * word may contain dots '.' where dots can be matched with any letter.
 *
 * Example 1:
 *
 * Input:
 * ["WordDictionary", "addWord", "day", "addWord", "bay", "addWord", "may", "search", "say", "search", "day", "search", ".ay", "search", "b.."]
 * Output:
 * [null, null, null, null, false, true, true, true]
 * Explanation:
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("day");
 * wordDictionary.addWord("bay");
 * wordDictionary.addWord("may");
 * wordDictionary.search("say"); // return false
 * wordDictionary.search("day"); // return true
 * wordDictionary.search(".ay"); // return true
 * wordDictionary.search("b.."); // return true
 *
 * Constraints:
 *
 * 1. 1 <= word.length <= 20
 * 2. word in addWord consists of lowercase English letters.
 * 3. word in search consist of '.' or lowercase English letters.
 * 4. There will be at most 2 dots in word for search queries.
 * 5. At most 10,000 calls will be made to addWord and search.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time for each function call and O(t + n) space, where n is the length of the
 * string and t is the total number of nodes created in the Trie.
 */
class WordDictionary {

    private final WordDictionaryNode rootNode;

    public WordDictionary() {
        rootNode = new WordDictionaryNode();
    }

    public void addWord(String word) {
        WordDictionaryNode currentParentNode = rootNode;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            WordDictionaryNode currentNode = currentParentNode.childNodes.get(currentChar);
            if (currentNode == null) {
                currentNode = new WordDictionaryNode();
                currentParentNode.childNodes.put(currentChar, currentNode);
            }
            if (i == word.length() - 1) {
                currentNode.isEnd = true;
            }
            currentParentNode = currentNode;
        }
    }

    public boolean search(String word) {
        return searchInternal(word, rootNode);
    }

    public boolean searchInternal(String word, WordDictionaryNode currentParentNode) {
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (currentChar == '.') {
                boolean isMatchFound = false;
                for (WordDictionaryNode node : currentParentNode.childNodes.values()) {
                    isMatchFound = searchInternal(word.substring(i + 1), node);
                    if (isMatchFound) {
                        break;
                    }
                }
                return isMatchFound;
            } else {
                WordDictionaryNode currentNode = currentParentNode.childNodes.get(currentChar);
                if (currentNode == null) {
                    return false;
                }
                currentParentNode = currentNode;
            }
        }
        return currentParentNode.isEnd;
    }

    // Primitive search
    public boolean primitiveSearch(String word) {
        WordDictionaryNode currentParentNode = rootNode;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            WordDictionaryNode currentNode = currentParentNode.childNodes.get(currentChar);
            if (currentNode == null) {
                return false;
            }
            currentParentNode = currentNode;
        }
        return currentParentNode.isEnd;
    }

    static class WordDictionaryNode {
        HashMap<Character, WordDictionaryNode> childNodes;
        boolean isEnd;

        public WordDictionaryNode() {
            childNodes = new HashMap<>();
            isEnd = false;
        }
    }
}

class WordDictionaryTester {

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("day");
        wordDictionary.addWord("bay");
        wordDictionary.addWord("may");
        System.out.println(wordDictionary.search("say")); // return false
        System.out.println(wordDictionary.search("day")); // return true
        System.out.println(wordDictionary.search(".ay")); // return true
        System.out.println(wordDictionary.search("b..")); // return true
    }
}
