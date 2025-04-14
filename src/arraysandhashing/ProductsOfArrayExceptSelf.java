package arraysandhashing;

import java.util.Arrays;

/*
 * Products of Array Except Self
 *
 * Given an integer array nums, return an array output where output[i] is the product of all the elements of nums except
 * nums[i].
 *
 * Each product is guaranteed to fit in a 32-bit integer.
 *
 * Follow-up: Could you solve it in O(n) time without using the division operation?
 *
 * Example 1:
 *
 * Input: nums = [1,2,4,6]
 * Output: [48,24,12,8]
 *
 * Example 2:
 *
 * Input: nums = [-1,0,1,2,3]
 * Output: [0,-6,0,0,0]
 *
 * Constraints:
 *
 * 2 <= nums.length <= 1000
 * -20 <= nums[i] <= 20
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution as good or better than O(n) time and O(n) space, where n is the size of the input
 * array.
 */
public class ProductsOfArrayExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        int[] productsLeft = getProductsLeft(nums);
        int[] productsRight = getProductsRight(nums);
        return getProductExceptSelf(productsLeft, productsRight);
    }

    /**
     * Returns int[] productsLeft where productsLeft[i] is the products of all numbers to the left of nums[i].
     */
    public static int[] getProductsLeft(int[] nums) {
        int[] productsLeft = new int[nums.length];
        int currentProductsLeft = 1;
        productsLeft[0] = currentProductsLeft; // Product to left of nums[0] is always 1.
        for (int i = 1; i < nums.length; i++) {
            currentProductsLeft = currentProductsLeft * nums[i - 1];
            productsLeft[i] = currentProductsLeft;
        }
        return productsLeft;
    }

    /**
     * Returns int[] productsRight where productsRight[i] is the products of all numbers to the right of nums[i].
     */
    public static int[] getProductsRight(int[] nums) {
        int[] productsRight = new int[nums.length];
        int currentProductsRight = 1;
        productsRight[nums.length - 1] = 1; // Product to right of nums[nums.length - 1] is always 1.
        for (int i = nums.length - 2; i >= 0; i--) {
            currentProductsRight = currentProductsRight * nums[i + 1];
            productsRight[i] = currentProductsRight;
        }
        return productsRight;
    }

    /**
     * Returns int[] productExceptSelf where productExceptSelf[i] is the products of productsLeft[i] and
     * productsRight[i]. That is, productExceptSelf[i] is the products of all numbers to the left of nums[i] and all
     * numbers to the right of nums[i];
     */
    public static int[] getProductExceptSelf(int[] productsLeft, int[] productsRight) {
        int[] productExceptSelf = new int[productsLeft.length];
        for (int i = 0; i < productsLeft.length; i++) {
            productExceptSelf[i] = productsLeft[i] * productsRight[i];
        }
        return productExceptSelf;
    }

    public static void printExample(int[] nums, String exampleId) {
        System.out.println("productExceptSelf" + exampleId + "=" + Arrays.toString(productExceptSelf(nums)));
    }

    public static void main(String[] args) {
        printExample(new int[]{1, 2, 4, 6}, "1");
        printExample(new int[]{-1, 0, 1, 2, 3}, "2");
    }
}
