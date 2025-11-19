package com.yk.faang.utils;

/**
 * Represents a test case containing input array of integers and k.
 */
public class ArrayAndK {

  private final int[] array;
  private final int k;

  private ArrayAndK(int[] array, int k) {
    this.array = array;
    this.k = k;
  }

  public static ArrayAndK of(int[] array, int k) {
    return new ArrayAndK(array, k);
  }

  public int[] getArray() {
    return array;
  }

  public int getK() {
    return k;
  }
}
