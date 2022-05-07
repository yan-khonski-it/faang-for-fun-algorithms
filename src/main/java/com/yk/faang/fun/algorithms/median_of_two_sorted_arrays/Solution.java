package com.yk.faang.fun.algorithms.median_of_two_sorted_arrays;

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
class Solution {

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length == 0) {
      return median(nums2);
    } else if (nums2.length == 0) {
      return median(nums1);
    } else if (nums1.length == 1 && nums2.length == 1) {
      return average(nums1[0], nums2[0]);
    }

    // O(log(min(n, m)))
    // swap, to have first array smaller - less number of iterations
    if (nums1.length > nums2.length) {
      int[] tmp = nums1;
      nums1 = nums2;
      nums2 = tmp;
    }

    int totalLength = nums1.length + nums2.length;
    boolean totalLengthEven = totalLength % 2 == 0;

    // perform binary search in the smaller array
    int left1 = 0;
    int right1 = nums1.length;

    while (left1 <= right1) {
      int partition1 = (left1 + right1) / 2;
      int partition2 = (totalLength + 1) / 2 - partition1;

      int beforePartition1 = partition1 - 1 < 0 ? Integer.MIN_VALUE : nums1[partition1 - 1];
      int afterPartition1 = partition1 >= nums1.length ? Integer.MAX_VALUE : nums1[partition1];
      int beforePArtition2 = partition2 - 1 < 0 ? Integer.MIN_VALUE : nums2[partition2 - 1];
      int afterPartition2 = partition2 >= nums2.length ? Integer.MAX_VALUE : nums2[partition2];

      if (beforePartition1 <= afterPartition2 && beforePArtition2 <= afterPartition1) {
        // We found partitions
        return totalLengthEven ? average(Math.max(beforePartition1, beforePArtition2),
            Math.min(afterPartition1, afterPartition2)) : Math.max(beforePartition1, beforePArtition2);
      } else if (beforePartition1 > afterPartition2) {
        right1 = partition1 - 1;
      } else if (beforePArtition2 > afterPartition1) {
        left1 = partition1 + 1;
      }
    }

    throw new IllegalArgumentException("Arrays are not sorted.");
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

@SuppressWarnings("ALL")
class Main {

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] array1 = {1, 2};
    int[] array2 = {3};
    int[] array3 = {3, 4};
    double median1 = solution.findMedianSortedArrays(array1, array2);
    assertThat(median1).isEqualTo(2.0);
    double median2 = solution.findMedianSortedArrays(array1, array3);
    assertThat(median2).isEqualTo(2.5);
  }
}
