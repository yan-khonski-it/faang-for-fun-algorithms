package com.yk.faang.leetcode.l0287_find_the_duplicate_number;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/submissions/
 * <p>
 * 287. Find the Duplicate Number
 * <p>
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive. There is only one repeated number in nums,
 * return this repeated number. You must solve the problem without modifying the array nums and uses only constant extra space.
 */
class Solution1 implements FindDuplicates {

  @Override
  public int findDuplicate(int[] nums) {
    // Dirichlet principle - n + 1 boxes, n or less numbers - at least one number is repeated
    // find middle element (even if it is not present), count elements smaller than middle smallerThanMiddleCount.
    // if smallerThanMiddleCount less than middle,
    // than the second part of the array contains duplicates because it has less elements than boxes (indices of the array)
    // O(n * log(n)) - n elements to count, log(n) - iterations of splitting (divide and conquer).

    int start = 0;
    int end = nums.length - 1;

    while (start <= end) {
      int middle = (start + end) / 2;

      int smallerEqualThanMiddleCount = 0;
      for (int num : nums) {
        if (num <= middle) {
          smallerEqualThanMiddleCount++;
        }
      }

      if (smallerEqualThanMiddleCount <= middle) {
        // search the second part of the array
        start = middle + 1;
      } else {
        end = middle - 1;
      }
    }

    return start;
  }
}
