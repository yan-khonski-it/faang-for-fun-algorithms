package com.yk.faang.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ArrayUtilsTest {

    @TempDir
    Path tempDir;

    @Test
    void testGenerateRandomArray() {
        int length = 10;
        int maxNumber = 100;
        int[] array = ArrayUtils.generateRandomArray(length, maxNumber);

        assertThat(array).hasSize(length);
        for (int num : array) {
            assertThat(num).isBetween(0, maxNumber - 1);
        }
    }

    @Test
    void testConvertIntArrayToBytesAndBack() {
        int[] original = {1, 2, 300, -5, Integer.MAX_VALUE, Integer.MIN_VALUE};
        byte[] bytes = ArrayUtils.convertIntArrayToBytes(original);
        int[] result = ArrayUtils.convertBytesToIntArray(bytes);

        assertThat(result).containsExactly(original);
    }

    @Test
    void testWriteAndReadArrayToBinaryFile() {
        int[] original = {10, 20, 30, 40, 50};
        Path filePath = tempDir.resolve("test_array.bin");
        String filename = filePath.toString();

        ArrayUtils.writeArrayToBinaryFile(original, filename);

        assertThat(Files.exists(filePath)).isTrue();

        int[] result = ArrayUtils.readArrayFromBinaryFile(filename);
        assertThat(result).containsExactly(original);
    }

    @Test
    void testWriteAndReadArrayToTextFile() {
        int[] original = {100, 200, 300, -100};
        Path filePath = tempDir.resolve("test_array.txt");
        String filename = filePath.toString();

        ArrayUtils.writeArrayToTextFile(original, filename);

        assertThat(Files.exists(filePath)).isTrue();

        int[] result = ArrayUtils.readArrayFromTextFile(filename);
        assertThat(result).containsExactly(original);
    }

    @Test
    void testAreArraysEqual() {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        int[] arr3 = {1, 2, 4};
        int[] arr4 = {1, 2};

        assertThat(ArrayUtils.areArraysEqual(arr1, arr2)).isTrue();
        assertThat(ArrayUtils.areArraysEqual(arr1, arr3)).isFalse();
        assertThat(ArrayUtils.areArraysEqual(arr1, arr4)).isFalse();
    }

    @Test
    void testWriteIntAsBytes() {
        byte[] bytes = new byte[4];
        ArrayUtils.writeIntAsBytes(0x12345678, bytes, 0);
        assertThat(bytes).containsExactly(new byte[]{0x12, 0x34, 0x56, 0x78});
    }

    @Test
    void testReadBytesAsInt() {
        byte[] bytes = new byte[]{0x12, 0x34, 0x56, 0x78};
        int result = ArrayUtils.readBytesAsInt(bytes, 0);
        assertThat(result).isEqualTo(0x12345678);
    }
}
