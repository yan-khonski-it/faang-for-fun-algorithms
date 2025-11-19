package com.yk.faang.leetcode.l0054_spiral_matrix;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.ArrayList;
import java.util.List;

enum Direction {

  RIGHT,
  DOWN,
  LEFT,
  UP;

  private static final Direction[] vals = values();

  public Direction next() {
    return vals[(ordinal() + 1) % vals.length];
  }
}

/**
 * 54. Spiral Matrix
 * <p>
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 */
public class Solution2 {

  private List<Integer> res;
  private int rows;
  private int minRow = 0;
  private int maxRow;
  private int columns;
  private int minColumn = 0;
  private int maxColumn;
  private int currentRow = minRow;
  private int currentColumn = minColumn;
  private Direction direction = Direction.RIGHT;


  public List<Integer> spiralOrder(int[][] matrix) {
    this.rows = matrix.length;
    if (rows == 0) {
      return List.of();
    }

    if (rows == 1) {
      return copyRow0(matrix);
    }

    this.columns = matrix[0].length;
    if (columns == 0) {
      return List.of();
    }

    if (columns == 1) {
      return copyColumn0(matrix);
    }

    int totalCount = rows * columns;
    int count = 0;
    res = new ArrayList<>(totalCount);
    maxRow = rows - 1;
    maxColumn = columns - 1;

    while (count < totalCount) {
      int value = matrix[currentRow][currentColumn];
      res.add(value);
      move();
      count++;
    }

    return res;
  }

  private void move() {
    switch (direction) {
      case RIGHT:
        currentColumn = currentColumn + 1;
        break;

      case DOWN:
        currentRow = currentRow + 1;
        break;

      case LEFT:
        currentColumn = currentColumn - 1;
        break;

      case UP:
        currentRow = currentRow - 1;
        break;
    }

    if (currentColumn == maxColumn && direction == Direction.RIGHT) {
      maxColumn = maxColumn - 1;
      this.direction = direction.next();
    } else if (currentRow == maxRow && direction == Direction.DOWN) {
      maxRow = maxRow - 1;
      this.direction = direction.next();
    } else if (currentColumn == minColumn && direction == Direction.LEFT) {
      minColumn = minColumn + 1;
      this.direction = direction.next();
    } else if (currentRow == minRow && direction == Direction.UP) {
      minRow = minRow + 1;
      currentRow = minRow;
      currentColumn = minColumn;
      this.direction = direction.next();

      if (currentColumn == maxColumn && direction == Direction.RIGHT) {
        maxColumn = maxColumn - 1;
        this.direction = direction.next();
      }
    }
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

class Main2 {

  static void main() {
    Solution2 solution21 = new Solution2();
    int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    List<Integer> spiral1 = solution21.spiralOrder(matrix1);
    assertThat(spiral1).isEqualTo(List.of(1, 2, 3, 6, 9, 8, 7, 4, 5));

    Solution2 solution22 = new Solution2();
    int[][] matrix2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
    List<Integer> spiral2 = solution22.spiralOrder(matrix2);
    assertThat(spiral2).isEqualTo(List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7));

    Solution2 solution23 = new Solution2();
    int[][] matrix3 = {{2, 3, 4}, {5, 6, 7}, {8, 9, 10}, {11, 12, 13}};
    List<Integer> spiral3 = solution23.spiralOrder(matrix3);
    assertThat(spiral3).isEqualTo(List.of(2, 3, 4, 7, 10, 13, 12, 11, 8, 5, 6, 9));
  }
}