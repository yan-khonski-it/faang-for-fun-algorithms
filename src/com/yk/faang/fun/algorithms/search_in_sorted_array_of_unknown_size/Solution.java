package com.yk.faang.fun.algorithms.search_in_sorted_array_of_unknown_size;

/**
 * https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/
 *
 * https://www.youtube.com/watch?v=LQYYkSe_9CY&ab_channel=SandeepKumar
 *
 * Approach: similar to the binary search,
 * but here we need to find the maximum index - the upper boundary of the array.
 * We will use a sliding window, initial size is 10 (it can be any reasonable value).
 * Then each time we don't find a target in the range of elements with (minIndex, maxIndex),
 * we move the window further, by moving the maxIndex and minIndex.
 * We do not know the size of the array, and it can be large. In this case, each new window will be larger.
 * Once we hit the boundary of the array, we can search the element inside the window.
 */
public class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();

    UnknownSizedArray nums01 = UnknownSizedArray.of(1);
    int target01 = 1;
    int result01 = solution.search(nums01, target01);
    System.out.println("result01: " + result01);

    int target02 = 2;
    int result02 = solution.search(nums01, target02);
    System.out.println("result02: " + result02);

    UnknownSizedArray nums1 = UnknownSizedArray.of(1, 2, 3, 4);
    int target1 = 3;
    int result1 = solution.search(nums1, target1);
    System.out.println("result1: " + result1);

    UnknownSizedArray nums2 = UnknownSizedArray.of(
        1, 2, 3, 4, 6, 9, 11, 14, 18, 22,
        24, 31, 36, 38, 41, 44, 47, 300, 1000, 2022,
        2033, 2044, 2099, 3010, 3011, 3012
    );

    int target2 = 44;
    int result2 = solution.search(nums2, target2);
    System.out.println("result2: " + result2 + " | " + nums2);
  }

  public int search(UnknownSizedArray nums, int target) {
    if (nums == null) {
      return -1;
    }

    float windowSizeMultiplier = 1.5f;

    int minIndex = 0;
    int maxIndex = 10;

    while (nums.get(maxIndex) < target) {
      if (nums.get(maxIndex) < target) {
        minIndex = maxIndex;
      }

      maxIndex = (int) (maxIndex * windowSizeMultiplier);

    }

    while (minIndex < maxIndex - 1) {
      int middleIndex = minIndex + (maxIndex - minIndex) / 2;
      if (nums.get(middleIndex) == target) {
        return middleIndex;
      } else if (nums.get(middleIndex) < target) {
        minIndex = middleIndex;
      } else {
        maxIndex = middleIndex;
      }
    }

    if (nums.get(minIndex) == target) {
      return minIndex;
    } else if (nums.get(maxIndex) == target) {
      return maxIndex;
    } else {
      return -1;
    }
  }

  static class UnknownSizedArray {

    private static final int OUTSIDE_VALUE = 999999999;

    private final int[] elements;

    private UnknownSizedArray(int[] elements) {
      this.elements = elements;
    }

    public static UnknownSizedArray of(int... array) {
      return new UnknownSizedArray(array);
    }

    int get(int index) {
      if (index >= elements.length) {
        return OUTSIDE_VALUE;
      }

      return elements[index];
    }
  }
}

// approach
// {1, 3},  2
// Iteration 0
// result -1

// {0, 1, 2, 3}, 2
// Iteration 0
// minIndex = 0, maxIndex = 3, middleIndex = 1
// 1 < 2, minIndex = 1
// Iteration 1
// minIndex = 1, maxIndex = 3, middleIndex = 2
// return 2

// {0, 1, 2, 3, 5}, 3
// Iteration 0
// minIndex = 0, maxIndex = 4, middleIndex = 2
// 2 < 3, minIndex = 2
// Iteration 1
// minIndex = 2, maxIndex = 4, middleIndex = 3
// return 3
