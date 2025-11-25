package com.yk.faang.leetcode.l0162_find_peak_element;

/**
 * https://leetcode.com/problems/find-peak-element/
 * <p>
 * 162. Find Peak Element
 * <p>
 * A peak element is an element that is strictly greater than its neighbors.
 * <p>
 * Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞.
 * <p>
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
