package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
 * Rotting Fruit
 *
 * You are given a 2-D matrix grid. Each cell can have one of three possible values:
 *
 * 0 representing an empty cell
 * 1 representing a fresh fruit
 * 2 representing a rotten fruit
 *
 * Every minute, if a fresh fruit is horizontally or vertically adjacent to a rotten fruit, then the fresh fruit also
 * becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until there are zero fresh fruits remaining. If this state is
 * impossible within the grid, return -1.
 *
 * Example 1:
 *
 * Input: grid = [[1,1,0],[0,1,1],[0,1,2]]
 * Output: 4
 *
 * Example 2:
 *
 * Input: grid = [[1,0,1],[0,2,0],[1,0,1]]
 * Output: -1
 *
 * Constraints:
 *
 * 1 <= grid.length, grid[i].length <= 10
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(m * n) time and O(m * n) space, where m is the number of rows and n is the
 * number of columns in the given grid.
 */
class RottingFruit {

    private static final int FRESH_FRUIT = 1;
    private static final int ROTTEN_FRUIT = 2;

    public int orangesRotting(int[][] grid) {
        FruitRegistry fruitRegistry = getFruitRegistry(grid);

        int minutes = 0;
        while (!fruitRegistry.activeRottenFruitRegistry.isEmpty()) {
            boolean rotHappenedOnThisPass = false;
            List<Fruit> toAddToActiveRottenFruitRegistry = new ArrayList<>();
            List<Fruit> toRemoveFromActiveRottenFruitRegistry = new ArrayList<>();
            for (Fruit fruit : fruitRegistry.activeRottenFruitRegistry) {
                boolean rotHappenedOnThisPassForThisFruit = false;

                // Top
                if (fruit.i - 1 >= 0 && grid[fruit.i - 1][fruit.j] == FRESH_FRUIT) {
                    grid[fruit.i - 1][fruit.j] = ROTTEN_FRUIT;
                    Fruit newlyRottenFruit = new Fruit(fruit.i - 1, fruit.j);
                    fruitRegistry.freshFruitRegistry.remove(newlyRottenFruit);
                    toAddToActiveRottenFruitRegistry.add(newlyRottenFruit);
                    rotHappenedOnThisPassForThisFruit = true;
                    rotHappenedOnThisPass = true;
                }

                // Bottom
                if (fruit.i + 1 < grid.length && grid[fruit.i + 1][fruit.j] == FRESH_FRUIT) {
                    grid[fruit.i + 1][fruit.j] = ROTTEN_FRUIT;
                    Fruit newlyRottenFruit = new Fruit(fruit.i + 1, fruit.j);
                    fruitRegistry.freshFruitRegistry.remove(newlyRottenFruit);
                    toAddToActiveRottenFruitRegistry.add(newlyRottenFruit);
                    rotHappenedOnThisPassForThisFruit = true;
                    rotHappenedOnThisPass = true;
                }

                // Left
                if (fruit.j - 1 >= 0 && grid[fruit.i][fruit.j - 1] == FRESH_FRUIT) {
                    grid[fruit.i][fruit.j - 1] = ROTTEN_FRUIT;
                    Fruit newlyRottenFruit = new Fruit(fruit.i, fruit.j - 1);
                    fruitRegistry.freshFruitRegistry.remove(newlyRottenFruit);
                    toAddToActiveRottenFruitRegistry.add(newlyRottenFruit);
                    rotHappenedOnThisPassForThisFruit = true;
                    rotHappenedOnThisPass = true;
                }

                // Right
                if (fruit.j + 1 < grid[fruit.i].length && grid[fruit.i][fruit.j + 1] == FRESH_FRUIT) {
                    grid[fruit.i][fruit.j + 1] = ROTTEN_FRUIT;
                    Fruit newlyRottenFruit = new Fruit(fruit.i, fruit.j + 1);
                    fruitRegistry.freshFruitRegistry.remove(newlyRottenFruit);
                    toAddToActiveRottenFruitRegistry.add(newlyRottenFruit);
                    rotHappenedOnThisPassForThisFruit = true;
                    rotHappenedOnThisPass = true;
                }

                /* If no rot occurred on this pass for this fruit, it is an inactive rotten fruit. No need to track rot
                   from it anymore! */
                if (!rotHappenedOnThisPassForThisFruit) {
                    toRemoveFromActiveRottenFruitRegistry.add(fruit);
                }
            }

            // Process changes made to activeRottenFruitRegistry.
            fruitRegistry.activeRottenFruitRegistry.addAll(toAddToActiveRottenFruitRegistry);
            for (Fruit fruit : toRemoveFromActiveRottenFruitRegistry) {
                fruitRegistry.activeRottenFruitRegistry.remove(fruit);
            }

            if (rotHappenedOnThisPass) {
                minutes++;
            }
        }

        if (!fruitRegistry.freshFruitRegistry.isEmpty()) {
            // Fresh fruit still remaining, so impossible to make them rotten.
            return -1;
        } else {
            // No fresh fruit still remaining!
            return minutes;
        }
    }

    public FruitRegistry getFruitRegistry(int[][] grid) {
        FruitRegistry fruitRegistry = new FruitRegistry();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int value = grid[i][j];
                if (value == FRESH_FRUIT) {
                    fruitRegistry.freshFruitRegistry.add(new Fruit(i, j));
                } else if (value == ROTTEN_FRUIT) {
                    fruitRegistry.activeRottenFruitRegistry.add(new Fruit(i, j));
                }
            }
        }
        return fruitRegistry;
    }

    // Some definitions
    // Active rotten fruit: Fruit that is rotten and still has potential to spread its rot to adjacent cells.
    // Inactive rotten fruit: Fruit that is rotten but has already spread its rot to adjacent cells.
    public static class FruitRegistry {
        HashSet<Fruit> activeRottenFruitRegistry;
        HashSet<Fruit> freshFruitRegistry;

        public FruitRegistry() {
            activeRottenFruitRegistry = new HashSet<>();
            freshFruitRegistry = new HashSet<>();
        }
    }

    public static class Fruit {
        int i;
        int j;

        public Fruit(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Fruit)) return false;
            Fruit fruit = (Fruit) o;
            return i == fruit.i && j == fruit.j;
        }

        @Override
        public int hashCode() {
            return 31 * i + j;
        }
    }
}

class RottingFruitTester {

    public static void printExample(int[][] grid, String exampleId) {
        RottingFruit rottingFruit = new RottingFruit();
        System.out.println("orangesRotting" + exampleId + "=" + rottingFruit.orangesRotting(grid));
    }

    public static void main(String[] args) {
        printExample(new int[][]{
                        {1, 1, 0},
                        {0, 1, 1},
                        {0, 1, 2}
                },
                "1"
        );
        printExample(new int[][]{
                        {1, 0, 1},
                        {0, 2, 0},
                        {1, 0, 1}
                },
                "2"
        );
        printExample(new int[][]{{0}}, "3");
        printExample(new int[][]{{1}}, "4");
    }
}
