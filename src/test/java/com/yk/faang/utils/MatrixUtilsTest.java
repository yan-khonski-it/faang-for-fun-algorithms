package com.yk.faang.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MatrixUtilsTest {

    @Test
    void testMatrixToString() {
        int[][] matrix = {
            {1, 2},
            {3, 4}
        };
        String expected = "[1, 2]\n[3, 4]\n";
        String actual = MatrixUtils.matrixToString(matrix);
        assertThat(actual).isEqualTo(expected);
    }
}
