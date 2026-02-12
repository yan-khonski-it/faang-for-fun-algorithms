package com.yk.faang.leetcode.l0498_diagonal_traverse;


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

