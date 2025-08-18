package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Clone Graph
 *
 * Given a node in a connected undirected graph, return a deep copy of the graph.
 *
 * Each node in the graph contains an integer value and a list of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 * The graph is shown in the test cases as an adjacency list. An adjacency list is a mapping of nodes to lists, used to
 * represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 *
 * For simplicity, nodes values are numbered from 1 to n, where n is the total number of nodes in the graph. The index
 * of each node within the adjacency list is the same as the node's value (1-indexed).
 *
 * The input node will always be the first node in the graph and have 1 as the value.
 *
 * Example 1:
 *
 * Input: adjList = [[2],[1,3],[2]]
 * Output: [[2],[1,3],[2]]
 * Explanation: There are 3 nodes in the graph.
 * Node 1: val = 1 and neighbors = [2].
 * Node 2: val = 2 and neighbors = [1, 3].
 * Node 3: val = 3 and neighbors = [2].
 *
 * Example 2:
 *
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: The graph has one node with no neighbors.
 *
 * Example 3:
 *
 * Input: adjList = []
 * Output: []
 * Explanation: The graph is empty.
 *
 * Constraints:
 *
 * 1. 0 <= The number of nodes in the graph <= 100.
 * 2. 1 <= Node.val <= 100
 * 3. There are no duplicate edges and no self-loops in the graph.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(V + E) time and O(E) space, where V is the number of vertices and E is the
 * number of edges in the given graph.
 */
class CloneGraph {

    // Time complexity: O(V + E), where V is the number of vertices and E is the number of edges in the given graph
    // Space complexity: O(E)
    public Node cloneGraph(Node node) {
        HashMap<Node, Node> sourceToCloneNodes = new HashMap<>();
        return cloneNode(node, sourceToCloneNodes);
    }

    public Node cloneNode(Node sourceNode, HashMap<Node, Node> sourceToCloneNodes) {
        if (sourceNode == null) {
            return null;
        }

        // Create new node with val.
        Node cloneNode = new Node(sourceNode.val);
        sourceToCloneNodes.put(sourceNode, cloneNode);

        // Add neighbor nodes.
        for (Node sourceNeighborNode : sourceNode.neighbors) {
            Node cloneNeighborNode = sourceToCloneNodes.get(sourceNeighborNode);
            if (cloneNeighborNode == null) {
                cloneNeighborNode = cloneNode(sourceNeighborNode, sourceToCloneNodes);
            }
            cloneNode.neighbors.add(cloneNeighborNode);
        }

        return cloneNode;
    }
}

class CloneGraphTester {

    public static void printExample(Node node, String exampleId) {
        CloneGraph cloneGraph = new CloneGraph();
        System.out.println("cloneGraph" + exampleId + "=" + cloneGraph.cloneGraph(node));
    }

    public static void runExample1() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        node1.neighbors.add(node2);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);

        printExample(node1, "1");
    }

    public static void main(String[] args) {
        runExample1();
        printExample(new Node(1), "2");
        printExample(null, "3");
    }
}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
