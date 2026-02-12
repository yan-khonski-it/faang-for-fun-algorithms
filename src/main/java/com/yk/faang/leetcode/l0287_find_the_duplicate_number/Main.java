package com.yk.faang.leetcode.l0287_find_the_duplicate_number;

import static org.assertj.core.api.Assertions.assertThat;

class Main {

  static void main() {
    test1();
    test2();
    test3();
  }

  private static void test1() {
    Solution1 solution1 = new Solution1();
    Solution2 solution2 = new Solution2();
    int[] array = {1, 2, 3, 3, 4, 5};
    int res1 = solution1.findDuplicate(array);
    int res2 = solution2.findDuplicate(array);
    assertThat(res1).isEqualTo(3);
    assertThat(res2).isEqualTo(3);
  }

  private static void test2() {
    Solution1 solution1 = new Solution1();
    Solution2 solution2 = new Solution2();
    int[] array = {1, 2, 2, 4, 3};
    int res1 = solution1.findDuplicate(array);
    int res2 = solution2.findDuplicate(array);
    assertThat(res1).isEqualTo(2);
    assertThat(res2).isEqualTo(2);
  }

  private static void test3() {
    Solution1 solution1 = new Solution1();
    Solution2 solution2 = new Solution2();
    int[] array = {2,2,2,2,2};
    int res1 = solution1.findDuplicate(array);
    int res2 = solution2.findDuplicate(array);
    assertThat(res1).isEqualTo(2);
    // The second approach does not work because it assumes that the duplicate is repeated twice, and not more.
    // Otherwise, the arithmetic progression sum does not work.
    assertThat(res2).isEqualTo(0);
  }
}
