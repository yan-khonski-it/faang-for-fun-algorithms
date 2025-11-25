package com.yk.faang.leetcode.l0004_median_of_two_sorted_arrays;


/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * <p>
 * 4. Median of Two Sorted Arrays
 * <p>
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 */
class Solution2 implements ISolution {

  @Override
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length == 0) {
      return median(nums2);
    } else if (nums2.length == 0) {
      return median(nums1);
    } else if (nums1.length == 1 && nums2.length == 1) {
      return average(nums1[0], nums2[0]);
    }

    // Similar to merge two sorter arrays, but here we just count the passed elements
    // (without storing them into a resulting array). We check until checkedNumbersCount == numbersCountToCheck.

    int index1 = 0;
    int index2 = 0;
    double current = 0;
    double previous;
    int checkedNumbersCount = 0;
    int totalLength = nums1.length + nums2.length;
    int numbersCountToCheck = totalLength / 2 + 1;
    boolean totalLengthEven = totalLength % 2 == 0;

    while (index1 < nums1.length && index2 < nums2.length) {
      checkedNumbersCount++;

      previous = current;
      if (nums1[index1] < nums2[index2]) {
        current = nums1[index1];
        index1++;
      } else {
        current = nums2[index2];
        index2++;
      }

      if (checkedNumbersCount == numbersCountToCheck) {
        if (totalLengthEven) {
          return average(previous, current);
        } else {
          return current;
        }
      }
    }

    // The second array is over, continue with the first array
    while (index1 < nums1.length) {
      checkedNumbersCount++;
      previous = current;
      current = nums1[index1];
      index1++;

      if (checkedNumbersCount == numbersCountToCheck) {
        if (totalLengthEven) {
          return average(previous, current);
        } else {
          return current;
        }
      }
    }

    // The first array is over, continue with the second array
    while (index2 < nums2.length) {
      checkedNumbersCount++;
      previous = current;
      current = nums2[index2];
      index2++;

      if (checkedNumbersCount == numbersCountToCheck) {
        if (totalLengthEven) {
          return average(previous, current);
        } else {
          return current;
        }
      }
    }

    return -1;
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
    return average(a, (double) b);
  }

  private double average(double a, double b) {
    return (a + b) / 2;
  }
}
