package com.yk.faang.fun.algorithms.count_squares;

import static com.yk.faang.fun.utils.TimerUtils.runTestCaseWithTimerNs;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

/**
 * https://leetcode.com/discuss/interview-question/1030039/pure-storage-technical-round-member-of-technical-staff
 * <p>
 * Design a function that takes in >= 4 points and returns the number of squares that can be formed
 * (i.e. how many groups of 4 points(x,y coordinates) within the input points form a square)
 * <p>
 * Expectation: The complexity of the extended question should be O(N^3) where N is the number of points.
 */
public class CountSquaresSolution2 {

  private Solution2 validSquareSolution = new Solution2();

  /**
   * Time: O(n^4) <br>
   * Space: O(1)
   * We will use brute force here. For each 4 points, we will check if it is a square.
   */
  public int countSquares(int[][] points) {
    if (points.length < 4 || !validatePoints(points)) {
      return 0;
    }

    Arrays.sort(points, this::comparePoints);

    int count = 0;
    for (int i = 0; i < points.length; i++) {
      breakTwoPointsLabel:
      for (int j = i + 1; j < points.length; j++) {


        for (int k = j + 1; k < points.length; k++) {
          for (int l = k + 1; l < points.length; l++) {
            if (validSquareSolution.validSquare(points[i], points[j], points[k], points[l])) {
              count++;
              break breakTwoPointsLabel;
            }
          }
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

  private int comparePoints(int[] point1, int[] point2) {
    int deltaX = point1[0] - point2[0];
    if (deltaX == 0) {
      return point1[1] - point2[1];
    } else {
      return deltaX;
    }
  }
}

class Main4 {

  public static void main(String[] args) {
    CountSquaresSolution2 solution2 = new CountSquaresSolution2();
    int res = runTestCaseWithTimerNs(() -> solution2.countSquares(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 1}, {2, 0}, {2, 1}, {3, 0}, {3, 1}, {1, 2}, {2, 2}, {3, 2}}));
    assertThat(res).isEqualTo(6);
  }
}
