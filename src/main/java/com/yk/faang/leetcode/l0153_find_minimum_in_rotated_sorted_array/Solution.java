package com.yk.faang.leetcode.l0153_find_minimum_in_rotated_sorted_array;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * <p>
 * 153. Find Minimum in Rotated Sorted Array
 * <p>
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums
 * = [0,1,2,4,5,6,7] might become:
 * <p>
 * [4,5,6,7,0,1,2] if it was rotated 4 times. [0,1,2,4,5,6,7] if it was rotated 7 times. Notice that rotating an array
 * [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 * <p>
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * <p>
 * You must write an algorithm that runs in O(log n) time.
 */
class Solution {

  public int findMin(int[] nums) {
    if (nums.length == 1) {
      return nums[0];
    }

    if (nums[nums.length - 1] > nums[0]) {
      return nums[0];
    }

    int left = 0;
    int right = nums.length - 1;

    while (left < right) {
      int middle = (left + right) / 2;
      if (nums[middle] > nums[right]) {
        left = middle + 1;
      } else {
        right = middle;
      }
    }

    return nums[left];
  }
}

class Main {

  static void main() {
    Solution solution = new Solution();
    int[] array = {3, 4, 5, 1, 2};
    int res = solution.findMin(array);
    assertThat(res).isEqualTo(1);
  }
}