package com.yk.faang.leetcode.l0033_search_in_rotated_sorted_array;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SearchInRotatedSortedArrayTest {

    @ParameterizedTest
    @CsvSource({
        "0, 4",
        "3, -1",
        "4, 0",
        "7, 3"
    })
    void testSearch(int target, int expected) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        Solution solution = new Solution();
        assertThat(solution.search(nums, target)).isEqualTo(expected);
    }

    @Test
    void testSearch_NotRotated() {
        int[] nums = {1, 3};
        Solution solution = new Solution();
        assertThat(solution.search(nums, 3)).isEqualTo(1);
    }

    @Test
    void testSearch_SingleElement() {
        int[] nums = {1};
        Solution solution = new Solution();
        assertThat(solution.search(nums, 1)).isEqualTo(0);
        assertThat(solution.search(nums, 0)).isEqualTo(-1);
    }

    @Test
    void testSearch_NullOrEmpty() {
        Solution solution = new Solution();
        assertThat(solution.search(null, 5)).isEqualTo(-1);
        assertThat(solution.search(new int[]{}, 5)).isEqualTo(-1);
    }
}
