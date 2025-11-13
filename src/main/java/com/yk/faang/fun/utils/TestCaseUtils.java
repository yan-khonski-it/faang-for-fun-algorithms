package com.yk.faang.fun.utils;

import static java.lang.String.format;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public final class TestCaseUtils {

  private TestCaseUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static List<String> readLines(String filename) {
    ClassLoader classLoader = TestCaseUtils.class.getClassLoader();
    InputStream classLoaderResourceAsStream = classLoader.getResourceAsStream(filename);
    if (classLoaderResourceAsStream == null) {
      throw new RuntimeException(format("Class loader resource: %s not found.", filename));
    }

    try (
        // @formatter:off
        InputStream is = classLoaderResourceAsStream;
        BufferedReader br = new BufferedReader(new InputStreamReader(is,   StandardCharsets.UTF_8))
        // @formatter:on
    ) {
      return br.lines().collect(Collectors.toList());
    } catch (IOException e) {
      throw new RuntimeException("Failed to read a resource file", e);
    }
  }

  public static ArrayAndK readInputAsArrayAndK(String filename) {
    List<String> lines = readLines(filename);
    if (lines.size() != 2) {
      throw new RuntimeException(
          format("Test file does not contain array and k. Invalid number of lines: %s, but we need 2.", lines.size()));
    }

    String arrayLine = lines.get(0);
    String kLine = lines.get(1);
    return parseArrayAndK(arrayLine, kLine);
  }

  public static int[] readInputAsArray(String filename) {
    List<String> lines = readLines(filename);
    if (lines.size() != 1) {
      throw new RuntimeException(
          format("Test file does not contain array. Invalid number of lines: %s, but we need 1.", lines.size()));
    }

    String arrayLine = lines.get(0);
    return parseArray(arrayLine);
  }

  private static ArrayAndK parseArrayAndK(String arrayLine, String kLine) {
    int[] array = parseArray(arrayLine);
    int k = Integer.parseInt(kLine);
    return ArrayAndK.of(array, k);
  }

  private static int[] parseArray(String arrayLine) {
    String[] numbers = arrayLine.split(",");
    int[] res = new int[numbers.length];

    // Parse the first element separately because it starts with [.
    res[0] = Integer.parseInt(numbers[0].replace("[", ""));
    for (int i = 1; i < numbers.length - 1; i++) {
      res[i] = Integer.parseInt(numbers[i]);
    }

    // Parse the last element separately because it ends with ].
    res[numbers.length - 1] = Integer.parseInt(numbers[numbers.length - 1].replace("]", ""));

    return res;
  }
}
