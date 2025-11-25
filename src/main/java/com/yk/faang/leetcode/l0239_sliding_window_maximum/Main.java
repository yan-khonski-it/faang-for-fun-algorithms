package com.yk.faang.leetcode.l0239_sliding_window_maximum;

import static com.yk.faang.utils.TestCaseUtils.readInputAsArray;
import static com.yk.faang.utils.TestCaseUtils.readInputAsArrayAndK;
import static com.yk.faang.utils.TimeUtils.withTimerMs;
import static org.assertj.core.api.Assertions.assertThat;

import com.yk.faang.utils.ArrayAndK;

public class Main {

  static void main() {
    ISolution solution = new Solution();
    testSolution(solution);

    ISolution solution1 = new Solution1();
    testSolution(solution1);

    ISolution solution2 = new Solution2();
    testSolution(solution2);

    ISolution solution3 = new Solution3();
    testSolution(solution3);
  }

  private static void testSolution(ISolution solution) {
    int[] array1 = {1, 3, -1, -3, 5, 3, 6, 7};
    int k1 = 3;
    int[] res1 = solution.maxSlidingWindow(array1, k1);
    assertThat(res1).isEqualTo(new int[]{3, 3, 5, 5, 6, 7});

    ArrayAndK arrayAndK2 = readInputAsArrayAndK("test_cases/sliding_window_maximum/test52.txt");
    int[] expectedArray2 = readInputAsArray("test_cases/sliding_window_maximum/test52_expected.txt");
    int[] array2 = arrayAndK2.array();
    int k2 = arrayAndK2.k();

    int[] res2 = withTimerMs(() -> solution.maxSlidingWindow(array2, k2), "maxSlidingWindow(array2, k2)");
    assertThat(res2).isEqualTo(expectedArray2);

    ArrayAndK arrayAndK3 = readInputAsArrayAndK("test_cases/sliding_window_maximum/test49.txt");
    int[] expectedArray3 = readInputAsArray("test_cases/sliding_window_maximum/test49_expected.txt");
    int[] array3 = arrayAndK3.array();
    int k3 = arrayAndK3.k();

    int[] res3 = withTimerMs(() -> solution.maxSlidingWindow(array3, k3), "maxSlidingWindow(array3, k3)");

    assertThat(res3).isEqualTo(expectedArray3);
  }
}
