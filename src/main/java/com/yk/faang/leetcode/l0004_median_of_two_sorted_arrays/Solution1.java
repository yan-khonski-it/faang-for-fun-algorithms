package com.yk.faang.leetcode.l0004_median_of_two_sorted_arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * <p>
 * 4. Median of Two Sorted Arrays
 * <p>
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * This is an improved version of {@link Solution2}.
 */
@SuppressWarnings("ALL")
class Solution1 {

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
     if (nums1.length == 0) {
         return median(nums2);
     } else if (nums2.length == 0) {
         return median(nums1);
     } else if (nums1.length == 1 && nums2.length == 1) {
         return average(nums1[0], nums2[0]);
     }

     // O(n + m)
    // Similar to merge two sorted array, but we just check elements.
    int totalSize = nums1.length + nums2.length;
    boolean totalSizeEven = totalSize % 2 == 0;
    int previousElement = 0;
    int currentElement = 0;
    int index1 = 0;
    int index2 = 0;

    for (int i = 0; i <= totalSize / 2; i++) {
      previousElement = currentElement;
      if (index1 >= nums1.length) {
        currentElement = nums2[index2];
        index2++;
      } else if (index2 >= nums2.length) {
        currentElement = nums1[index1];
        index1++;
      } else if (nums1[index1] < nums2[index2]) {
        currentElement = nums1[index1];
        index1++;
      } else {
        currentElement = nums2[index2];
        index2++;
      }
    }

    if (totalSizeEven) {
      return average(previousElement, currentElement);
    } else {
      return currentElement;
    }
  }

  private double median(int[] array) {
    if (array.length == 0) {
      return 0;
    }

    if (array.length == 1) {
      return array[0];
    }

    if (array.length == 2) {
      return average(array[0], array[1]);
    }

    int middle = array.length / 2;
    if (array.length % 2 == 0) {
      return average(array[middle - 1], array[middle]);
    } else {
      return array[middle];
    }
  }

  private double average(int a, int b) {
    return ((double) a + b) / 2;
  }
}

@SuppressWarnings("DuplicatedCode")
class Main1 {

  static void main() {
    Solution1 solution1 = new Solution1();
    int[] array1 = {1, 2};
    int[] array2 = {3};
    int[] array3 = {3, 4};
    double median1 = solution1.findMedianSortedArrays(array1, array2);
    assertThat(median1).isEqualTo(2.0);
    double median2 = solution1.findMedianSortedArrays(array1, array3);
    assertThat(median2).isEqualTo(2.5);
  }
}
