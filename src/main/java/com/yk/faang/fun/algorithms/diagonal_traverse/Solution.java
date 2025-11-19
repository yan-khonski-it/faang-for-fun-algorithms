package com.yk.faang.fun.algorithms.diagonal_traverse;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/diagonal-traverse/description/
 * <p>
 * 498. Diagonal Traverse Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 */
public class Solution {

  private int rows = -1;
  private int columns = -1;

  private int currentRow = 0;
  private int currentColumn = 0;
  private int currentDirection = 1;

  private static int[] copy0thRowAsArray(int[][] matrix) {
    int size = matrix.length;
    int[] res = new int[size];

    for (int i = 0; i < size; i++) {
      res[i] = matrix[i][0];
    }

    return res;
  }

  public int[] findDiagonalOrder(int[][] mat) {
    this.rows = mat.length;
    if (rows == 0) {
      return new int[0];
    }

    this.columns = mat[0].length;
    if (columns == 0) {
      return new int[0];
    }

    if (rows == 1) {
      return mat[0];
    }

    if (columns == 1) {
      return copy0thRowAsArray(mat);
    }

    int visitedCount = 0;
    int totalCount = rows * columns;
    int[] res = new int[totalCount];

    while (visitedCount < totalCount) {
      res[visitedCount] = mat[currentRow][currentColumn];
      nextCell();
      visitedCount++;
    }

    return res;
  }

  private void nextCell() {
    if (currentDirection > 0) {
      currentRow = currentRow - 1;
      currentColumn = currentColumn + 1;
    } else {
      currentRow = currentRow + 1;
      currentColumn = currentColumn - 1;
    }

    if (currentRow < 0) {
      currentRow = 0;
      currentDirection = -currentDirection;
      if (currentColumn >= columns) {
        currentRow = 1;
        currentColumn = columns - 1;
      }
    } else if (currentRow >= rows) {
      currentRow = rows - 1;
      currentDirection = -currentDirection;
      currentColumn = currentColumn + 2;
    } else if (currentColumn < 0) {
      currentColumn = 0;
      currentDirection = -currentDirection;
    } else if (currentColumn >= columns) {
      currentColumn = columns - 1;
      currentDirection = -currentDirection;
      currentRow = currentRow + 2;
    }
  }
}

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
