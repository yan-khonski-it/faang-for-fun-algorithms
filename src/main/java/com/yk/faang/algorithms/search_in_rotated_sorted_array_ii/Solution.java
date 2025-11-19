package com.yk.faang.algorithms.search_in_rotated_sorted_array_ii;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * <p>
 * 81. Search in Rotated Sorted Array II
 * <p>
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 * <p>
 * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that
 * the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For
 * example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 * <p>
 * Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is
 * not in nums.
 * <p>
 * You must decrease the overall operation steps as much as possible.
 * <p>
 * Similar to https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
class Solution {

  public boolean search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return false;
    }

    if (nums.length == 1) {
      return nums[0] == target;
    }

    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
      int middle = (left + right) / 2;
      if (nums[middle] == target) {
        return true;
      }

      if (nums[left] == nums[middle]) {
        left = left + 1;
      } else if (nums[left] < nums[middle]) {
        // we are in the left part
        if (nums[middle] < target || nums[left] > target) {
          left = middle + 1;
        } else {
          right = middle - 1;
        }
      } else {
        // we are in the right part
        if (nums[middle] > target || target > nums[right]) {
          right = middle - 1;
        } else {
          left = middle + 1;
        }
      }
    }

    return false;
  }
}

class Main {

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] array1 = {2, 5, 6, 0, 0, 1, 2};
    int target1 = 0;
    boolean res1 = solution.search(array1, target1);
    assertThat(res1).isTrue();

    int[] array2 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    int target2 = 2;
    boolean res2 = solution.search(array2, target2);
    assertThat(res2).isFalse();
  }
}