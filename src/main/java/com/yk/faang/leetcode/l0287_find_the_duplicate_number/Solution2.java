package com.yk.faang.leetcode.l0287_find_the_duplicate_number;

/**
 * If there could be only 2 duplicates, this solution would work.
 * <p>
 * Using arithmetical progression, counting the expected sum of n elements. Using sum of all elements, - actual sum. Returning actual sum - expected sum.
 */
class Solution2 implements FindDuplicates {

  @Override
  public int findDuplicate(int[] nums) {
    // arithmetic progression - expected sum
    // actual sum
    // and their difference is the duplicate number
    // O(n)

    int expectedSum = nums.length * (nums.length - 1) / 2;
    int actualSum = sum(nums);
    return actualSum - expectedSum;
  }

  private int sum(int[] array) {
    int res = 0;
    for (int j : array) {
      res = res + j;
    }

    return res;
  }
}
