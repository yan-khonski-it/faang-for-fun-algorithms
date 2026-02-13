package com.yk.faang.utils;

import java.util.Arrays;

public final class MatrixUtils {

  private MatrixUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static String matrixToString(int[][] matrix) {
    StringBuilder res = new StringBuilder();
    for (int[] row : matrix) {
      res.append(Arrays.toString(row));
      res.append("\n");
    }
    return res.toString();
  }
}
