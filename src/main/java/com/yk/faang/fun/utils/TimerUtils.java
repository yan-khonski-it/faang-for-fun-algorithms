package com.yk.faang.fun.utils;

import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used to measure time for various tests.
 */
public class TimerUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(TimerUtils.class);

  private TimerUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static <T> T runTestCaseWithTimer(Callable<T> testCase) {
    long startNs = System.nanoTime();
    T result;

    try {
      result = testCase.call();
    } catch (Exception e) {
      throw new RuntimeException("Failed to run test case.", e);
    }

    long endNs = System.nanoTime();
    long totalMs = toMilliseconds(endNs, startNs);
    LOGGER.debug("Test case run time {} ms.", totalMs);
    return result;
  }

  public static <T> T runTestCaseWithTimerNs(Callable<T> testCase) {
    long startNs = System.nanoTime();
    T result;

    try {
      result = testCase.call();
    } catch (Exception e) {
      throw new RuntimeException("Failed to run test case.", e);
    }

    long endNs = System.nanoTime();
    long totalNs = endNs - startNs;
    LOGGER.debug("Test case run time {} ns.", totalNs);
    return result;
  }

  private static long toMilliseconds(long endNs, long startNs) {
    return (endNs - startNs) / 1_000_000;
  }
}
