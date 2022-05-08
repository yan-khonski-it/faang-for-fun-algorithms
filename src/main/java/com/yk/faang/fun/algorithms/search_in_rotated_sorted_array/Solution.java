package com.yk.faang.fun.algorithms.search_in_rotated_sorted_array;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * <p>
 * 33. Search in Rotated Sorted Array
 * <p>
 * There is an integer array nums sorted in ascending order (with distinct values).
 * <p>
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * <p>
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
 * or -1 if it is not in nums.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * Coding in notepad https://docs.google.com/document/d/1XjDHc_VHbsRA0_xEQO4ZZ74gafxPtknWGxCTetCcVZY/edit?usp=sharing
 * <p>
 * Similar problems:
 * <p>
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * <p>
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
class Solution {

  // O(log(n))
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
      int middle = (left + right) / 2;
      if (nums[middle] == target) {
        return middle;
      }

      if (nums[left] <= nums[middle]) {
        // we are in the left part
        if (nums[middle] < target || target < nums[left]) {
          left = middle + 1;
        } else {
          right = middle - 1;
        }
      } else {
        // we are in the right part
        if (target < nums[middle] || target > nums[right]) {
          right = middle - 1;
        } else {
          left = middle + 1;
        }
      }
    }

    return -1;
  }
}

class Main {

  public static void main(String[] args) {
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