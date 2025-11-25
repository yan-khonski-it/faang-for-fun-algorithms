package com.yk.faang.leetcode.l0069_sqrtx;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    Solution solution = new Solution();
    int x = 1000000;
    int sqrtX = solution.mySqrt(x);
    assertThat(sqrtX).isEqualTo(1000);
  }
}
