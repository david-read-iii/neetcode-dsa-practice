package dynamicprogramming2d;

/*
 * Best Time to Buy and Sell Stock with Cooldown
 *
 * You are given an integer array prices where prices[i] is the price of NeetCoin on the ith day.
 *
 * You may buy and sell one NeetCoin multiple times with the following restrictions:
 *
 * - After you sell your NeetCoin, you cannot buy another one on the next day (i.e., there is a cooldown period of one
 * day).
 * - You may only own at most one NeetCoin at a time.
 * - You may complete as many transactions as you like.
 *
 * Return the maximum profit you can achieve.
 *
 * Example 1:
 *
 * Input: prices = [1,3,4,0,4]
 * Output: 6
 * Explanation: Buy on day 0 (price = 1) and sell on day 1 (price = 3), profit = 3-1 = 2. Then buy on day 3 (price = 0)
 * and sell on day 4 (price = 4), profit = 4-0 = 4. Total profit is 2 + 4 = 6.
 *
 * Example 2:
 *
 * Input: prices = [1]
 * Output: 0
 *
 * Constraints:
 *
 * 1. 1 <= prices.length <= 5000
 * 2. 0 <= prices[i] <= 1000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n) time and O(n) space, where n is the size of the input
 * array.
 */
class BestTimeToBuyAndSellStockWithCooldown {

    // Time complexity O(n)
    // Space complexity O(n)
    public int maxProfit(int[] prices) {
        int[] hold = new int[prices.length];
        int[] notHold = new int[prices.length];
        int[] cooldown = new int[prices.length];

        hold[0] = -prices[0];
        notHold[0] = 0;
        cooldown[0] = Integer.MIN_VALUE;

        for (int i = 1; i < prices.length; i++) {
            hold[i] = Math.max(
                    hold[i - 1],                    // continue to hold (did nothing today)
                    notHold[i - 1] - prices[i]      // purchased a stock today
            );
            notHold[i] = Math.max(
                    notHold[i - 1],                 // continue to not hold (did nothing today)
                    cooldown[i - 1]                 // sold yesterday, cooldown expires today
            );
            cooldown[i] = hold[i - 1] + prices[i];  // sold today
        }

        return Math.max(notHold[prices.length - 1], cooldown[prices.length - 1]);
    }
}

class BestTimeToBuyAndSellStockWithCooldownTester {

    public static void printExample(int[] prices, String exampleId) {
        BestTimeToBuyAndSellStockWithCooldown bestTimeToBuyAndSellStockWithCooldown = new BestTimeToBuyAndSellStockWithCooldown();
        System.out.println("maxProfit" + exampleId + "=" + bestTimeToBuyAndSellStockWithCooldown.maxProfit(prices));
    }

    public static void main(String[] args) {
        printExample(new int[]{1, 3, 4, 0, 4}, "1");
        printExample(new int[]{1}, "2");
    }
}
