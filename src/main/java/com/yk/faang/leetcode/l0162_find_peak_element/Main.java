package com.yk.faang.leetcode.l0162_find_peak_element;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    Solution solution = new Solution();
    int[] array = {1, 2, 3, 1};
    int res = solution.findPeakElement(array);
    assertThat(res).isEqualTo(2);
  }
}
