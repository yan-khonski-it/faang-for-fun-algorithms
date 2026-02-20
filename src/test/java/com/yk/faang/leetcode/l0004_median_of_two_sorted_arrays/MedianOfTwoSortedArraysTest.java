package com.yk.faang.leetcode.l0004_median_of_two_sorted_arrays;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MedianOfTwoSortedArraysTest {

    static List<ISolution> getSolutions() {
        return List.of(
            new Solution(),
            new Solution1(),
            new Solution2()
        );
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testFindMedianSortedArrays(ISolution solution) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        assertThat(solution.findMedianSortedArrays(nums1, nums2)).isEqualTo(2.0);
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testFindMedianSortedArrays_EvenTotal(ISolution solution) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        assertThat(solution.findMedianSortedArrays(nums1, nums2)).isEqualTo(2.5);
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testFindMedianSortedArrays_EmptyOne(ISolution solution) {
        int[] nums1 = {};
        int[] nums2 = {1};
        assertThat(solution.findMedianSortedArrays(nums1, nums2)).isEqualTo(1.0);
    }
}
