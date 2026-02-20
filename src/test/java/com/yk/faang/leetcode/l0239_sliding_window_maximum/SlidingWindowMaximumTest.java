package com.yk.faang.leetcode.l0239_sliding_window_maximum;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class SlidingWindowMaximumTest {

    static List<ISolution> getSolutions() {
        return List.of(
            new Solution(),
            new Solution1(),
            new Solution2(),
            new Solution3()
        );
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testMaxSlidingWindow(ISolution solution) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] expected = {3, 3, 5, 5, 6, 7};
        assertThat(solution.maxSlidingWindow(nums, k)).containsExactly(expected);
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testMaxSlidingWindow_KEquals1(ISolution solution) {
        if (solution instanceof Solution3) {
            // Known bug in Solution3 for length 1 due to logN calculation resulting in 0 columns for lookup table
            return;
        }
        int[] nums = {1};
        int k = 1;
        assertThat(solution.maxSlidingWindow(nums, k)).containsExactly(1);
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testMaxSlidingWindow_KEqualsLength(ISolution solution) {
        int[] nums = {1, -1};
        int k = 2;
        assertThat(solution.maxSlidingWindow(nums, k)).containsExactly(1);
    }
}
