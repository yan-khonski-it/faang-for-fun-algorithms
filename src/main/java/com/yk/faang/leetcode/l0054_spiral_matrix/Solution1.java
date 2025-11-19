package com.yk.faang.leetcode.l0054_spiral_matrix;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix
 * <p>
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 */
public class Solution1 {

  public List<Integer> spiralOrder(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) {
      return List.of();
    }

    if (matrix.length == 1) {
      return copyRow0(matrix);
    }

    if (matrix[0].length == 1) {
      return copyColumn0(matrix);
    }

    int totalSize = matrix.length * matrix[0].length;
    List<Integer> res = new ArrayList<>(totalSize);

    int minRow = 0;
    int maxRow = matrix.length;
    int minColumn = 0;
    int maxColumn = matrix[0].length;

    while (minRow < maxRow && minColumn < maxColumn) {
      for (int column = minColumn; column < maxColumn; column++) {
        res.add(matrix[minRow][column]);
      }
      minRow++;

      for (int row = minRow; row < maxRow; row++) {
        res.add(matrix[row][maxColumn - 1]);
      }
      maxColumn--;

      if (minRow != maxRow) {
        for (int column = maxColumn - 1; column >= minColumn; column--) {
          res.add(matrix[maxRow - 1][column]);
        }
        maxRow--;
      }

      if (minColumn != maxColumn) {
        for (int row = maxRow - 1; row >= minRow; row--) {
          res.add(matrix[row][minColumn]);
        }
        minColumn++;
      }
    }

    return res;
  }

  private List<Integer> copyRow0(int[][] matrix) {
    List<Integer> res = new ArrayList<>(matrix.length);

    for (int j = 0; j < matrix[0].length; j++) {
      res.add(matrix[0][j]);
    }

    return res;
  }

  private List<Integer> copyColumn0(int[][] matrix) {
    List<Integer> res = new ArrayList<>(matrix[0].length);

    for (int i = 0; i < matrix.length; i++) {
      res.add(matrix[i][0]);
    }

    return res;
  }
}

class Main {

  static void main() {
    Solution1 solution = new Solution1();
    int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    List<Integer> spiral1 = solution.spiralOrder(matrix1);
    assertThat(spiral1).isEqualTo(List.of(1, 2, 3, 6, 9, 8, 7, 4, 5));

    int[][] matrix2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
    List<Integer> spiral2 = solution.spiralOrder(matrix2);
    assertThat(spiral2).isEqualTo(List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7));

    int[][] matrix3 = {{2,3,4},{5,6,7},{8,9,10},{11,12,13}};
    List<Integer> spiral3 = solution.spiralOrder(matrix3);
    assertThat(spiral3).isEqualTo(List.of(2,3,4,7,10,13,12,11,8,5,6,9));
  }
}