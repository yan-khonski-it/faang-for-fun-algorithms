package com.yk.faang.leetcode.l0048_rotate_image;

import static com.yk.faang.utils.TimeUtils.withTimerNs;
import static org.assertj.core.api.Assertions.assertThat;

public class Main {

  static void main() {
    ISolution solution = new Solution();
    testSolution(solution);

    ISolution solution1 = new Solution1();
    testSolution(solution1);

    ISolution solution2 = new Solution2();
    testSolution(solution2);
  }

  private static void testSolution(ISolution solution) {
    // @formatter:off
    int[][] matrix = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    // @formatter:on

    withTimerNs(() -> solution.rotate(matrix), "rotate(matrix)");

    // @formatter:off
    assertThat(matrix).isEqualTo(new int[][]{
        {7, 4, 1},
        {8, 5, 2},
        {9, 6, 3}
    });
    // @formatter:on

    // @formatter:off
    int[][] matrix2 = {
        {1, 2, 3, 4, 5, 6, 7, 8, 9},
        {1, 2, 3, 4, 5, 6, 7, 8, 9},
        {1, 2, 3, 4, 5, 6, 7, 8, 9},
        {1, 2, 3, 4, 5, 6, 7, 8, 9},
        {1, 2, 3, 4, 5, 6, 7, 8, 9},
        {1, 2, 3, 4, 5, 6, 7, 8, 9},
        {1, 2, 3, 4, 5, 6, 7, 8, 9},
        {1, 2, 3, 4, 5, 6, 7, 8, 9},
        {1, 2, 3, 4, 5, 6, 7, 8, 9}
    };
    // @formatter:on

    withTimerNs(() -> solution.rotate(matrix2), "rotate(matrix)");

    // @formatter:off
    assertThat(matrix2).isEqualTo(new int[][]{
        {1, 1, 1, 1, 1, 1, 1, 1, 1},
        {2, 2, 2, 2, 2, 2, 2, 2, 2},
        {3, 3, 3, 3, 3, 3, 3, 3, 3},
        {4, 4, 4, 4, 4, 4, 4, 4, 4},
        {5, 5, 5, 5, 5, 5, 5, 5, 5},
        {6, 6, 6, 6, 6, 6, 6, 6, 6},
        {7, 7, 7, 7, 7, 7, 7, 7, 7},
        {8, 8, 8, 8, 8, 8, 8, 8, 8},
        {9, 9, 9, 9, 9, 9, 9, 9, 9},
    });
    // @formatter:on
  }
}
