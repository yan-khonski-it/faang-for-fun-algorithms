package com.yk.faang.leetcode.l0704_binary_search;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BinarySearchTest {

    @ParameterizedTest
    @CsvSource({
        "0, 0",
        "5, 5",
        "10, -1"
    })
    void testSearch(int target, int expected) {
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Solution solution = new Solution();
        assertThat(solution.search(nums, target)).isEqualTo(expected);
    }

    @Test
    void testSearch_SingleElement() {
        Solution solution = new Solution();
        assertThat(solution.search(new int[]{5}, 5)).isEqualTo(0);
        assertThat(solution.search(new int[]{5}, 10)).isEqualTo(-1);
    }

    @Test
    void testSearch_EmptyArray() {
        Solution solution = new Solution();
        // The problem description says "Given an array of integers nums...", doesn't specify non-empty.
        // But implementation assumes valid indices.
        // If empty, maxIndex = -1. minIndex = 0. loop condition minIndex <= maxIndex (0 <= -1) is false.
        // Returns -1. Correct.
        assertThat(solution.search(new int[]{}, 5)).isEqualTo(-1);
    }
}
