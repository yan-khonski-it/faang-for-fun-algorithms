package com.yk.faang.leetcode.l0048_rotate_image;

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
class Solution1 {

  // For each layer, iterate though first row of the layer and move its elements by 90 degrees, one by one.
  // Complexity O(N), where N is total number of the elements in the matrix, each element is moved once.
  public void rotate(int[][] matrix) {
    if (matrix == null || matrix.length == 1) {
      return;
    }

    int matrixSize = matrix.length;
    int layerCount = matrixSize / 2;
    for (int layer = 0; layer < layerCount; layer++) {
      int layerSize = matrixSize - layer * 2;

      for (int i = 0; i < layerSize - 1; i++) {
        // Move each element by 90 degrees.
        int tmp = matrix[layer][layer + i];
        matrix[layer][layer + i] = matrix[matrixSize - 1 - layer - i][layer];
        matrix[matrixSize - 1 - layer - i][layer] = matrix[matrixSize - 1 - layer][matrixSize - 1 - layer - i];
        matrix[matrixSize - 1 - layer][matrixSize - 1 - layer - i] = matrix[layer + i][matrixSize - 1 - layer];
        matrix[layer + i][matrixSize - 1 - layer] = tmp;
      }
    }
  }
}

class Main1 {

  static void main() {
    Solution1 solution1 = new Solution1();

    // @formatter:off
    int[][] matrix = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    // @formatter:on

    solution1.rotate(matrix);

    // @formatter:off
    assertThat(matrix).isEqualTo(new int[][]{
        {7, 4, 1},
        {8, 5, 2},
        {9, 6, 3}
    });
    // @formatter:on
  }
}
