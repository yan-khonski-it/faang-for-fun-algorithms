package com.yk.faang.leetcode.l1015_divisible_by_k;

import static org.assertj.core.api.Assertions.assertThat;

public class Main {

  static void main() {
    Solution solution = new Solution();
    int k = 7;
    int res = solution.smallestRepunitDivByK(k);
    assertThat(res).isEqualTo(6);

    k = 99311;
    res = solution.smallestRepunitDivByK(k);
    assertThat(res).isEqualTo(48576);

    k = 17;
    res = solution.smallestRepunitDivByK(k);
    assertThat(res).isEqualTo(16);

    k = 3;
    res = solution.smallestRepunitDivByK(k);
    assertThat(res).isEqualTo(3);

    k = 2;
    res = solution.smallestRepunitDivByK(k);
    assertThat(res).isEqualTo(-1);
  }
}
