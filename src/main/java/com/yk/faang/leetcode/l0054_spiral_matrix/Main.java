package com.yk.faang.leetcode.l0054_spiral_matrix;

import static com.yk.faang.utils.TimeUtils.withTimerNs;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;

public class Main {

  static void main() {
    ISolution solution1 = new Solution1();
    testSolution(solution1);

    ISolution solution2 = new Solution2();
    testSolution(solution2);
  }

  private static void testSolution(ISolution solution) {
    int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    List<Integer> spiral1 = solution.spiralOrder(matrix1);
    assertThat(spiral1).isEqualTo(List.of(1, 2, 3, 6, 9, 8, 7, 4, 5));
    solution.reset();

    int[][] matrix2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
    List<Integer> spiral2 = solution.spiralOrder(matrix2);
    assertThat(spiral2).isEqualTo(List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7));
    solution.reset();

    int[][] matrix3 = {{2, 3, 4}, {5, 6, 7}, {8, 9, 10}, {11, 12, 13}};
    List<Integer> spiral3 = withTimerNs(() -> solution.spiralOrder(matrix3), "spiralOrder(matrix3)");
    assertThat(spiral3).isEqualTo(List.of(2, 3, 4, 7, 10, 13, 12, 11, 8, 5, 6, 9));
    solution.reset();
  }
}
