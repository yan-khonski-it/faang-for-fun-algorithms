package com.yk.faang.data;


import static com.yk.faang.utils.TimeUtils.withTimerMs;
import static org.assertj.core.api.Assertions.assertThat;

import com.yk.faang.utils.ArrayUtils;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomArrayToFile {

  private static final Logger LOGGER = LoggerFactory.getLogger(RandomArrayToFile.class);

  /**
   * Set these values.
   */
  private static final String ARRAY_TEXT_FILE = "C:\\Dev\\workspaces\\tech-rounds\\faang-for-fun-algorithms\\array-text.txt";
  private static final String ARRAY_BINARY_FILE = "C:\\Dev\\workspaces\\tech-rounds\\faang-for-fun-algorithms\\array-binary.bin";

  static void main() {
    removeFilesIfExist();

    int n = 10_000_000;
    int maxNumber = n;

    int[] array = ArrayUtils.generateRandomArray(n, maxNumber);

    withTimerMs(() -> ArrayUtils.writeArrayToTextFile(array, ARRAY_TEXT_FILE), "Write int array as text into text file.");
    withTimerMs(() -> ArrayUtils.writeArrayToBinaryFile(array, ARRAY_BINARY_FILE), "Write bytes into binary file.");

    int[] arrayFromTextFile = withTimerMs(() -> ArrayUtils.readArrayFromTextFile(ARRAY_TEXT_FILE), "Read int array from text file.");
    int[] arrayFromBinaryFile = withTimerMs(() -> ArrayUtils.readArrayFromBinaryFile(ARRAY_BINARY_FILE), "Read int array from binary file.");

    boolean arrayFromTextFileIsCorrect = ArrayUtils.areArraysEqual(array, arrayFromTextFile);
    assertThat(arrayFromTextFileIsCorrect).isTrue();

    boolean arrayFromBinaryFileIsCorrect = ArrayUtils.areArraysEqual(array, arrayFromBinaryFile);
    assertThat(arrayFromBinaryFileIsCorrect).isTrue();
  }

  private static void removeFilesIfExist() {
    removeExistingFiles(ARRAY_TEXT_FILE, ARRAY_BINARY_FILE);
  }

  private static void removeExistingFiles(String... filenames) {
    for (String filename : filenames) {
      removeIfFileExists(filename);
    }
  }

  private static void removeIfFileExists(String filename) {
    File file = new File(filename);
    if (file.exists() && file.isFile()) {
      if (file.delete()) {
        LOGGER.info("File {} has been deleted.", filename);
      } else {
        LOGGER.error("File {} could not be deleted.", filename);
      }
    }
  }
}
