package com.yk.faang.leetcode.l0498_diagonal_traverse;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    int[][] matrix1 = new int[][]{
        {1, 2, 3},
        {4, 5, 6},
    };

    int[][] matrix2 = new int[][]{
        {1, 2, 3, 4},
        {5, 6, 7, 8}
    };

    int[][] matrix3 = new int[][]{
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    int[][] matrix4 = new int[][]{
        {2, 5},
        {8, 4},
        {0, -1}
    };

    int[][] matrix5 = new int[][]{
        {1, 2},
        {3, 4}
    };

    Solution solution1 = new Solution();
    int[] diag1 = solution1.findDiagonalOrder(matrix1);
    assertThat(diag1).isEqualTo(new int[]{1, 2, 4, 5, 3, 6});

    Solution solution2 = new Solution();
    int[] diag2 = solution2.findDiagonalOrder(matrix2);
    assertThat(diag2).isEqualTo(new int[]{1, 2, 5, 6, 3, 4, 7, 8});

    Solution solution3 = new Solution();
    int[] diag3 = solution3.findDiagonalOrder(matrix3);
    assertThat(diag3).isEqualTo(new int[]{1, 2, 4, 7, 5, 3, 6, 8, 9});

    Solution solution4 = new Solution();
    int[] diag4 = solution4.findDiagonalOrder(matrix4);
    assertThat(diag4).isEqualTo(new int[]{2, 5, 8, 0, 4, -1});

    Solution solution5 = new Solution();
    int[] diag5 = solution5.findDiagonalOrder(matrix5);
    assertThat(diag5).isEqualTo(new int[]{1, 2, 3, 4});
  }
}
