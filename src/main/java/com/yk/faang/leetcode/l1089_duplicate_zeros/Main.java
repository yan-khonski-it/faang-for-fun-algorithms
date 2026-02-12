package com.yk.faang.leetcode.l1089_duplicate_zeros;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    Solution solution = new Solution();
    int[] array = {1, 0, 2, 3, 0, 4, 5, 0};
    solution.duplicateZeros(array);
    assertThat(array).isEqualTo(new int[]{1, 0, 0, 2, 3, 0, 0, 4});
  }
}
