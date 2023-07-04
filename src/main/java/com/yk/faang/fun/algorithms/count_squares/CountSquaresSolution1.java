package com.yk.faang.fun.algorithms.count_squares;

import static com.yk.faang.fun.utils.TimerUtils.runTestCaseWithTimerNs;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/discuss/interview-question/1030039/pure-storage-technical-round-member-of-technical-staff
 * <p>
 * Design a function that takes in >= 4 points and returns the number of squares that can be formed
 * (i.e. how many groups of 4 points(x,y coordinates) within the input points form a square)
 * <p>
 * Expectation: The complexity of the extended question should be O(N^3) where N is the number of points.
 */
public class CountSquaresSolution1 {

  /**
   * Time: O(n^2) <br>
   * Space: O(n)
   *
   * We iterate though all couple of points (it is potentially a diagonal of a square). We will find orthogonal diagonal and check if its points are present in the input.
   * We double the coordinates of the input, to divide them by 2 as integers.
   *
   * Let us have a pair of points A and C. AC is a diagonal of a square.
   * So we need to find points B and D, such as AC is orthogonal to BD and AC == BD.
   *
   * Let v1 and v2 be vectors. If v1 * v2 = 0, v1 is orthogonal to v2. Let v1 have coordindates (x, y), then v2 has coordinates (-y, x).
   * Let O be the center of the square. Then O has coordinates ((Ax + Cx) / 2, (Ay + Cy) / 2). [This is why we double the coordinates of the input].
   * Let AO be the vector that represents half of the diagonal AC. Then AO has coordinates ((Cx - Ax) / 2, (Cy - Ay) / 2).
   *
   * Now, let's find the vector OB, that is orthogonal to AO. And vector BO that has the opposite direction to OB.
   * OB has coordinates ((Ay - Cy) / 2, (-Ax + Cx) / 2).
   *
   * Now, to get coordinates of B, we add coordinates of O to the vector OB.
   * Therefore, B has coordinates ((Ax + Cx + Ay - Cy) / 2; (Ay + Cy - Ax + Cx) / 2).
   * Similarly, D has coordinates ((Ax + Cx - Ay + Cy) / 2; (Ay + Cy + Ax - Cx) / 2).
   *
   * https://math.stackexchange.com/questions/506785/given-two-diagonally-opposite-points-on-a-square-how-to-calculate-the-other-two?rq=1
   *
   */
  public int countSquares(int[][] points) {
    if (points.length < 4 || !validatePoints(points)) {
      return 0;
    }

    Set<Point> pointsSet = new HashSet<>();
    for (int[] point : points) {
      // multiply by 2 to avoid using double when dividing by 2.
      pointsSet.add(new Point(2 * point[0], 2 * point[1]));
    }

    int count = 0;

    for (int i = 0; i < points.length; i++) {
      for (int j = i + 1; j < points.length; j++) {
        // A is point i. C is point j. We want to find B and D coordinates. Then we want to check if these points are present in the input.
        int ax = points[i][0];
        int ay = points[i][1];

        int cx = points[j][0];
        int cy = points[j][1];

        // Coordinates of the diagonally opposite points in a square.
        int bx = (ax + cx + ay - cy) / 2;
        int by = (-ax + cx + ay + cy) / 2;

        int dx = (ax + cx - ay + cy) / 2;
        int dy = (ax - cx + ay + cy) / 2;

        if (pointsSet.contains(new Point(bx, by)) && pointsSet.contains(new Point(dx, dy))) {
          count++;
        }
      }
    }

    return count;
  }

  private boolean validatePoints(int[]... points) {
    for (int[] point : points) {
      if (point == null || point.length != 2) {
        return false;
      }
    }

    return true;
  }

  private static class Point {
    int x;
    int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      Point point = (Point) o;

      if (x != point.x) {
        return false;
      }
      return y == point.y;
    }

    @Override
    public int hashCode() {
      int result = x;
      result = 31 * result + y;
      return result;
    }
  }
}

class Main3 {

  public static void main(String[] args) {
    CountSquaresSolution1 solution = new CountSquaresSolution1();
    int res = runTestCaseWithTimerNs(() -> solution.countSquares(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}, {2, 0}, {2, 1}, {3, 0}, {3, 1}, {1, 2}, {2, 2}, {3, 2}}));
    assertThat(res).isEqualTo(6);
  }
}
