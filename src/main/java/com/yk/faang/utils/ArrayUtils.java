package com.yk.faang.utils;

import static java.lang.String.format;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Utils to work with arrays.
 */
public final class ArrayUtils {

  private static final String NEW_LINE = "\n";

  private ArrayUtils() {
    throw new AssertionError("Instance is not allowed.");
  }

  public static int[] generateRandomArray(int length, int maxNumber) {
    Random random = new Random();
    int[] array = new int[length];
    for (int i = 0; i < array.length; i++) {
      array[i] = random.nextInt(maxNumber);
    }

    return array;
  }

  public static byte[] convertIntArrayToBytes(int[] array) {
    byte[] bytes = new byte[array.length * 4];
    int bytesIndex = 0;
    for (int number : array) {
      writeIntAsBytes(number, bytes, bytesIndex);
      bytesIndex = bytesIndex + 4;
    }

    return bytes;
  }

  public static void writeIntAsBytes(int number, byte[] bytes, int bytesIndex) {
    bytes[bytesIndex] = (byte) (number >> 24);
    bytes[bytesIndex + 1] = (byte) (number >> 16);
    bytes[bytesIndex + 2] = (byte) (number >> 8);
    bytes[bytesIndex + 3] = (byte) number;
  }

  public static int[] convertBytesToIntArray(byte[] bytes) {
    int[] array = new int[bytes.length / 4];
    for (int i = 0; i < array.length; i++) {
      int number = readBytesAsInt(bytes, i * 4);
      array[i] = number;
    }
    return array;
  }

  /**
   * Reads 4 bytes from the array of bytes and converts them into int (Big Endian).
   */
  public static int readBytesAsInt(byte[] bytes, int bytesIndex) {
    return ((bytes[bytesIndex] & 0xFF) << 24) |
        ((bytes[bytesIndex + 1] & 0xFF) << 16) |
        ((bytes[bytesIndex + 2] & 0xFF) << 8) |
        (bytes[bytesIndex + 3] & 0xFF);
  }

  public static void writeArrayToBinaryFile(int[] array, String filename) {
    byte[] arrayLengthAsBytes = new byte[4];
    writeIntAsBytes(array.length, arrayLengthAsBytes, 0);
    byte[] bytes = convertIntArrayToBytes(array);

    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filename))) {
      bos.write(arrayLengthAsBytes);
      bos.write(bytes);
    } catch (IOException e) {
      throw new RuntimeException("Failed to write array to file.", e);
    }
  }

  public static void writeArrayToTextFile(int[] array, String filename) {
    try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filename, StandardCharsets.UTF_8))) {
      // Write array size
      fileWriter.write(numberAsString(array.length));
      fileWriter.write(NEW_LINE);

      for (int n : array) {
        fileWriter.write(numberAsString(n));
        fileWriter.write(NEW_LINE);
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to write array to file.", e);
    }
  }

  private static String numberAsString(int number) {
    return String.valueOf(number);
  }

  public static boolean areArraysEqual(int[] array1, int[] array2) {
    if (array1.length != array2.length) {
      return false;
    }

    for (int i = 0; i < array1.length; i++) {
      if (array1[i] != array2[i]) {
        return false;
      }
    }

    return true;
  }

  public static int[] readArrayFromBinaryFile(String filename) {
    byte[] lengthBytes = new byte[4];
    try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename))) {
      int readBytesForLength = bis.read(lengthBytes, 0, 4);
      if (readBytesForLength != 4) {
        throw new RuntimeException(format("Failed to read array length (4 bytes) from binary file. Read %s bytes instead of 4.", readBytesForLength));
      }

      int length = readBytesAsInt(lengthBytes, 0);
      int byteLength = length * 4;

      byte[] bytes = bis.readNBytes(byteLength);
      return convertBytesToIntArray(bytes);
    } catch (IOException e) {
      throw new RuntimeException("Failed to read array from binary file.", e);
    }
  }

  public static int[] readArrayFromTextFile(String filename) {
    try (BufferedReader fileReader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
      int length = Integer.parseInt(fileReader.readLine());
      int[] array = new int[length];
      for (int i = 0; i < array.length; i++) {
        array[i] = Integer.parseInt(fileReader.readLine());
      }
      return array;
    } catch (IOException e) {
      throw new RuntimeException("Failed to read array from text file.", e);
    }
  }
}
