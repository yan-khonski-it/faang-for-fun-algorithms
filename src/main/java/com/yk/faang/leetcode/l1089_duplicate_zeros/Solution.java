package com.yk.faang.leetcode.l1089_duplicate_zeros;

/**
 * https://leetcode.com/problems/duplicate-zeros/
 * <p>
 * 1089. Duplicate Zeros
 * <p>
 * Given a fixed-length integer array arr, duplicate each occurrence of zero, shifting the remaining elements to the
 * right. Note that elements beyond the length of the original array are not written. Do the above modifications to the
 * input array in place and do not return anything.
 */
class Solution {

  public void duplicateZeros(int[] arr) {
    int zeroCount = countZeros(arr);
    int lastIndex = arr.length - 1;

    for (int i = 0; i <= lastIndex; i++) {
      int index = lastIndex - i + zeroCount;
      if (index <= lastIndex) {
        arr[index] = arr[lastIndex - i];
      }

      if (arr[lastIndex - i] == 0) {
        zeroCount--;
        if (index - 1 < arr.length) {
          arr[index - 1] = 0;
        }
      }
    }
  }

  private int countZeros(int[] array) {
    int count = 0;
    for (int number : array) {
      if (number == 0) {
        count++;
      }
    }
    return count;
  }
}
