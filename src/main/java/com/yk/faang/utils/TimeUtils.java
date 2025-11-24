package com.yk.faang.utils;

import static java.lang.String.format;

import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used to measure time for various tests.
 */
@SuppressWarnings("LoggingSimilarMessage")
public class TimeUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(TimeUtils.class);

  private TimeUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static <T> T withTimerMs(Callable<T> callable, String label) {
    long startNs = System.nanoTime();
    T result;

    try {
      result = callable.call();
    } catch (Exception e) {
      throw new RuntimeException(format("Failed to call callable. Label: %s.", label), e);
    }

    long endNs = System.nanoTime();
    long totalMs = toMilliseconds(endNs, startNs);
    LOGGER.info("label: {} Time ms: {}.", label, totalMs);
    return result;
  }

  public static void withTimerMs(Runnable runnable, String label) {
    long startNs = System.nanoTime();

    try {
      runnable.run();
    } catch (Exception e) {
      throw new RuntimeException(format("Failed to run runnable. Label: %s.", label), e);
    }

    long endNs = System.nanoTime();
    long totalMs = toMilliseconds(endNs, startNs);
    LOGGER.info("label: {} Time ms: {}.", label, totalMs);
  }

  private static long toMilliseconds(long endNs, long startNs) {
    return (endNs - startNs) / 1_000_000;
  }
}
