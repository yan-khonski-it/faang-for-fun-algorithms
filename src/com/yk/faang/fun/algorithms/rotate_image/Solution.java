package com.yk.faang.fun.algorithms.rotate_image;

/**
 * Optimal solution.
 * #Google
 *
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
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

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] matrix = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    solution.rotate(matrix);
    System.out.println(matrixToString(matrix));
  }

  private static String matrixToString(int[][] matrix) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < matrix.length; i++) {
      if (i > 0) {
        sb.append("\n");
      }
      for (int j = 0; j < matrix[i].length; j++) {
        if (j > 0) {
          sb.append(", ");
        }
        sb.append(matrix[i][j]);
      }
    }
    return sb.toString();
  }
}
