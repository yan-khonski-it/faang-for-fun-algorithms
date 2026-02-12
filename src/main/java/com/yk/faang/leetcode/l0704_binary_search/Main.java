package com.yk.faang.leetcode.l0704_binary_search;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    Solution solution = new Solution();

    int[] array1 = {1, 2, 3, 4};
    int target1 = 3;
    int result1 = solution.search(array1, target1);
    assertThat(result1).isEqualTo(2);

    int[] array2 = {1, 2, 3, 4, 300, 1000, 2022};
    int target2 = 999;
    int result2 = solution.search(array2, target2);
    assertThat(result2).isEqualTo(-1);
  }
}
