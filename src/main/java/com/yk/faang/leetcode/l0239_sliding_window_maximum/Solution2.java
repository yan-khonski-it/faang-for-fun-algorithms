package com.yk.faang.leetcode.l0239_sliding_window_maximum;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * <p>
 * 239. Sliding Window Maximum
 * <p>
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only
 * see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 */
public class Solution2 implements ISolution {

  // O(n * k)
  // Runtime: 2204 ms on leetcode
  // and on my machine
  // 268 ms
  // 402 ms
  @Override
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (k == 1) {
      return nums;
    }

    if (k == nums.length) {
      int[] res = new int[1];
      res[0] = max(nums);
      return res;
    }

    List<Integer> res = new ArrayList<>(nums.length + 1 - k);
    int right = k;
    int left = 0;

    while (right <= nums.length) {
      int maxInLeftRightWindow = max(nums, left, right);
      res.add(maxInLeftRightWindow);
      left++;
      right++;
    }

    return toArray(res);
  }

  private int max(int[] array) {
    return max(array, 0, array.length);
  }

  private int max(int[] array, int start, int end) {
    int res = Integer.MIN_VALUE;
    for (int i = start; i < end; i++) {
      if (array[i] > res) {
        res = array[i];
      }
    }

    return res;
  }

  private int[] toArray(List<Integer> list) {
    int[] res = new int[list.size()];
    for (int i = 0; i < list.size(); i++) {
      res[i] = list.get(i);
    }
    return res;
  }
}
