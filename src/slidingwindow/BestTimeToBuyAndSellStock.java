package slidingwindow;

/*
 * Best Time to Buy and Sell Stock
 *
 * You are given an integer array prices where prices[i] is the price of NeetCoin on the ith day.
 *
 * You may choose a single day to buy one NeetCoin and choose a different day in the future to sell it.
 *
 * Return the maximum profit you can achieve. You may choose to not make any transactions, in which case the profit
 * would be 0.
 *
 * Example 1:
 *
 * Input: prices = [10,1,5,6,7,1]
 * Output: 6
 * Explanation: Buy prices[1] and sell prices[4], profit = 7 - 1 = 6.
 *
 * Example 2:
 *
 * Input: prices = [10,8,7,5,2]
 * Output: 0
 * Explanation: No profitable transactions can be made, thus the max profit is 0.
 *
 * Constraints:
 *
 * 1 <= prices.length <= 100
 * 0 <= prices[i] <= 100
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n) time and O(1) space, where n is the size of the input array.
 */
class BestTimeToBuyAndSellStock {

    public static int maxProfit(int[] prices) {
        int minIndex = 0;
        int sellIndex = 0;
        int maxProfit = 0;

        // Iterate through prices array with sellIndex.
        while (sellIndex < prices.length) {
            minIndex = getMinIndex(prices, minIndex, sellIndex);                        // minIndex will always point to the min value that sellIndex has encountered so far
            int maxProfitForCurrentSellIndex = getProfit(prices, minIndex, sellIndex);  // Calculate the maxProfit we can get for this value of sellIndex
            maxProfit = Math.max(maxProfit, maxProfitForCurrentSellIndex);              // maxProfit will always contain the max value encountered so far
            sellIndex++;
        }

        // By the time we get here, we have encountered each possible max profit for each sellIndex.
        return maxProfit;
    }

    private static int getMinIndex(int[] prices, int leftIndex, int rightIndex) {
        if (prices[leftIndex] <= prices[rightIndex]) {
            return leftIndex;
        } else {
            return rightIndex;
        }
    }

    private static int getProfit(int[] prices, int leftIndex, int rightIndex) {
        return prices[rightIndex] - prices[leftIndex];
    }

    public static void main(String[] args) {
        // Example 1
        int[] prices1 = new int[]{10, 1, 5, 6, 7, 1};
        int maxProfit1 = maxProfit(prices1);
        System.out.println("maxProfit1=" + maxProfit1);

        // Example 2
        int[] prices2 = new int[]{10, 8, 7, 5, 2};
        int maxProfit2 = maxProfit(prices2);
        System.out.println("maxProfit2=" + maxProfit2);

        // Example 3
        int[] prices3 = new int[]{10, 1, 5, 0, 6, 7, 1};
        int maxProfit3 = maxProfit(prices3);
        System.out.println("maxProfit3=" + maxProfit3);
    }
}
