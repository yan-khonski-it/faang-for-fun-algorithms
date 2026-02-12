package com.yk.faang.leetcode.l1886_determine_whether_matrix_can_be_obtained_by_rotation;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    Solution solution = new Solution();

    int[][] matrix1 = {{0, 1}, {1, 0}};
    int[][] matrix2 = {{1, 0}, {0, 1}};

    boolean res = solution.findRotation(matrix1, matrix2);
    assertThat(res).isTrue();
  }
}
