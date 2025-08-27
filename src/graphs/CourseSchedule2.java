package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * Course Schedule II
 *
 * You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first if
 * you want to take course a.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.
 *
 * Return a valid ordering of courses you can take to finish all courses. If there are many valid answers, return any of
 * them. If it's not possible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: numCourses = 3, prerequisites = [[1,0]]
 * Output: [0,1,2]
 * Explanation: We must ensure that course 0 is taken before course 1.
 *
 * Example 2:
 *
 * Input: numCourses = 3, prerequisites = [[0,1],[1,2],[2,0]]
 * Output: []
 * Explanation: It's impossible to finish all courses.
 *
 * Constraints:
 *
 * 1. 1 <= numCourses <= 1000
 * 2. 0 <= prerequisites.length <= 1000
 * 3. All prerequisite pairs are unique.
 *
 * Recommended Time & Space Complexity
 *
 * You should aim for a solution with O(V + E) time and O(V + E) space, where V is the number of courses (nodes) and E
 * is the number of prerequisites (edges).
 */
class CourseSchedule2 {

    private static final int UNVISITED = 0;
    private static final int VISITING = 1;
    private static final int VISITED = 2;

    // Time complexity: O(V + E), where V is number of nodes and E is number of edges
    // Space complexity: O(V + E)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> adjacencyList = getAdjacencyList(numCourses, prerequisites);
        int[] visitStatus = new int[numCourses];
        List<Integer> orderVisited = new ArrayList<>();
        for (int course : adjacencyList.keySet()) {
            if (visitStatus[course] == UNVISITED) {
                boolean isCycleDetected = visitNode(course, adjacencyList, visitStatus, orderVisited);
                if (isCycleDetected) {
                    return new int[]{};
                }
            }
        }
        return toIntArray(orderVisited);
    }

    /**
     * Maps a course to its course dependencies
     */
    private HashMap<Integer, List<Integer>> getAdjacencyList(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> adjacencyList = new HashMap<>();
        // Add key for each course.
        for (int course = 0; course < numCourses; course++) {
            adjacencyList.put(course, new ArrayList<>());
        }
        // Add dependencies for each course.
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int dependency = prerequisite[1];
            List<Integer> existingDependencyList = adjacencyList.get(course);
            existingDependencyList.add(dependency);
        }
        return adjacencyList;
    }

    private boolean visitNode(int course, HashMap<Integer, List<Integer>> adjacencyList, int[] visitStatus, List<Integer> orderVisited) {
        if (visitStatus[course] == VISITING) {
            return true; // cycle detected
        }
        if (visitStatus[course] == VISITED) {
            return false; // this and all further nodes from this point already visited
        }

        visitStatus[course] = VISITING;
        for (int dependency : adjacencyList.get(course)) {
            boolean isCycleDetected = visitNode(dependency, adjacencyList, visitStatus, orderVisited);
            if (isCycleDetected) {
                return true; // cycle detected
            }
        }
        visitStatus[course] = VISITED;
        orderVisited.add(course);
        return false; // this node done processing
    }

    private int[] toIntArray(List<Integer> list) {
        int[] intArray = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            intArray[i] = list.get(i);
        }
        return intArray;
    }
}

class CourseSchedule2Tester {

    public static void printExample(int numCourses, int[][] prerequisites, String exampleId) {
        CourseSchedule2 courseSchedule2 = new CourseSchedule2();
        System.out.println("findOrder" + exampleId + "=" + Arrays.toString(courseSchedule2.findOrder(numCourses, prerequisites)));
    }

    public static void main(String[] args) {
        printExample(3, new int[][]{{1, 0}}, "1");
        printExample(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}, "2");
    }
}
