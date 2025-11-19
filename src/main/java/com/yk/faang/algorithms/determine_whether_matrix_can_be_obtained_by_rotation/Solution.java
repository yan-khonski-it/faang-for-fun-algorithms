package com.yk.faang.algorithms.determine_whether_matrix_can_be_obtained_by_rotation;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/
 * <p>
 * 1886. Determine Whether Matrix Can Be Obtained By Rotation
 * <p>
 * Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating
 * mat in 90-degree increments, or false otherwise.
 */
class Solution {

  // check 0 deg, 180 deg, transform, check 90 and 270 deg
  public boolean findRotation(int[][] mat, int[][] target) {
    if (areMatrixEqual(mat, target)) {
      return true;
    }

    if (check180Deg(mat, target)) {
      return true;
    }

    transform(mat);

    return check90Deg(mat, target) || check270Deg(mat, target);
  }

  private boolean areMatrixEqual(int[][] a, int[][] b) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (a[i][j] != b[i][j]) {
          return false;
        }
      }
    }

    return true;
  }

  private boolean check180Deg(int[][] a, int[][] b) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (a[i][j] != b[n - 1 - i][n - 1 - j]) {
          return false;
        }
      }
    }

    return true;
  }

  private boolean check90Deg(int[][] a, int[][] b) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (a[i][j] != b[i][n - 1 - j]) {
          return false;
        }
      }
    }

    return true;
  }

  private boolean check270Deg(int[][] a, int[][] b) {
    int n = a.length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (a[i][j] != b[n - 1 - i][j]) {
          return false;
        }
      }
    }

    return true;
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
}

class Main {

  public static void main(String[] args) {
    Solution solution = new Solution();

    int[][] matrix1 = {{0, 1}, {1, 0}};
    int[][] matrix2 = {{1, 0}, {0, 1}};

    boolean res = solution.findRotation(matrix1, matrix2);
    assertThat(res).isTrue();
  }
}
