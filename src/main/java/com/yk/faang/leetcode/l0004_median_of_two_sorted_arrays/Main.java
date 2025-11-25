package com.yk.faang.leetcode.l0004_median_of_two_sorted_arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Main {

  static void main() {
    ISolution solution = new Solution();
    testSolution(solution);

    ISolution solution1 = new Solution1();
    testSolution(solution1);

    ISolution solution2 = new Solution2();
    testSolution(solution2);
  }

  private static void testSolution(ISolution solution) {
    int[] array1 = {1, 2};
    int[] array2 = {3};
    int[] array3 = {3, 4};
    double median1 = solution.findMedianSortedArrays(array1, array2);
    assertThat(median1).isEqualTo(2.0);
    double median2 = solution.findMedianSortedArrays(array1, array3);
    assertThat(median2).isEqualTo(2.5);
  }
}
