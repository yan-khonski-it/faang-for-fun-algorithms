package com.yk.faang.algorithms.count_squares;

import static com.yk.faang.utils.TimerUtils.runTestCaseWithTimerNs;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/valid-square/ Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a square.
 */
public class Solution1 {

  public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
    // validate input

    // fix the first point and calculate distances to other points from the first point.
    // sort these points by the distance: we will get: A, B, D, C and distances: AB, AD, AC.
    // then we will calculate the remaining distances: BD, BC.
    // AB == AD == BC == CD && AC == BD - this is squre.
    // We will use the square of each side to avoid using sqrt.
    // Time complexity: constant. We will calculate 6 distances and compare them.
    // Space complexity: constant. We will use 6 variables to store distances.

    if (!validatePoints(p1, p2, p3, p4)) {
      return false;
    }

    PointWithDistance[] points = new PointWithDistance[4];
    points[0] = new PointWithDistance(p1, 0);
    points[1] = new PointWithDistance(p2, distance2(p1, p2));
    points[2] = new PointWithDistance(p3, distance2(p1, p3));
    points[3] = new PointWithDistance(p4, distance2(p1, p4));

    Arrays.sort(points, Comparator.comparingInt(pointWithDistance -> pointWithDistance.distance));

    int AB2 = points[1].distance; // AB squared
    int AD2 = points[2].distance;
    int AC2 = points[3].distance;
    if (AB2 == 0) {
      return false;
    }

    if (AB2 != AD2) {
      return false;
    }

    int BC2 = distance2(points[1].point, points[3].point);
    int BD2 = distance2(points[1].point, points[2].point);
    int CD2 = distance2(points[3].point, points[2].point);

    if (AB2 != BC2 || AB2 != CD2) {
      return false;
    }

    return AC2 == BD2;
  }

  private boolean validatePoints(int[]... points) {
    for (int[] point : points) {
      if (point == null || point.length != 2) {
        return false;
      }
    }

    return true;
  }

  // Squared distance between two points.
  private int distance2(int[] point1, int[] point2) {
    int x2 = point1[0] - point2[0];
    x2 = x2 * x2;

    int y2 = point1[1] - point2[1];
    y2 = y2 * y2;

    return x2 + y2;
  }

  private static class PointWithDistance {

    int[] point;
    int distance;

    public PointWithDistance(int[] point, int distance) {
      this.point = point;
      this.distance = distance;
    }
  }
}


class Main1 {

  public static void main(String[] args) {
    Solution1 solution = new Solution1();

    boolean res1 = runTestCaseWithTimerNs(() -> solution.validSquare(new int[]{0, 0}, new int[]{1, 1}, new int[]{0, 0}, new int[]{1, 1}));
    assertThat(res1).isFalse();

    boolean res2 = runTestCaseWithTimerNs(() -> solution.validSquare(new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}));
    assertThat(res2).isFalse();

    boolean res3 = runTestCaseWithTimerNs(() -> solution.validSquare(new int[]{0, 1}, new int[]{1, 2}, new int[]{0, 2}, new int[]{0, 0}));
    assertThat(res3).isFalse();

    boolean res4 = runTestCaseWithTimerNs(() -> solution.validSquare(new int[]{1, 1}, new int[]{0, 1}, new int[]{1, 2}, new int[]{0, 0}));
    assertThat(res4).isFalse();

    boolean res5 = runTestCaseWithTimerNs(() -> solution.validSquare(new int[]{1, 1}, new int[]{5, 3}, new int[]{3, 5}, new int[]{7, 7}));
    assertThat(res5).isFalse();

    boolean res6 = runTestCaseWithTimerNs(() -> solution.validSquare(new int[]{0, 0}, new int[]{1, 1}, new int[]{1, 0}, new int[]{0, 1}));
    assertThat(res6).isTrue();

    boolean res7 = runTestCaseWithTimerNs(() -> solution.validSquare(new int[]{1, 0}, new int[]{-1, 0}, new int[]{0, 1}, new int[]{0, -1}));
    assertThat(res7).isTrue();

    boolean res8 = runTestCaseWithTimerNs(() -> solution.validSquare(new int[]{2, 0}, new int[]{1, 2}, new int[]{3, 3}, new int[]{4, 1}));
    assertThat(res8).isTrue();
  }
}
