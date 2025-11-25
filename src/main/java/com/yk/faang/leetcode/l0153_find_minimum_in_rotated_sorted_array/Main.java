package com.yk.faang.leetcode.l0153_find_minimum_in_rotated_sorted_array;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    Solution solution = new Solution();
    int[] array = {3, 4, 5, 1, 2};
    int res = solution.findMin(array);
    assertThat(res).isEqualTo(1);
  }
}
