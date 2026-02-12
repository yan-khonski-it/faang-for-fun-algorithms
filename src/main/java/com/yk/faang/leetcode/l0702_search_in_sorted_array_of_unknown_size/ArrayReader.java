package com.yk.faang.leetcode.l0702_search_in_sorted_array_of_unknown_size;

/**
 * This is ArrayReader's API interface.
 */
record ArrayReader(int[] elements) {

  private static final int OUTSIDE_VALUE = 999999999;

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
