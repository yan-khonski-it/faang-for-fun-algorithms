package com.yk.faang.leetcode.l0702_search_in_sorted_array_of_unknown_size;

/**
 * https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/
 * <p>
 * 702. Search in a Sorted Array of Unknown Size
 * <p>
 * https://www.youtube.com/watch?v=LQYYkSe_9CY&ab_channel=SandeepKumar
 * <p>
 * Approach: similar to the binary search, but here we need to find the maximum index - the upper boundary of the array. We will use a sliding window, initial
 * size is 10 (it can be any reasonable value). Then each time we don't find a target in the range of elements with (minIndex, maxIndex), we move the window
 * further, by moving the maxIndex and minIndex. We do not know the size of the array, and it can be large. In this case, each new window will be larger. Once
 * we hit the boundary of the array, we can search the element inside the window.
 */
class Solution {

  public int search(ArrayReader reader, int target) {
    if (reader == null) {
      return -1;
    }

    if (reader.get(0) == target) {
      return 0;
    }

    int minIndex = 0;
    int maxIndex = 1;

    while (reader.get(maxIndex) < target) {
      minIndex = maxIndex;
      maxIndex = maxIndex << 1;
    }

    while (minIndex <= maxIndex) {
      int middleIndex = minIndex + (maxIndex - minIndex) / 2;
      int number = reader.get(middleIndex);
      if (number == target) {
        return middleIndex;
      } else if (number < target) {
        minIndex = middleIndex + 1;
      } else {
        maxIndex = middleIndex - 1;
      }
    }

    return -1;
  }
}


