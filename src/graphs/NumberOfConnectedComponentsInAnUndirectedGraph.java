package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Number of Connected Components in an Undirected Graph
 *
 * There is an undirected graph with n nodes. There is also an edges array, where edges[i] = [a, b] means that there is
 * an edge between node a and node b in the graph.
 *
 * The nodes are numbered from 0 to n - 1.
 *
 * Return the total number of connected components in that graph.
 *
 * Example 1:
 *
 * Input:
 * n=3
 * edges=[[0,1], [0,2]]
 * Output:
 * 1
 *
 * Example 2:
 *
 * Input:
 * n=6
 * edges=[[0,1], [1,2], [2,3], [4,5]]
 * Output:
 * 2
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
class NumberOfConnectedComponentsInAnUndirectedGraph {

    private static final int UNVISITED = 0;
    private static final int VISITED = 1;

    // Time complexity O(V + E), V is the number of vertices and E is the number of edges
    // Space complexity O(V + E)
    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjacencyList = buildAdjacencyList(n, edges);
        int[] visitStatus = new int[n];
        int countComponents = 0;
        for (int i = 0; i < n; i++) {
            if (visitStatus[i] == UNVISITED) {
                countComponents++;
                visitNode(i, adjacencyList, visitStatus);
            }
        }
        return countComponents;
    }

    private Map<Integer, List<Integer>> buildAdjacencyList(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        return adjacencyList;
    }

    private void visitNode(int i, Map<Integer, List<Integer>> adjacencyList, int[] visitStatus) {
        if (visitStatus[i] == VISITED) {
            return;
        }
        visitStatus[i] = VISITED;
        for (int neighborI : adjacencyList.get(i)) {
            visitNode(neighborI, adjacencyList, visitStatus);
        }
    }
}

class NumberOfConnectedComponentsInAnUndirectedGraphTester {

    public static void printExample(int n, int[][] edges, String exampleId) {
        NumberOfConnectedComponentsInAnUndirectedGraph numberOfConnectedComponentsInAnUndirectedGraph = new NumberOfConnectedComponentsInAnUndirectedGraph();
        System.out.println("countComponents" + exampleId + "=" + numberOfConnectedComponentsInAnUndirectedGraph.countComponents(n, edges));
    }

    public static void main(String[] args) {
        printExample(3, new int[][]{{0, 1}, {0, 2}}, "1");
        printExample(6, new int[][]{{0, 1}, {1, 2}, {2, 3}, {4, 5}}, "2");
    }
}
