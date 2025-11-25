package com.yk.faang.leetcode.l0239_sliding_window_maximum;


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
public class Solution3 implements ISolution {

  // Sparse table based solution
  // O(n*log(n))
  int query(int l, int r, int[][] lookup) {

    int len = r - l + 1;

    int k = 0;
    while (1 << (k + 1) < len) {
      k++;
    }

    return Math.max(lookup[l][k], lookup[r - (1 << k) + 1][k]);
  }

  @Override
  public int[] maxSlidingWindow(int[] nums, int k) {

    final int len = nums.length;
    final int logN = (int) Math.ceil(Math.log(len) / Math.log(2));

    int[][] lookup = new int[len][logN];
    int[] res = new int[len - k + 1];

    for (int i = 0; i < len; i++) {
      lookup[i][0] = nums[i];
    }

    for (int step = 1; step < logN; step++) {
      int currPower2 = 1 << step;
      int prevPower2 = 1 << (step - 1);

      for (int i = 0; i + currPower2 - 1 < len; i++) {
        lookup[i][step] = Math.max(lookup[i][step - 1], lookup[i + prevPower2][step - 1]);
      }
    }

    for (int i = 0; i + k <= len; i++) {
      res[i] = query(i, i + k - 1, lookup);
    }
    return res;
  }
}
