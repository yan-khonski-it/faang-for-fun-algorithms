package com.yk.faang.leetcode.l0054_spiral_matrix;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class SpiralMatrixTest {

    static List<ISolution> getSolutions() {
        return List.of(
            new Solution1(),
            new Solution2()
        );
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testSpiralOrder(ISolution solution) {
        solution.reset();
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        List<Integer> expected = List.of(1, 2, 3, 6, 9, 8, 7, 4, 5);
        assertThat(solution.spiralOrder(matrix)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testSpiralOrder_Rectangular(ISolution solution) {
        solution.reset();
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        List<Integer> expected = List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7);
        assertThat(solution.spiralOrder(matrix)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testSpiralOrder_SingleElement(ISolution solution) {
        solution.reset();
        int[][] matrix = {{1}};
        List<Integer> expected = List.of(1);
        assertThat(solution.spiralOrder(matrix)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("getSolutions")
    void testSpiralOrder_Empty(ISolution solution) {
        solution.reset();
        int[][] matrix = {};
        assertThat(solution.spiralOrder(matrix)).isEmpty();
    }
}
