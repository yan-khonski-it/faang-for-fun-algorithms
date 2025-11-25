package com.yk.faang.leetcode.l0034_find_first_and_last_position_of_element_in_sorted_array;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    Solution solution = new Solution();
    int[] array = {5, 7, 7, 8, 8, 10};
    int target = 8;
    int[] borders = solution.searchRange(array, target);
    assertThat(borders).isEqualTo(new int[]{3, 4});

    int target2 = 7;
    int[] borders2 = solution.searchRange(array, target2);
    assertThat(borders2).isEqualTo(new int[]{1, 2});

    int target3 = 5;
    int[] borders3 = solution.searchRange(array, target3);
    assertThat(borders3).isEqualTo(new int[]{0, 0});

    int target4 = 9;
    int[] borders4 = solution.searchRange(array, target4);
    assertThat(borders4).isEqualTo(new int[]{-1, -1});
  }
}
