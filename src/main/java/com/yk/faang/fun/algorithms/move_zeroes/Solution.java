package com.yk.faang.fun.algorithms.move_zeroes;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/move-zeroes/
 * <p>
 * 283. Move Zeroes
 * <p>
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero
 * elements. Note that you must do this in-place without making a copy of the array.
 */
class Solution {

  // Originally, similarly to https://leetcode.com/problems/duplicate-zeros/
  // we write into current position element of the current index + current zeros count.
  // nums[index] = nums[index + zerosCount];
  // if (nums[index] == 0) zerosCount++ else index++;
  // Here time complexity is O(n) because total number of writes is limited by array size minus current zeros count
  // (which is also limited by array size).
  // However, here we first allow to pass multiple zeros (it is becomes more effecient if zeros are repeated).
  // Also we do not write current element, if zerosCount is 0. Those steps allow us to save write operations.
  // Memory O(1), but time complexity is O(n) because each element could be written at most once!
  public void moveZeroes(int[] nums) {
    if (nums.length == 1) {
      return;
    }

    int index = 0;
    int zerosCount = 0;

    while (index + zerosCount < nums.length) {
      if (nums[index + zerosCount] == 0) {
        zerosCount++;
        continue;
      }

      if (zerosCount > 0) {
        nums[index] = nums[index + zerosCount];
      }

      index++;
    }

    while (zerosCount > 0) {
      nums[nums.length - zerosCount] = 0;
      zerosCount--;
    }
  }
}

class Main {

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] array = {0, 1, 0, 3, 12};
    solution.moveZeroes(array);
    assertThat(array).isEqualTo(new int[]{1, 3, 12, 0, 0});
  }
}