package com.yk.faang.fun.algorithms.search_in_sorted_array_of_unknown_size;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/
 * <p>
 * 702. Search in a Sorted Array of Unknown Size
 * <p>
 * https://www.youtube.com/watch?v=LQYYkSe_9CY&ab_channel=SandeepKumar
 * <p>
 * Approach: similar to the binary search, but here we need to find the maximum index - the upper boundary of the array.
 * We will use a sliding window, initial size is 10 (it can be any reasonable value). Then each time we don't find a
 * target in the range of elements with (minIndex, maxIndex), we move the window further, by moving the maxIndex and
 * minIndex. We do not know the size of the array, and it can be large. In this case, each new window will be larger.
 * Once we hit the boundary of the array, we can search the element inside the window.
 */

/**
 * This is ArrayReader's API interface.
 */
class ArrayReader {

  private static final int OUTSIDE_VALUE = 999999999;

  private final int[] elements;

  private ArrayReader(int[] elements) {
    this.elements = elements;
  }

  public static ArrayReader of(int... array) {
    return new ArrayReader(array);
  }

  int get(int index) {
    if (index >= elements.length) {
      return OUTSIDE_VALUE;
    }

    return elements[index];
  }
}

class Solution {

  public int search(ArrayReader reader, int target) {
    if (reader == null) {
      return -1;
    }

    int windowSizeMultiplier = 2;

    int minIndex = 0;
    int maxIndex = 10;

    while (reader.get(maxIndex) < target) {
      minIndex = maxIndex;
      maxIndex = maxIndex * windowSizeMultiplier;
    }

    while (minIndex < maxIndex - 1) {
      int middleIndex = minIndex + (maxIndex - minIndex) / 2;
      if (reader.get(middleIndex) == target) {
        return middleIndex;
      } else if (reader.get(middleIndex) < target) {
        minIndex = middleIndex;
      } else {
        maxIndex = middleIndex;
      }
    }

    if (reader.get(minIndex) == target) {
      return minIndex;
    } else if (reader.get(maxIndex) == target) {
      return maxIndex;
    } else {
      return -1;
    }
  }
}

class Main {

  public static void main(String[] args) {
    Solution solution = new Solution();

    ArrayReader nums01 = ArrayReader.of(1);
    int target01 = 1;
    int result01 = solution.search(nums01, target01);
    assertThat(result01).isEqualTo(0);

    int target02 = 2;
    int result02 = solution.search(nums01, target02);
    assertThat(result02).isEqualTo(-1);

    ArrayReader nums1 = ArrayReader.of(1, 2, 3, 4);
    int target03 = 3;
    int result03 = solution.search(nums1, target03);
    assertThat(result03).isEqualTo(2);

    ArrayReader nums2 = ArrayReader.of(1, 2, 3, 4, 6, 9, 11, 14, 18, 22, 24, 31, 36, 38, 41, 44, 47, 300, 1000, 2022,
        2033, 2044, 2099, 3010, 3011, 3012);

    int target2 = 44;
    int result2 = solution.search(nums2, target2);
    assertThat(result2).isEqualTo(15);
  }
}
