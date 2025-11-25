package com.yk.faang.leetcode.l0035_search_insert_position;

/**
 * https://leetcode.com/problems/search-insert-position/
 * <p>
 * 35. Search Insert Position
 * <p>
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were
 * inserted in order.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 */
class Solution {

  public int searchInsert(int[] nums, int target) {
    int minIndex = 0;
    int maxIndex = nums.length - 1;

    while (minIndex <= maxIndex) {
      int middle = (minIndex + maxIndex) / 2;
      if (nums[middle] == target) {
        return middle;
      } else if (nums[middle] < target) {
        minIndex = middle + 1;
      } else {
        maxIndex = middle - 1;
      }
    }

    return minIndex;
  }
}
