package com.yk.faang.leetcode.l0702_search_in_sorted_array_of_unknown_size;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    Solution solution = new Solution();

    ArrayReader nums01 = ArrayReader.of(1);
    int target01 = 1;
    int result01 = solution.search(nums01, target01);
    assertThat(result01).isEqualTo(0);

    int target02 = 2;
    int result02 = solution.search(nums01, target02);
    assertThat(result02).isEqualTo(-1);

    ArrayReader nums1 = ArrayReader.of(1, 2, 3, 4);
    int target03 = 3;
    int result03 = solution.search(nums1, target03);
    assertThat(result03).isEqualTo(2);

    ArrayReader nums2 = ArrayReader.of(1, 2, 3, 4, 6, 9, 11, 14, 18, 22, 24, 31, 36, 38, 41, 44, 47, 300, 1000, 2022,
        2033, 2044, 2099, 3010, 3011, 3012);

    int target2 = 44;
    int result2 = solution.search(nums2, target2);
    assertThat(result2).isEqualTo(15);
  }
}
