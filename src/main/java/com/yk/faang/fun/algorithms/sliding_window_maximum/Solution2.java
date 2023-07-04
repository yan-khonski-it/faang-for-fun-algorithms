package com.yk.faang.fun.algorithms.sliding_window_maximum;

import static com.yk.faang.fun.utils.TimerUtils.runTestCaseWithTimer;
import static com.yk.faang.fun.utils.TestCaseUtils.readInputAsArray;
import static com.yk.faang.fun.utils.TestCaseUtils.readInputAsArrayAndK;
import static org.assertj.core.api.Assertions.assertThat;

import com.yk.faang.fun.utils.ArrayAndK;
import java.util.ArrayList;
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
public class Solution2 {

  // O(n * k)
  // Runtime: 2204 ms on leetcode
  // and on my machine
  // 268 ms
  // 402 ms
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

@SuppressWarnings("DuplicatedCode")
class Main2 {

  public static void main(String[] args) {
    Solution2 solution2 = new Solution2();
    int[] array1 = {1, 3, -1, -3, 5, 3, 6, 7};
    int k1 = 3;
    int[] res1 = solution2.maxSlidingWindow(array1, k1);
    assertThat(res1).isEqualTo(new int[]{3, 3, 5, 5, 6, 7});

    ArrayAndK arrayAndK2 = readInputAsArrayAndK("test_cases/sliding_window_maximum/test52.txt");
    int[] expectedArray2 = readInputAsArray("test_cases/sliding_window_maximum/test52_expected.txt");
    int[] array2 = arrayAndK2.getArray();
    int k2 = arrayAndK2.getK();
    int[] res2 = runTestCaseWithTimer(() -> { // 268 ms
      return solution2.maxSlidingWindow(array2, k2);
    });

    assertThat(res2).isEqualTo(expectedArray2);

    ArrayAndK arrayAndK3 = readInputAsArrayAndK("test_cases/sliding_window_maximum/test49.txt");
    int[] expectedArray3 = readInputAsArray("test_cases/sliding_window_maximum/test49_expected.txt");
    int[] array3 = arrayAndK3.getArray();
    int k3 = arrayAndK3.getK();
    int[] res3 = runTestCaseWithTimer(() -> { // 402 ms
      return solution2.maxSlidingWindow(array3, k3);
    });

    assertThat(res3).isEqualTo(expectedArray3);
  }
}
