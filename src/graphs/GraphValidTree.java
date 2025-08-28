package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Graph Valid Tree
 *
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function
 * to check whether these edges make up a valid tree.
 *
 * Example 1:
 *
 * Input:
 * n = 5
 * edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
 * Output:
 * true
 *
 * Example 2:
 *
 * Input:
 * n = 5
 * edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
 * Output:
 * false
 *
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as
 * [1, 0] and thus will not appear together in edges.
 *
 * Constraints:
 *
 * 1. 1 <= n <= 100
 * 2. 0 <= edges.length <= n * (n - 1) / 2
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(V + E) time and O(V + E) space, where V is the number vertices
 * and E is the number of edges in the graph.
 */
class GraphValidTree {

    private static final int NO_PARENT = -1;

    public boolean validTree(int n, int[][] edges) {
        if (n - 1 != edges.length) {
            return false; // n - 1 = edges in a valid connected tree
        }
        HashMap<Integer, List<Integer>> adjacencyList = buildAdjacencyList(n, edges);
        boolean[] visitStatuses = new boolean[n];
        boolean isCycleDetected = visitNode(0, NO_PARENT, adjacencyList, visitStatuses);
        if (isCycleDetected) {
            return false; // Cycle detected.
        }
        for (boolean visitStatus : visitStatuses) {
            if (!visitStatus) {
                return false; // At least one node is not apart of the main graph.
            }
        }
        return true; // All nodes connected and no cycles detected.
    }

    private boolean visitNode(int node, int parentNode, HashMap<Integer, List<Integer>> adjacencyList, boolean[] visitStatuses) {
        if (visitStatuses[node]) {
            return true; // Cycle detected.
        }
        visitStatuses[node] = true;
        for (int neighborNode : adjacencyList.get(node)) {
            if (neighborNode != parentNode) {
                boolean isCycleDetected = visitNode(neighborNode, node, adjacencyList, visitStatuses);
                if (isCycleDetected) {
                    return true;
                }
            }
        }
        return false; // Node discovered with no cycles detected.
    }

    private HashMap<Integer, List<Integer>> buildAdjacencyList(int n, int[][] edges) {
        HashMap<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        return adjacencyList;
    }
}

class GraphValidTreeTester {

    public static void printExample(int n, int[][] edges, String exampleId) {
        GraphValidTree graphValidTree = new GraphValidTree();
        System.out.println("validTree" + exampleId + "=" + graphValidTree.validTree(n, edges));
    }

    public static void main(String[] args) {
        printExample(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}}, "1");
        printExample(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}, "2");
    }
}
