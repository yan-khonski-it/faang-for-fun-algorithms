package com.yk.faang.algorithms.find_peak_element;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/find-peak-element/
 *
 * 162. Find Peak Element
 *
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * You must write an algorithm that runs in O(log n) time.
 */
class Solution {

  public int findPeakElement(int[] nums) {
    if (nums.length == 1) {
      return 0;
    }

    if (nums[0] > nums[1]) {
      return 0;
    }

    if (nums[nums.length - 1] > nums[nums.length - 2]) {
      return nums.length - 1;
    }

    int left = 0;
    int right = nums.length - 1;

    while (left < right) {
      int middle = (left + right) / 2;
      if (nums[middle] < nums[middle + 1]) {
        left = middle + 1;
      } else {
        right = middle;
      }
    }

    return left;
  }
}

class Main {

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] array = {1, 2, 3, 1};
    int res = solution.findPeakElement(array);
    assertThat(res).isEqualTo(2);
  }
}