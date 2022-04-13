package com.yk.faang.fun.algorithms.utils;

import static java.lang.String.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ArrayUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(ArrayUtils.class);

  private ArrayUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static void assertArraysEqual(int[] actual, int[] expected) {
    notNull(actual);
    notNull(expected);

    if (actual.length != expected.length) {
      throw new AssertionFailedException(
          format("Arrays sizes are not equal: actual size: %s, expected size %s.",
              actual.length,
              expected.length));
    }

    for (int i = 0; i < actual.length; i++) {
      if (actual[i] != expected[i]) {
        throw new AssertionFailedException(
            format("Actual array is not equal to expected array.\nActual: %s\nExpected: %s",
                arrayToString(actual),
                arrayToString(expected)));
      }
    }
  }

  public static String arrayToString(int[] array) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < array.length; i++) {
      if (i > 0) {
        sb.append(", ");
      }
      sb.append(array[i]);
    }

    return sb.toString();
  }

  public static void notNull(int[] array) {
    if (array == null) {
      throw new AssertionFailedException("Array is null.");
    }
  }
}
