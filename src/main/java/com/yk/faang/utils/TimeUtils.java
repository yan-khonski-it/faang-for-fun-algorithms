package com.yk.faang.utils;

import static java.lang.String.format;

import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Used to measure time for various tests.
 */
public class TimeUtils {

  private static final Logger LOGGER = LogManager.getLogger(TimeUtils.class);

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

  public static <T> T withTimerNs(Callable<T> callable, String label) {
    long startNs = System.nanoTime();
    T result;

    try {
      result = callable.call();
    } catch (Exception e) {
      throw new RuntimeException(format("Failed to call callable. Label: %s.", label), e);
    }

    long endNs = System.nanoTime();
    long totalNs = endNs - startNs;
    LOGGER.info("label: {} Time ns: {}.", label, totalNs);
    return result;
  }

  public static void withTimerNs(Runnable runnable, String label) {
    long startNs = System.nanoTime();

    try {
      runnable.run();
    } catch (Exception e) {
      throw new RuntimeException(format("Failed to run runnable. Label: %s.", label), e);
    }

    long endNs = System.nanoTime();
    long totalNs = endNs - startNs;
    LOGGER.info("label: {} Time ns: {}.", label, totalNs);
  }

  private static long toMilliseconds(long endNs, long startNs) {
    return (endNs - startNs) / 1_000_000;
  }
}
