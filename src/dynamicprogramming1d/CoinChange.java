package dynamicprogramming1d;

import java.util.Arrays;

/*
 * Coin Change
 *
 * You are given an integer array coins representing coins of different denominations (e.g. 1 dollar, 5 dollars, etc)
 * and an integer amount representing a target amount of money.
 *
 * Return the fewest number of coins that you need to make up the exact target amount. If it is impossible to make up
 * the amount, return -1.
 *
 * You may assume that you have an unlimited number of each coin.
 *
 * Example 1:
 *
 * Input: coins = [1,5,10], amount = 12
 * Output: 3
 * Explanation: 12 = 10 + 1 + 1. Note that we do not have to use every kind coin available.
 *
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Explanation: The amount of 3 cannot be made up with coins of 2.
 *
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 * Explanation: Choosing 0 coins is a valid way to make up 0.
 *
 * Constraints:
 *
 * 1. 1 <= coins.length <= 10
 * 2. 1 <= coins[i] <= 2^31 - 1
 * 3. 0 <= amount <= 10000
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(n * t) time and O(t) space, where n is the number of coins and t is the given
 * amount.
 */
class CoinChange {

    private static final int NO_CACHE = -2;

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, NO_CACHE);
        return coinChangeInternal(dp, coins, amount);
    }

    public int coinChangeInternal(int[] dp, int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (dp[amount] != NO_CACHE) {
            return dp[amount];
        }

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int result = coinChangeInternal(dp, coins, amount - coin);
            if (result >= 0 && result < min) {
                min = 1 + result;
            }
        }

        dp[amount] = min == Integer.MAX_VALUE
                ? -1
                : min;
        return dp[amount];
    }
}

class CoinChangeTester {

    public static void printExample(int[] coins, int amount, String exampleId) {
        CoinChange coinChange = new CoinChange();
        System.out.println("coinChange" + exampleId + "=" + coinChange.coinChange(coins, amount));
    }

    public static void main(String[] args) {
        printExample(new int[]{1, 5, 10}, 12, "1");
        printExample(new int[]{2}, 3, "2");
        printExample(new int[]{1}, 0, "3");
    }
}
