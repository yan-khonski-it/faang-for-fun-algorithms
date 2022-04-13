package com.yk.faang.fun.algorithms.median_of_two_sorted_arrays;

import static com.yk.faang.fun.algorithms.utils.NumberUtils.assertEqual;

class Solution1 {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length == 0) {
      return median(nums2);
    } else if (nums2.length == 0) {
      return median(nums1);
    } else if (nums1.length == 1 && nums2.length == 1) {
      return average(nums1[0], nums2[0]);
    }

    int index1 = 0;
    int index2 = 0;
    double current = 0;
    double previous = 0;
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
    return average((double) a, (double) b);
  }

  private double average(double a, double b) {
    return (a + b) / 2;
  }

  public static void main(String[] args) {
    Solution1 solution1 = new Solution1();
    int[] array1 = {1, 2};
    int[] array2 = {3};
    int[] array3 = {3, 4};
    double median1 = solution1.findMedianSortedArrays(array1, array2);
    assertEqual(median1, 2.0);
    double median2 = solution1.findMedianSortedArrays(array1, array3);
    assertEqual(median2, 2.5);
  }
}
