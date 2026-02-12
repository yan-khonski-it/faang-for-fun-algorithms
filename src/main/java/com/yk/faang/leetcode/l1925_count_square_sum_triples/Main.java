package com.yk.faang.leetcode.l1925_count_square_sum_triples;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    Solution solution = new Solution();
    int res = solution.countTriples(5);
    assertThat(res).isEqualTo(2);
  }
}
