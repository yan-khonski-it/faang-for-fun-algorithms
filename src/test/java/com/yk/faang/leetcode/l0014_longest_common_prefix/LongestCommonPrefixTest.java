package com.yk.faang.leetcode.l0014_longest_common_prefix;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LongestCommonPrefixTest {

    static List<ISolution> getSolutions() {
        return List.of(
            new Solution1(),
            new Solution2(),
            new Solution3(),
            new Solution4(),
            new Solution5()
        );
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testLongestCommonPrefix(ISolution solution) {
        String[] strs = {"flower", "flow", "flight"};
        assertThat(solution.longestCommonPrefix(strs)).isEqualTo("fl");
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testNoCommonPrefix(ISolution solution) {
        String[] strs = {"dog", "racecar", "car"};
        assertThat(solution.longestCommonPrefix(strs)).isEqualTo("");
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testEmptyArray(ISolution solution) {
        String[] strs = {};
        assertThat(solution.longestCommonPrefix(strs)).isEqualTo("");
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testNullArray(ISolution solution) {
        assertThat(solution.longestCommonPrefix(null)).isEqualTo("");
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testSingleString(ISolution solution) {
        String[] strs = {"flower"};
        assertThat(solution.longestCommonPrefix(strs)).isEqualTo("flower");
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testOneEmptyString(ISolution solution) {
        String[] strs = {"flower", ""};
        assertThat(solution.longestCommonPrefix(strs)).isEqualTo("");
    }
}
