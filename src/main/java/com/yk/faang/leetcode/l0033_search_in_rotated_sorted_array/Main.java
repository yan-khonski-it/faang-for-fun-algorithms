package com.yk.faang.leetcode.l0033_search_in_rotated_sorted_array;

import static org.assertj.core.api.Assertions.assertThat;

public class Main {

  static void main() {
    Solution solution = new Solution();
    int[] array1 = {4, 5, 6, 7, 0, 1, 2};
    int target1 = 0;
    int res1 = solution.search(array1, target1);
    assertThat(res1).isEqualTo(4);

    int[] array2 = {3, 1};
    int target2 = 1;
    int res2 = solution.search(array2, target2);
    assertThat(res2).isEqualTo(1);
  }
}
