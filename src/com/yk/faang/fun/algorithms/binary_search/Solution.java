package com.yk.faang.fun.algorithms.binary_search;

/**
 * https://leetcode.com/problems/binary-search/
 *
 * Given an array of integers nums which is sorted in ascending order, and an integer target,
 * write a function to search target in nums.
 * If target exists, then return its index. Otherwise, return -1.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class Solution {

  public int search(int[] nums, int target) {
    if (nums.length == 1) {
      return nums[0] == target ? 0 : -1;
    }

    int minIndex = 0;
    int maxIndex = nums.length - 1;
    while (minIndex <= maxIndex) {
      int middleIndex = (maxIndex + minIndex) / 2;
      if (nums[middleIndex] == target) {
        return middleIndex;
      } else if (nums[middleIndex] < target) {
        minIndex = middleIndex + 1;
      } else {
        maxIndex = middleIndex - 1;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();

    int[] array1 = {1, 2, 3, 4};
    int target1 = 3;
    int result1 = solution.search(array1, target1);
    System.out.println("result1: " + result1);

    int[] array2 = {1, 2, 3, 4, 300, 1000, 2022};
    int target2 = 999;
    int result2 = solution.search(array2, target2);
    System.out.println("result2: " + result2);
  }
}

// approach
// {1, 3},  2
// Iteration 0
// result -1

// {0, 1, 2, 3}, 2
// Iteration 0
// minIndex = 0, maxIndex = 3, middleIndex = 1
// 1 < 2, minIndex = 1
// Iteration 1
// minIndex = 1, maxIndex = 3, middleIndex = 2
// return 2

// {0, 1, 2, 3, 5}, 3
// Iteration 0
// minIndex = 0, maxIndex = 4, middleIndex = 2
// 2 < 3, minIndex = 2
// Iteration 1
// minIndex = 2, maxIndex = 4, middleIndex = 3
// return 3
