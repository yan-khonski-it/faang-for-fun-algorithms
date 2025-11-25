package com.yk.faang.leetcode.l0026_remove_duplicates_from_sorted_array;

import static org.assertj.core.api.Assertions.assertThat;

public class Main {

  static void main() {
    Solution solution = new Solution();
    int[] array1 = {1, 1, 2};
    int res1 = solution.removeDuplicates(array1);
    assertThat(res1).isEqualTo(2);
    assertThat(array1).startsWith(1, 2);

    int[] array2 = {1, 1, 1, 2, 2, 3};
    int res2 = solution.removeDuplicates(array2);
    assertThat(res2).isEqualTo(3);
    assertThat(array2).startsWith(1, 2, 3);
  }
}
