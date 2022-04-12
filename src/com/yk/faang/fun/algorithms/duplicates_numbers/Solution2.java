package com.yk.faang.fun.algorithms.duplicates_numbers;

/**
 * If there could be only 2 duplicates, this solution would work.
 *
 * Using arithmetical progression, counting the expected sum of n elements.
 * Using sum of all elements, - actual sum.
 * Returing actual sum - expected sum.
 */
class Solution2 {

  public int findDuplicate(int[] nums) {
    // arithmetic progression - expected sum
    // actual sum
    // and their difference is the duplicate number
    // O(n)

    int expectedSum = (nums.length) * (nums.length - 1) / 2;
    int actualSum = sum(nums);
    return actualSum - expectedSum;
  }

  private int sum(int[] array) {
    int res = 0;
    for (int i = 0; i < array.length; i++) {
      res = res + array[i];
    }

    return res;
  }

  public static void main(String[] args) {
    Solution2 solution2 = new Solution2();
    int[] array = {1, 2, 3, 3, 4, 5};
    int res = solution2.findDuplicate(array);
    System.out.println(res);
  }
}
