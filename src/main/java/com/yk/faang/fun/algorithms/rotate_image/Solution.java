package com.yk.faang.fun.algorithms.rotate_image;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Optimal solution. #Google
 * <p>
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate
 * another 2D matrix and do the rotation.
 */
class Solution {

  // Transform the matrix, so rows become columns and columns become rows.
  // Swap row[i] with row[n - 1 - i].
  public void rotate(int[][] matrix) {
    transform(matrix);
    swapColumns(matrix);
  }

  private void transform(int[][] matrix) {
    int n = matrix.length;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = tmp;
      }
    }
  }

  private void swapColumns(int[][] matrix) {
    int n = matrix.length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n / 2; j++) {
        int tmp = matrix[i][j];
        int rightColumnIndex = n - 1 - j;
        matrix[i][j] = matrix[i][rightColumnIndex];
        matrix[i][rightColumnIndex] = tmp;
      }
    }
  }
}

class Main {

  public static void main(String[] args) {
    Solution solution = new Solution();

    // @formatter:off
    int[][] matrix = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    // @formatter:on

    solution.rotate(matrix);

    // @formatter:off
    assertThat(matrix).isEqualTo(new int[][]{
        {7, 4, 1},
        {8, 5, 2},
        {9, 6, 3}
    });
    // @formatter:on
  }
}