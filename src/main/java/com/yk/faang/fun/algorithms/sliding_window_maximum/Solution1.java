package com.yk.faang.fun.algorithms.sliding_window_maximum;

import static com.yk.faang.fun.utils.TimerUtils.runTestCaseWithTimer;
import static com.yk.faang.fun.utils.TestCaseUtils.readInputAsArray;
import static com.yk.faang.fun.utils.TestCaseUtils.readInputAsArrayAndK;
import static org.assertj.core.api.Assertions.assertThat;

import com.yk.faang.fun.utils.ArrayAndK;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * <p>
 * 239. Sliding Window Maximum
 * <p>
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of
 * the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right
 * by one position.
 * <p>
 * Return the max sliding window.
 */
class Solution1 {

  // O(n)
  // Runtime on my machine
  // 13 ms
  // 19 ms
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (k == 1) {
      return nums;
    }

    if (k == nums.length) {
      int[] res = new int[1];
      res[0] = max(nums);
      return res;
    }

    Deque<Integer> indexesDeque = new LinkedList<>();
    List<Integer> res = new ArrayList<>(nums.length + 1 - k);

    // populate first window
    for (int i = 0; i < k; i++) {
      if (indexesDeque.isEmpty()) {
        indexesDeque.addLast(i);
        continue;
      }

      while (!indexesDeque.isEmpty() && nums[indexesDeque.getLast()] < nums[i]) {
        indexesDeque.removeLast();
      }

      indexesDeque.addLast(i);
    }

    res.add(nums[indexesDeque.getFirst()]);

    int left = 1;
    int right = k;

    while (right < nums.length) {
      while (!indexesDeque.isEmpty() && nums[indexesDeque.getLast()] < nums[right]) {
        indexesDeque.removeLast();
      }

      indexesDeque.addLast(right);

      while (!indexesDeque.isEmpty() && indexesDeque.getFirst() < left) {
        indexesDeque.removeFirst();
      }

      res.add(nums[indexesDeque.getFirst()]);

      right++;
      left++;
    }

    return toArray(res);
  }

  private int max(int[] array) {
    int res = Integer.MIN_VALUE;
    for (int j : array) {
      if (j > res) {
        res = j;
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

@SuppressWarnings("DuplicatedCode")
class Main1 {

  public static void main(String[] args) {
    Solution1 solution1 = new Solution1();
    int[] array1 = {1, 3, -1, -3, 5, 3, 6, 7};
    int k1 = 3;
    int[] res1 = solution1.maxSlidingWindow(array1, k1);
    assertThat(res1).isEqualTo(new int[]{3, 3, 5, 5, 6, 7});

    ArrayAndK arrayAndK2 = readInputAsArrayAndK("test_cases/sliding_window_maximum/test52.txt");
    int[] expectedArray2 = readInputAsArray("test_cases/sliding_window_maximum/test52_expected.txt");
    int[] array2 = arrayAndK2.getArray();
    int k2 = arrayAndK2.getK();

    int[] res2 = runTestCaseWithTimer(() -> { // 13 ms
      return solution1.maxSlidingWindow(array2, k2);
    });
    assertThat(res2).isEqualTo(expectedArray2);

    ArrayAndK arrayAndK3 = readInputAsArrayAndK("test_cases/sliding_window_maximum/test49.txt");
    int[] expectedArray3 = readInputAsArray("test_cases/sliding_window_maximum/test49_expected.txt");
    int[] array3 = arrayAndK3.getArray();
    int k3 = arrayAndK3.getK();
    int[] res3 = runTestCaseWithTimer(() -> { // 19 ms
      return solution1.maxSlidingWindow(array3, k3);
    });

    assertThat(res3).isEqualTo(expectedArray3);
  }
}