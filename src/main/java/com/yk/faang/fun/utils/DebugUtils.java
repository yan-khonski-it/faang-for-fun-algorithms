package com.yk.faang.fun.utils;

import static java.lang.String.format;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Can be used for debugging.
@SuppressWarnings("unused")
public final class DebugUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(DebugUtils.class);

  private DebugUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static void writeArrayToFile(int[] array, String filename) {
    File outputFile = new File(filename);

    try (
        // @formatter:off
        FileWriter fw = new FileWriter(outputFile);
        BufferedWriter br = new BufferedWriter(fw);
        PrintWriter printWriter = new PrintWriter(br)
        // @formatter:on
    ) {
      printWriter.print("[");
      printWriter.print(array[0]);
      for (int i = 1; i < array.length; i++) {
        printWriter.print(",");
        printWriter.print(array[i]);
      }

      printWriter.print("]");
    } catch (IOException e) {
      throw new RuntimeException(format("Failed to write into file: %s.", filename), e);
    }
  }
}
