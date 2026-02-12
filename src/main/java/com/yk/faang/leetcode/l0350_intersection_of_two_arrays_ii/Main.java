package com.yk.faang.leetcode.l0350_intersection_of_two_arrays_ii;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    int[] nums1 = {1, 2, 2, 1};
    int[] nums2 = {2, 2};
    Solution solution = new Solution();
    int[] res = solution.intersect(nums1, nums2);
    assertThat(res).isEqualTo(new int[]{2, 2});
  }
}
