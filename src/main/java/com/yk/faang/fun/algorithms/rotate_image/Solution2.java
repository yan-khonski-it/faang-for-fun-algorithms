package com.yk.faang.fun.algorithms.rotate_image;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/rotate-image/
 * <p>
 * 48. Rotate Image
 * <p>
 * Optimal solution. #Google
 * <p>
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate
 * another 2D matrix and do the rotation.
 */
class Solution2 {

  // This solution is not optimal. It uses a buffer of size n.
  // Use layers. Copy first row of the layer into a buffer.
  // Copy left column of the layer into a the upper row of the layer.
  // Then we copy lower row of the layer into left column of the layer.
  // Then we copy right column of the layer into the lower column of the layer.
  // Then copy the buffer into the right column of the layer.
  // Then use another layer and repeat this for all the layers.
  public void rotate(int[][] matrix) {
    if (matrix.length <= 1) {
      return;
    }

    final int layersCount = matrix.length / 2;
    final int[] row0 = new int[matrix.length]; // common buffer for all layers row[0].

    for (int layer = 0; layer < layersCount; layer++) {
      final int layerSize = matrix.length - layer * 2;
      if (layerSize <= 0) {
        return;
      }

      copyArray(matrix[layer], row0, layer, layerSize);

      final int layerBorder = matrix.length - 1 - layer;

      rotateColumnIntoRowUpper(matrix, layer, layer, layer, layerSize);
      rotateRowIntoColumnLeft(matrix, layerBorder, layer, layer, layerSize);
      rotateColumnIntoRowLower(matrix, layerBorder, layerBorder, layer, layerSize);
      copyArrayIntoColumn(matrix, row0, layerBorder, layer, layerSize);
    }
  }

  private void copyArray(final int[] source, final int[] destination, final int startPosition,
      final int elementsNumber) {
    for (int i = 0; i < elementsNumber; i++) {
      destination[i] = source[startPosition + i];
    }
  }

  private void copyArrayIntoColumn(final int[][] matrix, final int[] array, final int column, final int start,
      final int nElements) {
    for (int i = 0; i < nElements; i++) {
      matrix[start + i][column] = array[i];
    }
  }

  private void rotateColumnIntoRowUpper(final int[][] matrix, final int row, final int column, final int start,
      final int nElements) {
    for (int i = 0; i < nElements; i++) {
      matrix[row][start + nElements - 1 - i] = matrix[start + i][column];
    }
  }

  private void rotateColumnIntoRowLower(final int[][] matrix, final int row, final int column, final int start,
      final int nElements) {
    for (int i = 0; i < nElements; i++) {
      matrix[row][start + i] = matrix[start + nElements - 1 - i][column];
    }
  }

  private void rotateRowIntoColumnLeft(final int[][] matrix, final int row, final int column, final int start,
      final int nElements) {
    for (int i = 0; i < nElements; i++) {
      matrix[start + i][column] = matrix[row][start + i];
    }
  }
}

class Main2 {

  public static void main(String[] args) {
    Solution2 solution2 = new Solution2();
    // @formatter:off
    int[][] matrix = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    // @formatter:on

    solution2.rotate(matrix);

    // @formatter:off
    assertThat(matrix).isEqualTo(new int[][]{
        {7, 4, 1},
        {8, 5, 2},
        {9, 6, 3}
    });
    // @formatter:on
  }
}
