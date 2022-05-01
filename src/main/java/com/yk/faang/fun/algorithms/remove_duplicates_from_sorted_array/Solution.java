package com.yk.faang.fun.algorithms.remove_duplicates_from_sorted_array;

import static org.assertj.core.api.Assertions.assertThat;

public class Solution {

  public int removeDuplicates(int[] nums) {
    if (nums.length <= 1) {
      return nums.length;
    }

    int insertedIndex = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != nums[i - 1]) {
        nums[insertedIndex] = nums[i];
        insertedIndex++;
      }
    }

    return insertedIndex;
  }
}

class Main {

  public static void main(String[] args) {
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
