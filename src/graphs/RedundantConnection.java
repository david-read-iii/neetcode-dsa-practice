package graphs;

import java.util.Arrays;

/*
 * Redundant Connection
 *
 * You are given a connected undirected graph with n nodes labeled from 1 to n. Initially, it contained no cycles and
 * consisted of n-1 edges.
 *
 * We have now added one additional edge to the graph. The edge has two different vertices chosen from 1 to n, and was
 * not an edge that previously existed in the graph.
 *
 * The graph is represented as an array edges of length n where edges[i] = [ai, bi] represents an edge between nodes ai
 * and bi in the graph.
 *
 * Return an edge that can be removed so that the graph is still a connected non-cyclical graph. If there are multiple
 * answers, return the edge that appears last in the input edges.
 *
 * Example 1:
 *
 * Input: edges = [[1,2],[1,3],[3,4],[2,4]]
 * Output: [2,4]
 *
 * Example 2:
 *
 * Input: edges = [[1,2],[1,3],[1,4],[3,4],[4,5]]
 * Output: [3,4]
 *
 * Constraints:
 *
 * 1. n == edges.length
 * 2. 3 <= n <= 100
 * 3. 1 <= edges[i][0] < edges[i][1] <= edges.length
 *
 * There are no repeated edges and no self-loops in the input.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(V + E) time and O(V + E) space, where V is the number vertices
 * and E is the number of edges in the graph.
 */
class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {
        int[] parents = new int[edges.length + 1];
        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int[] edge : edges) {
            int rootU = findVertexRoot(edge[0], parents);
            int rootV = findVertexRoot(edge[1], parents);
            if (rootU == rootV) {
                return edge;
            } else {
                connectVertices(rootU, rootV, parents);
            }
        }

        return null;
    }

    public int findVertexRoot(int u, int[] parents) {
        if (parents[u] == u) {
            return u;
        } else {
            return findVertexRoot(parents[u], parents);
        }
    }

    public void connectVertices(int rootU, int rootV, int[] parents) {
        parents[rootV] = rootU;
    }
}

class RedundantConnectionTester {

    public static void printExample(int[][] edges, String exampleId) {
        RedundantConnection redundantConnection = new RedundantConnection();
        System.out.println("findRedundantConnection" + exampleId + "=" + Arrays.toString(redundantConnection.findRedundantConnection(edges)));
    }

    public static void main(String[] args) {
        printExample(new int[][]{{1, 2}, {1, 3}, {3, 4}, {2, 4}}, "1");
        printExample(new int[][]{{1, 2}, {1, 3}, {1, 4}, {3, 4}, {4, 5}}, "2");
    }
}
