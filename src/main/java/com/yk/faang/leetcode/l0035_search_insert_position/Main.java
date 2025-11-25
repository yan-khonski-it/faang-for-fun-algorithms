package com.yk.faang.leetcode.l0035_search_insert_position;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    Solution solution = new Solution();

    int[] array = {1, 3, 5, 6};
    int target1 = 5;
    int res1 = solution.searchInsert(array, target1);
    assertThat(res1).isEqualTo(2);

    int target2 = 2;
    int res2 = solution.searchInsert(array, target2);
    assertThat(res2).isEqualTo(1);
  }
}
