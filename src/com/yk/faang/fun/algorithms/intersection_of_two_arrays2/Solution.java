package com.yk.faang.fun.algorithms.intersection_of_two_arrays2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/submissions/
 *
 * 350. Intersection of Two Arrays II
 *
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 *
 * Coding in notepad
 * https://docs.google.com/document/d/1LRkOF8LlZpzmpNRgUuRwijoaHjsYl-TYNpYFfFejeuA/edit?usp=sharing
 */
class Solution {

  public int[] intersect(int[] nums1, int[] nums2) {
    int[] array1 = nums1;
    int[] array2 = nums2;
    if (nums2.length > nums1.length) {
      array1 = nums2;
      array2 = nums1;
    }

    Arrays.sort(array1);
    Arrays.sort(array2);

    List<Integer> res = new ArrayList<>();

    int index1 = 0;
    int index2 = 0;

    while (index1 < array1.length && index2 < array2.length) {
      if (array1[index1] ==array2[index2] ) {
        res.add(array1[index1]);
        index1++;
        index2++;
      } else if (array1[index1] < array2[index2]) {
        index1++;
      } else {
        index2++;
      }
    }

    return toArray(res);
  }

  private int[] toArray(List<Integer> list) {
    int size = list.size();
    int[] res = new int[size ];
    for (int i = 0; i < size ; i++) {
      res[i] = list.get(i);
    }

    return res;
  }

  public static void main(String[] args) {
    int[] nums1 = {1, 2, 2, 1};
    int[] nums2 = {2, 2};
    Solution solution = new Solution();
    int[] res = solution.intersect(nums1, nums2);
    String formattedResult = Arrays.stream(res)
        .mapToObj(number -> number + "")
        .collect(Collectors.joining(", "));
    System.out.println(formattedResult);
  }
}
