package com.yk.faang.utils;

/**
 * Represents a test case containing input array of integers and k.
 */
public record ArrayAndK(int[] array, int k) {

  public static ArrayAndK of(int[] array, int k) {
    return new ArrayAndK(array, k);
  }
}
