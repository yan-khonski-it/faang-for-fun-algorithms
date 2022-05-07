package com.yk.faang.fun.algorithms.remove_duplicates_from_sorted_array;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * <p>
 * 26. Remove Duplicates from Sorted Array
 * <p>
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique
 * element appears only once. The relative order of the elements should be kept the same.
 * <p>
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be
 * placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates,
 * then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k
 * elements.
 * <p>
 * Return k after placing the final result in the first k slots of nums.
 * <p>
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra
 * memory.
 */
class Solution {

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
