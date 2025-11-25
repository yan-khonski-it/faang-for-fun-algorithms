package com.yk.faang.leetcode.l0074_search_in_matrix;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    Solution solution = new Solution();

    // @formatter:off
    int[][] matrix = {
        {1, 3, 5, 7},
        {10, 11, 16, 20},
        {23, 30, 34, 60}
    };
    // @formatter:on

    int target = 3;
    boolean res = solution.searchMatrix(matrix, target);
    assertThat(res).isTrue();

    int target2 = 60;
    boolean res2 = solution.searchMatrix(matrix, target2);
    assertThat(res2).isTrue();

    int target3 = 33;
    boolean res3 = solution.searchMatrix(matrix, target3);
    assertThat(res3).isFalse();

    int target4 = 61;
    boolean res4 = solution.searchMatrix(matrix, target4);
    assertThat(res4).isFalse();
  }
}
