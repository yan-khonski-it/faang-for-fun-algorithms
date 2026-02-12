package com.yk.faang.leetcode.l0283_move_zeroes;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    Solution solution = new Solution();
    int[] array = {0, 1, 0, 3, 12};
    solution.moveZeroes(array);
    assertThat(array).isEqualTo(new int[]{1, 3, 12, 0, 0});
  }
}
