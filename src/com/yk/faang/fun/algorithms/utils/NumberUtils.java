package com.yk.faang.fun.algorithms.utils;

import static java.lang.String.format;

public final class NumberUtils {

  private NumberUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static void assertEqual(Number expected, Number actual) {
    if (!expected.equals(actual)) {
      throw new AssertionFailedException(
          format("Actual number %s is not equal to expected: %s.", actual, expected));
    }
  }

}
