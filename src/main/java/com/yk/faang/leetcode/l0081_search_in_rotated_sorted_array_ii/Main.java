package com.yk.faang.leetcode.l0081_search_in_rotated_sorted_array_ii;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    Solution solution = new Solution();
    int[] array1 = {2, 5, 6, 0, 0, 1, 2};
    int target1 = 0;
    boolean res1 = solution.search(array1, target1);
    assertThat(res1).isTrue();

    int[] array2 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    int target2 = 2;
    boolean res2 = solution.search(array2, target2);
    assertThat(res2).isFalse();
  }
}
