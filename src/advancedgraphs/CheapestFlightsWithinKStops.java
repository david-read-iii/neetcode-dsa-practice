package advancedgraphs;

import java.util.Arrays;

/*
 * Cheapest Flights Within K Stops
 *
 * There are n airports, labeled from 0 to n - 1, which are connected by some flights. You are given an array flights
 * where flights[i] = [from_i, to_i, price_i] represents a one-way flight from airport from_i to airport to_i with cost
 * price_i. You may assume there are no duplicate flights and no flights from an airport to itself.
 *
 * You are also given three integers src, dst, and k where:
 *
 * - src is the starting airport
 * - dst is the destination airport
 * - src != dst
 * - k is the maximum number of stops you can make (not including src and dst)
 *
 * Return the cheapest price from src to dst with at most k stops, or return -1 if it is impossible.
 *
 * Example 1:
 *
 * Input: n = 4, flights = [[0,1,200],[1,2,100],[1,3,300],[2,3,100]], src = 0, dst = 3, k = 1
 * Output: 500
 * Explanation:
 * The optimal path with at most 1 stop from airport 0 to 3 is shown in red, with total cost 200 + 300 = 500.
 * Note that the path [0 -> 1 -> 2 -> 3] costs only 400, and thus is cheaper, but it requires 2 stops, which is more
 * than k.
 *
 * Example 2:
 *
 * Input: n = 3, flights = [[1,0,100],[1,2,200],[0,2,100]], src = 1, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The optimal path with at most 1 stop from airport 1 to 2 is shown in red and has cost 200.
 *
 * Constraints:
 *
 * 1. 1 <= n <= 100
 * 2. fromi != toi
 * 3. 1 <= pricei <= 1000
 * 4. 0 <= src, dst, k < n
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n + (m * k)) time and O(n) space, where n is the number of
 * cities, m is the number of flights, and k is the number of stops.
 */
class CheapestFlightsWithinKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;

        for (int i = 0; i <= k; i++) { // Repeat k + 1 times.
            int[] tempPrices = prices.clone();
            for (int[] flight : flights) {
                int fromI = flight[0];
                int toI = flight[1];
                int priceI = flight[2];

                if (prices[fromI] != Integer.MAX_VALUE) { // Adding to infinity will result in a super small number, messing up the algorithm.
                    tempPrices[toI] = Math.min(
                            tempPrices[toI],
                            prices[fromI] + priceI
                    );
                }
            }
            prices = tempPrices;
        }

        if (prices[dst] == Integer.MAX_VALUE) {
            return -1; // Impossible to reach dst with k stops.
        } else {
            return prices[dst];
        }
    }
}

class CheapestFlightsWithinKStopsTester {

    public static void printExample(int n, int[][] flights, int src, int dst, int k, String exampleId) {
        CheapestFlightsWithinKStops cheapestFlightsWithinKStops = new CheapestFlightsWithinKStops();
        System.out.println("findCheapestPrice" + exampleId + "=" + cheapestFlightsWithinKStops.findCheapestPrice(n, flights, src, dst, k));
    }

    public static void main(String[] args) {
        printExample(4, new int[][]{{0, 1, 200}, {1, 2, 100}, {1, 3, 300}, {2, 3, 100}}, 0, 3, 1, "1");
        printExample(3, new int[][]{{1, 0, 100}, {1, 2, 200}, {0, 2, 100}}, 1, 2, 1, "2");
    }
}
