package com.yk.faang.leetcode.l0054_spiral_matrix;

import java.util.List;

public interface ISolution {

  List<Integer> spiralOrder(int[][] matrix);

  // Used for resetting the internal state.
  default void reset() {
  }
}
