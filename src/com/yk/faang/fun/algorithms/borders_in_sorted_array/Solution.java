package com.yk.faang.fun.algorithms.borders_in_sorted_array;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * 34. Find First and Last Position of Element in Sorted Array
 *
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class Solution {

  private static final int[] NOT_FOUND = new int[]{-1, -1};

  // using a binary search, find a target or if it is missing
  // store previous min and max indeces - htey will be used to find starting and ending index of target.
  public int[] searchRange(int[] nums, int target) {
    if (nums.length == 0) {
      return NOT_FOUND;
    } else if (nums.length == 1) {
      return nums[0] == target ? new int[]{0, 0} : NOT_FOUND;
    }

    if (nums[0] > target) {
      return new int[]{-1, -1};
    } else if (nums[nums.length - 1] < target) {
      return NOT_FOUND;
    }

    int minIndex = -1;
    int maxIndex = -1;

    // Search left border
    int minLeft = 0;
    int maxLeft = nums.length - 1;
    while (minLeft <= maxLeft) {
      int middle = (minLeft + maxLeft) / 2;
      if (nums[middle] == target) {
        minIndex = middle;
        maxLeft = middle - 1;
      } else if (nums[middle] < target) {
        minLeft = middle + 1;
      } else {
        maxLeft = middle - 1;
      }
    }

    if (minIndex == -1) {
      return NOT_FOUND;
    }

    // search right border
    int minRight = minIndex;
    int maxRight = nums.length - 1;
    while (minRight <= maxRight) {
      int middle = (minRight + maxRight) / 2;
      if (nums[middle] == target) {
        maxIndex = middle;
        minRight = middle + 1;
      } else if (nums[middle] < target) {
        throw new RuntimeException(
            "This should not happen because we have already checked the left part of the array.");
      } else {
        maxRight = middle - 1;
      }
    }

    return new int[]{minIndex, maxIndex};
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] array = {5,7,7,8,8,10};
    int target = 8;
    int[] borders = solution.searchRange(array, target);
    System.out.println("(" + borders[0] + ", " + borders[1] + ")");
  }
}
