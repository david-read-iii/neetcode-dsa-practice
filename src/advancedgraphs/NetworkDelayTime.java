package advancedgraphs;

import java.util.*;

/*
 * Network Delay Time
 *
 * You are given a network of n directed nodes, labeled from 1 to n. You are also given times, a list of directed edges
 * where times[i] = (ui, vi, ti).
 *
 * - ui is the source node (an integer from 1 to n)
 * - vi is the target node (an integer from 1 to n)
 * - ti is the time it takes for a signal to travel from the source to the target node (an integer greater than or equal
 * to 0).
 *
 * You are also given an integer k, representing the node that we will send a signal from.
 *
 * Return the minimum time it takes for all of the n nodes to receive the signal. If it is impossible for all the nodes
 * to receive the signal, return -1 instead.
 *
 * Example 1:
 *
 * Input: times = [[1,2,1],[2,3,1],[1,4,4],[3,4,1]], n = 4, k = 1
 * Output: 3
 *
 * Example 2:
 *
 * Input: times = [[1,2,1],[2,3,1]], n = 3, k = 2
 * Output: -1
 *
 * Constraints:
 *
 * 1. 1 <= k <= n <= 100
 * 2. 1 <= times.length <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(ElogV) time and O(V + E) space, where E is the number of edges
 * and V is the number of vertices (nodes).
 */
class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<AdjacencyListPair>> adjacencyList = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
        for (int[] time : times) {
            int sourceId = time[0];
            int destinationId = time[1];
            int weight = time[2];
            adjacencyList.get(sourceId).add(new AdjacencyListPair(destinationId, weight));
        }

        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[k] = 0;

        PriorityQueue<QueuePair> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));
        queue.add(new QueuePair(0, k));

        while (!queue.isEmpty()) {
            QueuePair polledQueuePair = queue.poll();
            if (polledQueuePair.time > distances[polledQueuePair.nodeId]) {
                continue;
            }
            List<AdjacencyListPair> polledAdjacencyListPairs = adjacencyList.get(polledQueuePair.nodeId);
            for (AdjacencyListPair pair : polledAdjacencyListPairs) {
                int distanceToDestination = polledQueuePair.time + pair.weight;
                if (distanceToDestination < distances[pair.destinationId]) {
                    distances[pair.destinationId] = distanceToDestination;
                    queue.add(new QueuePair(distanceToDestination, pair.destinationId));
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < distances.length; i++) {
            max = Math.max(max, distances[i]);
        }

        if (max == Integer.MAX_VALUE) {
            return -1;
        } else {
            return max;
        }
    }

    static class QueuePair {
        int time;
        int nodeId;

        public QueuePair(int time, int nodeId) {
            this.time = time;
            this.nodeId = nodeId;
        }
    }

    static class AdjacencyListPair {
        int destinationId;
        int weight;

        public AdjacencyListPair(int destinationId, int weight) {
            this.destinationId = destinationId;
            this.weight = weight;
        }
    }
}

class NetworkDelayTimeTester {

    public static void printExample(int[][] times, int n, int k, String exampleId) {
        NetworkDelayTime networkDelayTime = new NetworkDelayTime();
        System.out.println("networkDelayTime" + exampleId + "=" + networkDelayTime.networkDelayTime(times, n, k));
    }

    public static void main(String[] args) {
        printExample(new int[][]{{1, 2, 1}, {2, 3, 1}, {1, 4, 4}, {3, 4, 1}}, 4, 1, "1");
        printExample(new int[][]{{1, 2, 1}, {2, 3, 1}}, 3, 2, "2");
        printExample(new int[][]{{1, 2, 1}, {2, 1, 3}}, 2, 2, "3");
    }
}
