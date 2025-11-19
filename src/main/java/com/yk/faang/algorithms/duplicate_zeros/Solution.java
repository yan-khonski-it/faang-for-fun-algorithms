package com.yk.faang.algorithms.duplicate_zeros;

import static org.assertj.core.api.Assertions.assertThat;

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

class Main {

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] array = {1, 0, 2, 3, 0, 4, 5, 0};
    solution.duplicateZeros(array);
    assertThat(array).isEqualTo(new int[]{1, 0, 0, 2, 3, 0, 0, 4});
  }
}
