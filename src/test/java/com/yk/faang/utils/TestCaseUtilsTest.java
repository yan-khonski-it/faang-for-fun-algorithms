package com.yk.faang.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class TestCaseUtilsTest {

    @Test
    void testReadLines() {
        List<String> lines = TestCaseUtils.readLines("test_array.txt");
        assertThat(lines).containsExactly("[1,2,3]");
    }

    @Test
    void testReadInputAsArray() {
        int[] array = TestCaseUtils.readInputAsArray("test_array.txt");
        assertThat(array).containsExactly(1, 2, 3);
    }

    @Test
    void testReadInputAsArrayAndK() {
        ArrayAndK arrayAndK = TestCaseUtils.readInputAsArrayAndK("test_array_and_k.txt");
        assertThat(arrayAndK.array()).containsExactly(4, 5, 6);
        assertThat(arrayAndK.k()).isEqualTo(10);
    }
}
