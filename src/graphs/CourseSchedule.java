package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Course Schedule
 *
 * You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b first if
 * you want to take course a.
 *
 * The pair [0, 1], indicates that must take course 1 before taking course 0.
 *
 * There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.
 *
 * Return true if it is possible to finish all courses, otherwise return false.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[0,1]]
 * Output: true
 * Explanation: First take course 1 (no prerequisites) and then take course 0.
 *
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[0,1],[1,0]]
 * Output: false
 * Explanation: In order to take course 1 you must take course 0, and to take course 0 you must take course 1. So it is
 * impossible.
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
class CourseSchedule {

    private static final int UNVISITED = 0;
    private static final int VISITING = 1;
    private static final int VISITED = 2;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjacencyList = buildAdjacencyList(prerequisites);
        int[] visitingStatus = new int[numCourses];

        for (int course = 0; course < numCourses; course++) {
            if (visitingStatus[course] == UNVISITED) {
                boolean cycleDetected = visitNode(course, adjacencyList, visitingStatus);
                if (cycleDetected) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean visitNode(int course, Map<Integer, List<Integer>> adjacencyList, int[] visitingStatus) {
        if (visitingStatus[course] == VISITING) {
            return true;
        }
        if (visitingStatus[course] == VISITED) {
            return false; // already explored and deemed safe
        }
        visitingStatus[course] = VISITING;
        for (int dependency : adjacencyList.getOrDefault(course, new ArrayList<>())) {
            boolean cycleDetected = visitNode(dependency, adjacencyList, visitingStatus);
            if (cycleDetected) {
                return true;
            }
        }
        visitingStatus[course] = VISITED;
        return false;
    }

    private Map<Integer, List<Integer>> buildAdjacencyList(int[][] prerequisites) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int dependency = prerequisite[1];
            if (!adjacencyList.containsKey(course)) {
                adjacencyList.put(course, new ArrayList<>());
            }
            List<Integer> dependencies = adjacencyList.get(course);
            dependencies.add(dependency);
        }
        return adjacencyList;
    }
}

class CourseScheduleTester {

    public static void printExample(int numCourses, int[][] prerequisites, String exampleId) {
        CourseSchedule courseSchedule = new CourseSchedule();
        System.out.println("canFinish" + exampleId + "=" + courseSchedule.canFinish(numCourses, prerequisites));
    }

    public static void main(String[] args) {
        printExample(2, new int[][]{{0, 1}}, "1");
        printExample(2, new int[][]{{0, 1}, {1, 0}}, "2");
    }
}
