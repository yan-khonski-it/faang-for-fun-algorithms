package com.yk.faang.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ArrayAndKTest {

    @Test
    void testCreation() {
        int[] array = {1, 2, 3};
        int k = 5;
        ArrayAndK arrayAndK = ArrayAndK.of(array, k);

        assertThat(arrayAndK.array()).isEqualTo(array);
        assertThat(arrayAndK.k()).isEqualTo(k);
    }
}
